package com.mettre.moduleclient.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.moduleclient.mapper.UserMapper;
import com.mettre.moduleclient.service.RedisService;
import com.mettre.moduleclient.service.UserService;
import com.mettre.modulecommon.base.ReturnType;
import com.mettre.modulecommon.enum_.SmsTypeEnum;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.jwt.AccessToken;
import com.mettre.modulecommon.jwt.JwtUtils;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulecommon.pojo.User;
import com.mettre.modulecommon.pojoVm.*;
import com.mettre.modulecommon.util.AssembleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Value("${spring.tokenExpireTime}")
    public Long tokenExpireTime;

    @Value("${spring.saveLoginTime}")
    public Integer saveLoginTime;

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public RedisService redisService;

    @Override
    public int deleteByPrimaryKey(String userId) {
        return 0;
    }

    @Override
    public int insert(UserRegisterVM record) {

        if (userMapper.selectByPhone(record.getPhone()) != null) {
            throw new CustomerException("该手机号已注册");
        }
        String code = redisService.get(AssembleUtils.sendMessageUtils(record.getPhone(), SmsTypeEnum.REGISTER));
        if (StrUtil.isBlank(code)) {
            throw new CustomerException("验证码已过期，请重新获取");
        }
        if (!record.getCaptchaCode().toLowerCase().equals(code.toLowerCase())) {
            throw new CustomerException("验证码不正确");
        }

        User user = new User(record);
        int type = userMapper.insert(user);
        return ReturnType.ReturnType(type, "注册失败");
    }

    @Override
    public int insertSelective(UserVM record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new CustomerException("用户信息不存在");
        }
        return user;
    }

    @Override
    public AccessToken selectByPhoneAndPassword(String phone, String password) {
        User user = userMapper.selectByPhone(phone);
        if (user == null) {
            throw new CustomerException("该手机号未注册");
        }
        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            throw new CustomerException("账号或者密码不正确");
        }
        return JwtUtils.saveAccessToken(user, tokenExpireTime, saveLoginTime);
    }

    @Override
    public int updateByPrimaryKeySelective(UserVM record, String userId) {

        UserVM.UserVmEmpty(record);
        User user = new User(record, userId);
        int type = userMapper.updateByPrimaryKeySelective(user);
        return ReturnType.ReturnType(type, "个人修改信息失败");
    }

    @Override
    public int updateByPrimaryKey(UserVM record) {
        return 0;
    }

    @Override
    public int forgetPassword(ForgetPasswordVM forgetPasswordVM) {

        String code = redisService.get(AssembleUtils.sendMessageUtils(forgetPasswordVM.getPhone(), SmsTypeEnum.FORGET_PASSWORD));
        if (StrUtil.isBlank(code)) {
            throw new CustomerException("验证码已过期，请重新获取");
        }
        if (!forgetPasswordVM.getCaptchaCode().toLowerCase().equals(code.toLowerCase())) {
            throw new CustomerException("验证码不正确");
        }
        int type = userMapper.forgetPassword(new User(forgetPasswordVM));
        return ReturnType.ReturnType(type, "密码修改失败");
    }

    @Override
    public int modifyPassword(ModifyPasswordVM modifyPasswordVM) {

        String userId = SecurityContextStore.getContext().getUserId();
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null || !new BCryptPasswordEncoder().matches(modifyPasswordVM.getOldPassword(), user.getPassword())) {
            throw new CustomerException("老密码输入错误！");
        }

        int type = userMapper.modifyPassword(new User(modifyPasswordVM, userId));
        return ReturnType.ReturnType(type, "密码修改失败");
    }

    @Override
    public List<User> searchByPhone(String phoneStr) {

        return userMapper.searchByPhone(phoneStr);
    }

    @Override
    public User selectByPhone(String phoneStr) {
        return userMapper.selectByPhone(phoneStr);
    }

    @Override
    public Page<User> searchByPhoneByPage(Page<User> page, selectByPhoneVM selectByPhoneVM) {
        List<User> followList = (List<User>) userMapper.searchByPhoneByPage(page, selectByPhoneVM);
        return page.setRecords(followList);
    }
}
