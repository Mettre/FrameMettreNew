package com.mettre.moduleclient.serviceImpl;

import com.mettre.moduleclient.service.LoginService;
import com.mettre.modulecommon.enum_.ResultEnum;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulecommon.pojo.User;
import com.mettre.pojoVM.UserRegisterVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    public LoginService loginService;

    @Override
    public int insert(UserRegisterVM record) {

        String userId = SecurityContextStore.getContext().getUserId();
        int type = 0;
        return 0;
    }

}
