package com.mettre.modulefriend.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulecommon.base.ReturnType;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulefriend.feign.UserRpcService;
import com.mettre.modulefriend.mapper.FollowMapper;
import com.mettre.modulefriend.pojo.FindUser;
import com.mettre.modulefriend.pojo.Follow;
import com.mettre.modulefriend.pojo.Friends;
import com.mettre.modulefriend.pojoVM.FollowVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FollowService {

    @Autowired
    public FollowMapper followMapper;

    @Autowired
    public UserRpcService userRpcFeign;

    public int insert(FollowVM followVM) {
        String userId = SecurityContextStore.getContext().getUserId();
        int type = 0;
        Follow follow = followMapper.findWhetherFollow(new Follow(followVM, userId));
        if (follow != null) {
            if (follow.getStatus()) {
                throw new CustomerException("已经是关注关系");
            }
            type = followMapper.addFollow(new Follow(followVM, userId));
        } else {
            userRpcFeign.findUserInfo(userId);
            userRpcFeign.findUserInfo(followVM.getFollowedUser());
            type = followMapper.insert(new Follow(followVM, userId));
        }

        return ReturnType.ReturnType(type, "关注失败");

    }

    public int cancelFollow(FollowVM followVM) {
        String userId = SecurityContextStore.getContext().getUserId();
        Follow follow = followMapper.findWhetherFollow(new Follow(followVM, userId));
        if (follow == null) {
            throw new CustomerException("取消关注失败");
        }
        int type = followMapper.cancelFollow(new Follow(followVM, userId));
        return ReturnType.ReturnType(type, "取消关注失败");
    }

    public Follow findWhetherFollow(FollowVM followVM) {
        String userId = SecurityContextStore.getContext().getUserId();
        return followMapper.findWhetherFollow(new Follow(followVM, userId));
    }

    public Page<Follow> myFollowPageVo(Page<Follow> page, String userId) {
        List<Follow> followList = (List<Follow>) followMapper.myFollowPageVo(page, userId);
        if (followList != null && followList.size() > 0) {
            for (Follow follow : followList) {
                follow.setEachOther(StrUtil.isNotBlank(follow.getUserId2()));
            }
        }
        page = page.setRecords(followList);
        return page;
    }

    public Page<Follow> myFansPageVo(Page<Follow> page, String userId) {
        List<Follow> followList = (List<Follow>) followMapper.myFansPageVo(page, userId);
        if (followList != null && followList.size() > 0) {
            for (Follow follow : followList) {
                follow.setEachOther(StrUtil.isNotBlank(follow.getUserId2()));
            }
        }
        page = page.setRecords(followList);
        return page;
    }

    public Page<Follow> myFriendsPageVo(Page<Follow> page, String userId) {
        List<Follow> followList = (List<Follow>) followMapper.myFriendsPageVo(page, userId);
        page = page.setRecords(followList);
        return page;
    }

    public List<Friends> myFriendsList(String userId) {
        List<Friends> followList = (List<Friends>) followMapper.myFriendsList(userId);
        return followList;
    }

    public List<FindUser> findUserList(String findUserId) {
        String userId = SecurityContextStore.getContext().getUserId();
        List<FindUser> followList = (List<FindUser>) followMapper.findUserList(userId, findUserId);
        return followList;
    }

}
