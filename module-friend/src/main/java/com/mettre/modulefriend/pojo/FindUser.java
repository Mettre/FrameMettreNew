package com.mettre.modulefriend.pojo;
import com.mettre.modulecommon.enum_.GenderEnum;
import lombok.Data;

import java.util.Date;

/**
 * 搜索用户(是否已关注)
 */
@Data
public class FindUser {

    private Long followId;

    private String userId;

    private String userName;

    private Date updateTime;

    private String headAvatar;

    private String backgroundWall;

    private GenderEnum gender;

    private Boolean isFollow;//否已关注

    private Boolean isFans;//是否是粉丝
}
