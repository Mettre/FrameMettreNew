package com.mettre.moduleclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mettre.modulecommon.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper extends BaseMapper<User> {

    User selectByPhone(String name);

    int insert(User user);

    List<User> searchByPhone(String phoneStr);

}