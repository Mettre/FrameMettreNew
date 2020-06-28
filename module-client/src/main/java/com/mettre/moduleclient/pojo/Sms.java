package com.mettre.moduleclient.pojo;

import com.mettre.moduleclient.inputPojo.SmsVM;
import com.mettre.modulecommon.enum_.SmsTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Sms implements Serializable {

    private Long smsId;

    private SmsTypeEnum smsType;

    private String smsPhone;

    private String smsContent;

    private Date creationTime;

    private String userId;

    private String userName;

    public Sms() {
    }

//    新增短信
    public Sms(SmsVM smsVM, String code) {
        this.smsType = smsVM.getSmsType();
        this.smsPhone = smsVM.getSmsPhone();
        this.smsContent = code;
        this.creationTime = new Date();
    }
}