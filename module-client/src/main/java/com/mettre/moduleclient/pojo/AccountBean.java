package com.mettre.moduleclient.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AccountBean {

    private Integer id;

    private String title;

    private BigDecimal amount;

    private String classification;

    private Integer type;

    private Date crateTime;

    private Date modifyTime;
}
