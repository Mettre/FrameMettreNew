package com.mettre.moduleclientb.mapper;

import com.mettre.moduleclientb.pojo.File;
import org.springframework.stereotype.Component;

@Component
public interface FileMapper {

    int deleteByPrimaryKey(Long fileId);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(Long fileId);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
}