package com.mettre.modulecommon.pojoVm;

import com.mettre.modulecommon.pojo.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class selectByPhoneVM extends BasePage {

    @ApiModelProperty(value = "手机号")
    String phoneStr;
}
