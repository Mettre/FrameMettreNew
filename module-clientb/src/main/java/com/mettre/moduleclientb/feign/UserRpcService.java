package com.mettre.moduleclientb.feign;

import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.pojo.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("module-client")
@RestController
public interface UserRpcService {

    @RequestMapping(value = "/rpc/getUserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "根据userId获得个人信息")
    Result<User> findUserInfo(@RequestParam("userId") String userId);

}
