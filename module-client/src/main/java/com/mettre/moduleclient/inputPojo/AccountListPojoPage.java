package com.mettre.moduleclient.inputPojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mettre.modulecommon.pojo.BasePage;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AccountListPojoPage extends BasePage {

    private String title;

    private String classification;

    private Integer type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDay;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDay;
}
