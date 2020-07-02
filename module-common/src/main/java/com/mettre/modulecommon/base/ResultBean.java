package com.mettre.modulecommon.base;

import lombok.Data;

@Data
public class ResultBean {

    private String result;

    public ResultBean() {
    }

    public ResultBean(String result) {
        this.result = result;
    }
}
