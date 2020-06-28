package com.mettre.moduleclient.service;

import cn.hutool.core.util.StrUtil;
import com.mettre.moduleclient.inputPojo.SmsVM;
import com.mettre.moduleclient.mapper.UserMapper;
import com.mettre.moduleclient.pojo.SendMessageBean;
import com.mettre.moduleclient.pojo.Sms;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.pojo.User;
import com.mettre.modulecommon.util.AssembleUtils;
import com.mettre.modulecommon.util.CreateVerifyCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    @Autowired
    public UserMapper userMapper;

    @Autowired
    private RedisService redisService;


    public SendMessageBean sendMessage(SmsVM smsVM) {
        if (null == smsVM.getSmsType()) {
            throw new CustomerException("短信类型不能为空");
        }

        User user = userMapper.selectByPhone(smsVM.getSmsPhone());
        switch (smsVM.getSmsType()) {
            case REGISTER:
                if (user != null)
                    throw new CustomerException("该手机号已注册");
                break;
            case FORGET_PASSWORD:
            case MODIFY_PASSWORD:
                if (user == null)
                    throw new CustomerException("该手机号未注册");
                break;
        }
        String code = new CreateVerifyCode().randomInteger(4);
        Sms sms = new Sms(smsVM, code);
        redisService.set(AssembleUtils.sendMessageUtils(sms.getSmsPhone(), smsVM.getSmsType()), code);
        redisService.expire(AssembleUtils.sendMessageUtils(sms.getSmsPhone(), smsVM.getSmsType()), 1 * 60);
        if (!StrUtil.isEmpty(code)) {
            return new SendMessageBean(code);
        } else {
            throw new CustomerException("短信发送失败");
        }
    }
}
