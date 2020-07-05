package com.mettre.modulefriend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulecommon.base.ReturnType;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulefriend.feign.UserRpcService;
import com.mettre.modulefriend.mapper.MomentsMapper;
import com.mettre.modulefriend.pojo.Moments;
import com.mettre.modulefriend.pojo.MomentsDetails;
import com.mettre.modulefriend.pojo.MomentsParameter;
import com.mettre.modulefriend.pojoVM.MomentsVM;
import com.mettre.modulefriend.pojoVM.VisitorRpcVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//import com.mettre.feign.UserClient;

@Service
@Transactional
public class MomentsService {

    @Autowired
    public UserRpcService userFeign;

    @Autowired
    public MomentsMapper momentsMapper;


    public int insert(MomentsVM momentsVM) {
        String userId = SecurityContextStore.getContext().getUserId();
        ReturnType.ReturnType(userFeign.findUserInfo(userId));
        return momentsMapper.insert(new Moments(momentsVM, userId));
    }

    public MomentsParameter selectByPrimaryKey(String momentsId) {
        MomentsParameter momentsParameter = momentsMapper.selectByPrimaryKey(momentsId);
        if (momentsParameter == null) {
            throw new CustomerException("该条动态不存在");
        }
        return momentsParameter;
    }

    public Page<MomentsParameter> selectPageVo(Page<MomentsParameter> page, String publisherUserId) {
        String userId = SecurityContextStore.getContext().getUserId();
        if (!userId.equals(publisherUserId)) {
            VisitorRpcVM visitorVM = new VisitorRpcVM();
            visitorVM.setUserId(userId);
            visitorVM.setVisitorsUser(publisherUserId);
        }
        List<MomentsParameter> momentsList = (List<MomentsParameter>) momentsMapper.selectPageVo(page, publisherUserId);
        page = page.setRecords(momentsList);
        return page;
    }


    public Page<MomentsParameter> circleFriendsPageVo(Page<MomentsParameter> page, String userId) {
        List<MomentsParameter> momentsList = (List<MomentsParameter>) momentsMapper.circleFriendsPageVo(page, userId);
        page = page.setRecords(momentsList);
        return page;
    }

    public MomentsDetails selectMomentsDetails(String momentsId) {
        return momentsMapper.selectMomentsDetails(momentsId);
    }
}
