package com.mettre.moduleclient.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mettre.moduleclient.inputPojo.AccountPojo;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Account {

    Logger logger = LoggerFactory.getLogger(Account.class);

    private Integer id;

    private String title;

    private BigDecimal amount;

    private String classification;

    private Integer type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordDay;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime crateTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modifyTime;

    private String userId;

    public Account() {

    }

    /**
     * 记账
     *
     * @param accountPojo
     */
    public Account(AccountPojo accountPojo, String userId) {
        this.title = accountPojo.getTitle();
        this.userId = userId;
        this.amount = accountPojo.getAmount();
        this.classification = accountPojo.getClassification();
        this.type = accountPojo.getType();
        this.recordDay = accountPojo.getRecordDay();
        logger.error("解金澎recordDay  " + recordDay);
        this.crateTime = LocalDateTime.now();
        logger.error("解金澎crateTime  " + crateTime);
        logger.error("解金澎crateTime2222222  " + LocalDateTime.now());
    }

    /**
     * 修改
     *
     * @param accountPojo
     */
    public Account(AccountPojo accountPojo, String userId, Boolean modify) {
        this.id = accountPojo.getId();
        this.userId = userId;
        this.title = accountPojo.getTitle();
        this.amount = accountPojo.getAmount();
        this.classification = accountPojo.getClassification();
        this.type = accountPojo.getType();
        this.recordDay = accountPojo.getRecordDay();
        this.modifyTime = LocalDateTime.now();
    }
}