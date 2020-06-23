package com.mettre.modulefriend.pojoVM;

import com.mettre.modulecommon.pojo.BasePage;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class PersonalMomentsListVM extends BasePage {

    @NotBlank(message = "查询人不能为空")
    String publisherUserId;
}
