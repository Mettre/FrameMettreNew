package com.mettre.modulecommon.base;

import lombok.Data;

import java.util.List;

@Data
public class ResultList<T> {

    public List<T> data;

    public ResultList(List<T> data) {
        this.data = data;
    }
}
