package com.mettre.moduleclient.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mettre.moduleclient.inputPojo.AccountPojo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Account {
    private Integer id;

    private String title;

    private BigDecimal amount;

    private String classification;

    private Integer type;

    private Date recordDay;

    private Date crateTime;

    private Date modifyTime;

    private Integer userId;


    /**
     * 记账
     *
     * @param accountPojo
     */
    public Account(AccountPojo accountPojo, Integer userId) {
        this.title = accountPojo.getTitle();
        this.userId = userId;
        this.amount = accountPojo.getAmount();
        this.classification = accountPojo.getClassification();
        this.type = accountPojo.getType();
        this.recordDay = accountPojo.getRecordDay();
        this.crateTime = new Date();
    }

    /**
     * 修改
     *
     * @param accountPojo
     */
    public Account(AccountPojo accountPojo, Integer userId, Boolean modify) {
        this.id = accountPojo.getId();
        this.userId = userId;
        this.title = accountPojo.getTitle();
        this.amount = accountPojo.getAmount();
        this.classification = accountPojo.getClassification();
        this.type = accountPojo.getType();
        this.recordDay = accountPojo.getRecordDay();
        this.modifyTime = new Date();
    }
}