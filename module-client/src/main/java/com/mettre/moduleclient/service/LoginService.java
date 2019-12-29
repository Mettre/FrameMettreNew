package com.mettre.moduleclient.service;


import com.mettre.moduleclient.pojo.UserRegisterVM;
import com.mettre.modulecommon.jwt.AccessToken;
import com.mettre.modulecommon.pojo.User;

import java.util.List;

public interface LoginService {

    int insert(UserRegisterVM record);

    AccessToken selectByPhoneAndPassword(String phone, String password);

    List<User> searchByPhone(String phoneStr);
}
