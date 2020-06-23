package com.mettre.modulecommon.util;

import com.mettre.modulecommon.enums.SmsTypeEnum;

/**
 * 组装拼接字符串
 */
public class AssembleUtils {

    /**
     * 注册
     *
     * @param phone
     * @return
     */
    public static String registerPasswordUtils(String phone) {
        return phone + "-register";
    }

    /**
     * 忘记密码
     *
     * @param phone
     * @return
     */
    public static String forgetPasswordUtils(String phone) {
        return phone + "-forget";
    }

    /**
     * 修改密码
     *
     * @param phone
     * @return
     */
    public static String modifyPasswordUtils(String phone) {
        return phone + "-modify";
    }


    /**
     * 短信
     *
     * @param phone
     * @return
     */
    public static String sendMessageUtils(String phone, SmsTypeEnum smsTypeEnum) {
        return phone + "-"+smsTypeEnum.name();
    }
}
