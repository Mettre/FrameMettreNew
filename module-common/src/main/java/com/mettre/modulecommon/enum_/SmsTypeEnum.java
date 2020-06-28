package com.mettre.modulecommon.enum_;

public enum SmsTypeEnum {

    REGISTER("SMS_146615681", "注册"),
    FORGET_PASSWORD("SMS_146616148", "忘记密码"),
    MODIFY_PASSWORD("SMS_146616148", "修改密码");

    private String code;
    private String explain;

    SmsTypeEnum(String code, String explain) {
        this.code = code;
        this.explain = explain;
    }

    public String getCode() {
        return code;
    }

    public static boolean contains(String smsType) {
        for (SmsTypeEnum typeEnum : SmsTypeEnum.values()) {
            if (typeEnum.name().equals(smsType)) {
                return true;
            }
        }
        return false;
    }
}
