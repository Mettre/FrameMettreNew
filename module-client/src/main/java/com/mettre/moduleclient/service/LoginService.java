package com.mettre.moduleclient.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.moduleclient.pojo.UserRegisterVM;
import com.mettre.moduleclient.pojo.selectByPhoneVM;
import com.mettre.modulecommon.jwt.AccessToken;
import com.mettre.modulecommon.pojo.User;

import java.util.List;

public interface LoginService {

    int insert(UserRegisterVM record);

    AccessToken selectByPhoneAndPassword(String phone, String password);

    List<User> searchByPhone(String phoneStr);

    User selectByPhone(String name);

    Page<User> searchByPhoneByPage(Page<User> page, selectByPhoneVM phoneStr);
}
