package com.mettre.moduleclient.controller;

import com.mettre.moduleclient.inputPojo.SmsVM;
import com.mettre.moduleclient.service.SmsService;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.base.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(description = "短信模块")
public class SmsController {

    private static final Logger logger = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    public SmsService smsService;

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ApiOperation(value = "发送短信")
    public Result<Object> sendMessage(@Valid @RequestBody SmsVM smsVM) {
        return Result.ok(smsService.sendMessage(smsVM));
    }

}
