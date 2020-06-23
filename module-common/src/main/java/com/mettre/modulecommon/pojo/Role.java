package com.mettre.modulecommon.pojo;

import lombok.Data;

import java.util.Set;

@Data
public class Role {

    private String id;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<Permissions> permissions;
}