package com.mettre.moduleclient.rpc;


import com.mettre.moduleclient.mapper.UserMapper;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.pojo.User;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rpc")
public class RpcService {

    Logger logger = LoggerFactory.getLogger(RpcService.class);

    @Autowired
    public UserMapper userMapper;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "根据userId获取个人信息")
    public Result<User> findUserInfo(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        logger.error("用户信息怎么样了？");
        if (user == null) {
            logger.error("用户信息获取失败");
            throw new CustomerException("用户信息获取失败");
//            return Result.error();
        }
        logger.error("用户信息获取往下走");

        return Result.ok(new User(user));
    }
}
