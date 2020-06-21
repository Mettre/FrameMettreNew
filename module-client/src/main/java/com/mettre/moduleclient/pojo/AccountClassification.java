package com.mettre.moduleclient.pojo;

import lombok.Data;

@Data
public class AccountClassification {

    private Integer id;

    private String classificationTitle;

    private Integer classificationType;

    public AccountClassification() {

    }

    /**
     * 添加
     *
     * @param accountClassification
     */
    public AccountClassification(AccountClassification accountClassification, Boolean add) {
        if (!add) {
            this.id = accountClassification.getId();
        }
        this.classificationTitle = accountClassification.getClassificationTitle();
        this.classificationType = accountClassification.getClassificationType();
    }
}