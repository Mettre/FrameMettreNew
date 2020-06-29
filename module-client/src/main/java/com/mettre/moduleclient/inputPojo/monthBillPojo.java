package com.mettre.moduleclient.inputPojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class monthBillPojo {

    @ApiModelProperty(value = "年份")
    private Integer year;

    @ApiModelProperty(value = "月份")
    private Integer month;
}
