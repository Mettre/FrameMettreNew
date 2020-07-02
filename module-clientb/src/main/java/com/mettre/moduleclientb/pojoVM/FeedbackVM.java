package com.mettre.moduleclientb.pojoVM;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class FeedbackVM {

    @NotBlank(message = "反馈内容不能为空")
    private String content;
}
