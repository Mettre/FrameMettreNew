package com.mettre.moduleclient.pojo;

import lombok.Data;

@Data
public class SendMessageBean {

    private String message;

    public SendMessageBean(String message) {
        this.message = message;
    }
}
