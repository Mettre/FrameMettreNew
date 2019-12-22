package com.mettre.modulecommon.exception;

import com.mettre.modulecommon.constant.CommonConstant;
import com.mettre.modulecommon.enum_.ResultEnum;
import lombok.Data;
//
//@Data
//public class CustomerException extends RuntimeException {
//    private Integer code;
//
//    public CustomerException(ResultEnum errorCodeEnum) {
//        super(errorCodeEnum.getMsg());
//        this.code = errorCodeEnum.getCode();
//    }
//
//    public CustomerException(String message) {
//        super(message);
//        this.code = CommonConstant.ERROR;
//    }
//
//    public Integer getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }
//}