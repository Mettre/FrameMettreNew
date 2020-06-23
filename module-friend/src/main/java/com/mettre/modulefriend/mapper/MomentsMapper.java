package com.mettre.modulefriend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulefriend.pojo.Moments;
import com.mettre.modulefriend.pojo.MomentsDetails;
import com.mettre.modulefriend.pojo.MomentsParameter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MomentsMapper {

    int deleteByPrimaryKey(String momentsId);

    int insert(Moments record);

    int insertSelective(Moments record);

    MomentsParameter selectByPrimaryKey(String momentsId);

    int updateByPrimaryKeySelective(Moments record);

    int updateByPrimaryKey(Moments record);

    MomentsDetails selectMomentsDetails(String momentsId);

    List<MomentsParameter> selectPageVo(Page<MomentsParameter> page, @Param(value = "publisherUserId") String publisherUserId);

    List<MomentsParameter> circleFriendsPageVo(Page<MomentsParameter> page, @Param(value = "userId") String userId);
}