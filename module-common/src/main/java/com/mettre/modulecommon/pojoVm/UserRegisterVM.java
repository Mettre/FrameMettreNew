package com.mettre.modulecommon.pojoVm;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;


@Data
public class UserRegisterVM {

    @Size(min = 6, max = 12, message = "密码格式必须为6-16位")
    private String password;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    @NotBlank(message = "验证码不能为空")
    private String captchaCode;

    @NotBlank(message = "昵称不能为空")
    private String nickname;
}
