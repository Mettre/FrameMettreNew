package com.mettre.moduleclientb.pojoVM;

import com.mettre.modulecommon.enum_.StateEnum;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class FeedbackModifyVM {

    @Min(value = 1, message = "反馈id为空")
    private Long feedbackId;

    private StateEnum state;

}
