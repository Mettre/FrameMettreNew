package com.mettre.moduleclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.moduleclient.pojo.selectByPhoneVM;
import com.mettre.modulecommon.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper extends BaseMapper<User> {

    User selectByPhone(String name);

    int insert(User user);

    List<User> searchByPhone(String phoneStr);

    List<User> searchByPhoneByPage(Page<User> page, @Param(value = "selectByPhoneVM") selectByPhoneVM selectByPhoneVM);

}