package com.mettre.modulefriend.pojo;

import com.mettre.modulecommon.util.RandomUtil;
import com.mettre.modulefriend.pojoVM.NewsVM;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

@Data
public class News {

    private String newsId;

    @NotBlank(message = "请输入新闻标题")
    private String newsTitle;

    @NotBlank(message = "作者不能为空")
    private String publisherUserId;

    private Date creationTime;

    private Date updateTime;

    private Boolean isShow;

    @NotBlank(message = "请输入新闻链接")
    private String newsLink;

    private Integer readNum;

    private String newsImage;

    private Integer commentNum;

    private Long category;

    private String categoryName;

    private String userName;//昵称

    private String headAvatar;//头像


    public News() {

    }

    //新增新闻
    public News(NewsVM newsVM) {
        this.newsId = RandomUtil.generateUserId();
        this.newsTitle = newsVM.getNewsTitle();
        this.publisherUserId = newsVM.getPublisherUserId();
        this.creationTime = new Date();
        this.updateTime = new Date();
        this.isShow = true;
        this.newsLink = newsVM.getNewsLink();
        this.readNum = 0;
        this.newsImage = newsVM.getNewsImage();
        this.category = newsVM.getCategory();
        this.commentNum = 0;
    }

    public News(String newsId, String newsTitle, String publisherUserId, Date creationTime, Date updateTime, Boolean isShow, String newsLink, Integer readNum, String newsImage, Integer commentNum, Long category) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.publisherUserId = publisherUserId;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
        this.isShow = isShow;
        this.newsLink = newsLink;
        this.category = category;
        this.readNum = readNum;
        this.newsImage = newsImage;
        this.commentNum = commentNum;
    }
}