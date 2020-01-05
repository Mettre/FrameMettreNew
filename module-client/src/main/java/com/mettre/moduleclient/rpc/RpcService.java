package com.mettre.moduleclient.rpc;


import com.google.gson.Gson;
import com.mettre.moduleclient.mapper.UserMapper;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.pojo.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rpc")
public class RpcService {

    @Autowired
    public UserMapper userMapper;


    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取个人信息")
    public User findUserInfo(String userId) {
        System.out.println("mettre:   " + userId);
        User user = userMapper.selectById(userId);
        System.out.println("mettre:   " + user == null);
        if (user == null) {
            System.out.println("mettre:   " + 11111111);
            throw new CustomerException("个人信息获取失败");
        }
        System.out.println("mettre:   " + 22222222);
        System.out.println("mettre:   " + new Gson().toJson(user));
        return new User(user);
    }
}
