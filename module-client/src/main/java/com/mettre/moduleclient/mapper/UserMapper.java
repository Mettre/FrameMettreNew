package com.mettre.moduleclient.mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulecommon.pojo.User;
import com.mettre.modulecommon.pojoVm.selectByPhoneVM;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    User selectByPhoneAndPassword(@Param(value = "phone") String phone, @Param(value = "password") String password);

    User selectByPhone(String phone);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int forgetPassword(User record);

    int modifyPassword(User user);

    List<User> searchByPhone(String phoneStr);

    List<User> searchByPhoneByPage(Page<User> page, @Param(value = "selectByPhoneVM") selectByPhoneVM selectByPhoneVM);

}