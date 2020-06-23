package com.mettre.modulefriend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulefriend.pojo.Reply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 评论
 */
@Component
public interface ReplyMapper {
    int deleteByPrimaryKey(String replyId);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(String replyId);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);

    List<Reply> selectPageVo(Page<Reply> page, @Param(value = "dynamicId") String dynamicId, @Param(value = "userId") String userId);

    List<Reply> selectMomentsReply(@Param(value = "dynamicId") String dynamicId, @Param(value = "userId") String userId);

    List<Reply> selectSecondMoments(@Param(value = "secondDynamicId") String secondDynamicId, @Param(value = "userId") String userId);

}