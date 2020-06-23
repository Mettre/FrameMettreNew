package com.mettre.modulefriend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulefriend.pojo.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface NewsMapper {
    int deleteByPrimaryKey(String newsId);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(String newsId);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<News> selectPageVo(Page<News> page, @Param(value = "categoryId") Long categoryId);
}