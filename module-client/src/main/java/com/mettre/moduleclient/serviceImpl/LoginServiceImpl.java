package com.mettre.moduleclient.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.moduleclient.mapper.UserMapper;
import com.mettre.moduleclient.pojo.UserRegisterVM;
import com.mettre.moduleclient.pojo.selectByPhoneVM;
import com.mettre.moduleclient.service.LoginService;
import com.mettre.modulecommon.base.ReturnType;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.jwt.AccessToken;
import com.mettre.modulecommon.jwt.JwtUtils;
import com.mettre.modulecommon.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    public UserMapper userMapper;

    @Value("${spring.tokenExpireTime}")
    private Long tokenExpireTime;

    @Value("${spring.saveLoginTime}")
    private Integer saveLoginTime;

    @Override
    public int insert(UserRegisterVM record) {
        User user1 = userMapper.selectByPhone(record.getPhone());
        if (user1 != null) {
            return ReturnType.ReturnType("该手机已经被注册");
        }
        User user = new User(record.getPhone(), record.getPassword(), record.getNickname());
        int type = userMapper.insert(user);
        return ReturnType.ReturnType(type, "注册失败");
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
    public List<User> searchByPhone(String phoneStr) {

        return userMapper.searchByPhone(phoneStr);
    }

    @Override
    public User selectByPhone(String name) {
        return userMapper.selectByPhone(name);
    }

    @Override
    public Page<User> searchByPhoneByPage(Page<User> page, selectByPhoneVM selectByPhoneVM) {
        List<User> followList = (List<User>) userMapper.searchByPhoneByPage(page, selectByPhoneVM);
        return page.setRecords(followList);
    }

}
