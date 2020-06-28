package com.mettre.moduleclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulecommon.jwt.AccessToken;
import com.mettre.modulecommon.pojo.User;
import com.mettre.modulecommon.pojoVm.*;

import java.util.List;

public interface UserService {

    int deleteByPrimaryKey(String userId);

    AccessToken insert(UserRegisterVM record);

    int insertSelective(UserVM record);

    User selectByPrimaryKey(String userId);

    AccessToken selectByPhoneAndPassword(String phone, String password);

    int updateByPrimaryKeySelective(UserVM record, String userId);

    int updateByPrimaryKey(UserVM record);

    int forgetPassword(ForgetPasswordVM forgetPasswordVM);

    int modifyPassword(ModifyPasswordVM forgetPasswordVM);

    List<User> searchByPhone(String phoneStr);

    User selectByPhone(String phoneStr);

    Page<User> searchByPhoneByPage(Page<User> page, selectByPhoneVM phoneStr);
}
