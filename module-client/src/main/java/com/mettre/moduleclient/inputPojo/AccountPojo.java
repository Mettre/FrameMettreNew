package com.mettre.moduleclient.inputPojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class AccountPojo {

    private Integer id;

    private String title;

    private BigDecimal amount;

    private Integer classification;

    private Integer type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordDay;

    private String userId;

}
