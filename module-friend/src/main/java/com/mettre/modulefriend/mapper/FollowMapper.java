package com.mettre.modulefriend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulefriend.pojo.FindUser;
import com.mettre.modulefriend.pojo.Follow;
import com.mettre.modulefriend.pojo.Friends;
import com.mettre.modulefriend.pojo.RecommendBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowMapper {

    int deleteByPrimaryKey(Long followId);

    int insert(Follow record);

    int insertRecommended(RecommendBean record);

    int insertSelective(Follow record);

    Follow selectByPrimaryKey(Long followId);

    int updateByPrimaryKeySelective(Follow record);

    int updateByPrimaryKey(Follow record);

    int cancelFollow(Follow record);

    int addFollow(Follow record);

    Follow findWhetherFollow(Follow record);

    List<Follow> myFollowPageVo(Page<Follow> page, @Param(value = "userId") String userId);

    List<Follow> myFansPageVo(Page<Follow> page, @Param(value = "userId") String userId);

    List<Follow> myFriendsPageVo(Page<Follow> page, @Param(value = "userId") String userId);

    List<Friends> myFriendsList(@Param(value = "userId") String userId);

    List<RecommendBean> recommendedList(@Param(value = "userId") String userId);

    List<FindUser> findUserList(@Param(value = "userId") String userId, @Param(value = "findUserId") String findUserId);
}