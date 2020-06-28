package com.mettre.modulecommon.base;

import com.mettre.modulecommon.enum_.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zhuwj on 2017/11/12.
 */
@ApiModel(description = "响应结果")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 8992436576262574064L;

    private Integer code;

    private String msg;

    private T result;

    private Long timestamp;

    @ApiModelProperty(value = "状态码")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @ApiModelProperty("调用结果消息")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @ApiModelProperty("成功时响应数据")
    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }


    public static <T> Result<T> loginInvalid() {
        Result<T> msg = new Result<>();
        msg.msg = "登录失效";
        msg.code = 401;
        msg.result = (T) new ResultBean();
        return msg.putTimeStamp();
    }

    public static <T> Result<T> NotLoginEd() {
        Result<T> msg = new Result<>();
        msg.msg = "未登录";
        msg.code = 401;
        msg.result = (T) new ResultBean();
        return msg.putTimeStamp();
    }

    /**
     * 系统定义的错误返回结果
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> error() {
        return error(ResultEnum.BUSINESS_FAIL.getCode(), ResultEnum.BUSINESS_FAIL.getMsg());
    }

    /**
     * 自定义返回错误结果 默认错误码为1  业务处理失败
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String message) {
        return error(ResultEnum.BUSINESS_FAIL.getCode(), message);
    }

    /**
     * @param code 传入对应的错误码 自动返回对应错误消息
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(Integer code) {
        return error(code, ResultEnum.getMsg(code));
    }

    /**
     * 全部自定义消息 与错误码
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> msg = new Result<>();
        msg.msg = message;
        msg.code = code;
        msg.result = (T) new ResultBean();
        return msg.putTimeStamp();
    }

    /**
     * 使用系统默认错误码 传入错误返回结果
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> Result<T> errorResult(T result) {
        return new Result<T>()
                .result(result)
                .putTimeStamp()
                .code(ResultEnum.BUSINESS_FAIL.getCode()).msg(ResultEnum.BUSINESS_FAIL.getMsg());
    }

    /**
     * 请求成功  默认code为0 掺入对应的返回结果
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok() {
        return new Result<T>()
                .putTimeStamp()
                .code(ResultEnum.RESULT_SUCCESS.getCode())
                .result((T) new com.mettre.modulecommon.base.ResultBean())
                .msg(ResultEnum.RESULT_SUCCESS.getMsg());
    }

    /**
     * 请求成功  默认code为0 掺入对应的返回结果
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T result) {
        return new Result<T>()
                .result(result)
                .putTimeStamp()
                .code(ResultEnum.RESULT_SUCCESS.getCode())
                .msg(ResultEnum.RESULT_SUCCESS.getMsg());
    }

    private Result<T> putTimeStamp() {
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public Result() {

    }

    public Result<T> result(T result) {
        this.result = result;
        return this;
    }

    public Result<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public Result<T> msg(String msg) {
        this.msg = msg;
        return this;
    }
}
