package com.mettre.modulefriend.pojo;

import com.mettre.modulefriend.pojoVM.VisitorVM;
import lombok.Data;

import java.util.Date;

@Data
public class Visitor {

    private Long visitorId;

    private Date creationTime;

    private String userId;//访问者

    private String visitorsUser;//被访问者

    private String userName;

    private String headAvatar;

    public Visitor() {
    }

    public Visitor(VisitorVM visitorVM, String userId) {
        this.creationTime = new Date();
        this.userId = userId;
        this.visitorsUser = visitorVM.getVisitorsUesr();
    }

    public Visitor(String visitorsUser, String userId) {
        this.creationTime = new Date();
        this.userId = userId;
        this.visitorsUser = visitorsUser;
    }
}