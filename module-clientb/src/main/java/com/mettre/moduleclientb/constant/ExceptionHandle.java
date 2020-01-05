package com.mettre.moduleclientb.constant;

import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.enums.CustomerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 直接在右边的文件框里编辑你说需要注释的东西，
 * 然后应用保存之后,当你创建类的时候就会自动生成注释。
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)  //申明要捕获的异常类
    @ResponseBody
    public Result<Object> handle(Exception e) {
        if (e instanceof CustomerException) {
            CustomerException customerException = (CustomerException) e;
            return Result.error(customerException.getCode(), e.getMessage());
        } else if (e instanceof BindException) {
            BindException exception = (BindException) e;
            FieldError fieldError = exception.getBindingResult().getFieldError();
            return Result.error(400, fieldError.getDefaultMessage());
        } else {
            logger.error("[系统异常 {}", e);
            return Result.error("未知错误" + e.getMessage());
        }
    }
}