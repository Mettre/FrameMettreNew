package com.mettre.modulecommon.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;

    private String name;

    private String password;

    private Date createDate;

    private Date modifyDate;

    private String nickname;
}