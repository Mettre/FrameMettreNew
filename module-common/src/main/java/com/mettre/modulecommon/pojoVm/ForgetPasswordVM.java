package com.mettre.modulecommon.pojoVm;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ForgetPasswordVM {

    @NotBlank(message = "手机号不能为空")
    private String phone;

    @NotNull
    @Size(max = 16, min = 6, message = "密码格式必须为6-16位")
    private String password;

    @NotBlank(message = "验证码不能为空")
    private String captchaCode;
}
