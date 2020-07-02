package com.mettre.moduleclientb.pojoVM;

import com.mettre.modulecommon.enum_.StateEnum;
import com.mettre.modulecommon.pojo.BasePage;
import lombok.Data;

@Data
public class FeedbackSearchVM extends BasePage {

    private StateEnum state;

    public FeedbackSearchVM() {
    }

    public FeedbackSearchVM(StateEnum state) {
        this.state = state;
    }
}
