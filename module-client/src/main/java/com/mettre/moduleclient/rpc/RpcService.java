package com.mettre.moduleclient.rpc;


import com.google.gson.Gson;
import com.mettre.moduleclient.mapper.UserMapper;
import com.mettre.modulecommon.base.Result;
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
    @ApiOperation(value = "根据userId获取个人信息")
    public Result<User> findUserInfo(String userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new CustomerException("个人信息获取失败");
        }
        return Result.ok(new User(user));
    }
}
