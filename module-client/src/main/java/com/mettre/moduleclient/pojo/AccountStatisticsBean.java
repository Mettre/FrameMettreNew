package com.mettre.moduleclient.pojo;

import lombok.Data;

@Data
public class AccountStatisticsBean {

    public Integer totalAccountNum;//坚持记账记账总条数

    public Integer totalAccountDay;//坚持记账总天数

    public AccountStatisticsBean(Integer totalAccountNum, Integer totalAccountDay) {
        this.totalAccountNum = totalAccountNum;
        this.totalAccountDay = totalAccountDay;
    }
}
