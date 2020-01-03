package com.mettre.modulecommon.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@Data
@TableName("f_user")
public class User {

    private int id;

    private String name;

    private String password;

    private Date createDate;

    private Date modifyDate;

    private String nickname;

    public User() {

    }


    /**
     * 激活区个人--抛出密码
     */
    public User(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.createDate = user.getCreateDate();
        this.modifyDate = user.getModifyDate();
        this.nickname = user.getNickname();
    }

    /**
     * 注册
     */
    public User(String name, String password, String nickname) {
        this.name = name;
        this.password = new BCryptPasswordEncoder().encode(password.trim());
        this.createDate = new Date();
        this.modifyDate = new Date();
        this.nickname = nickname;
    }
}