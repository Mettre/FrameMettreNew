package com.mettre.modulecommon.base;

import lombok.Data;

import java.util.List;

@Data
public class ResultListBean<T> {

    private List<T> data;

    public ResultListBean(List<T> data) {
        this.data = data;
    }
}
