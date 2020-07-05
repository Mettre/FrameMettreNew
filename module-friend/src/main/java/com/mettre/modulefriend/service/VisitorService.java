package com.mettre.modulefriend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulecommon.base.ReturnType;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulefriend.feign.UserRpcService;
import com.mettre.modulefriend.mapper.VisitorMapper;
import com.mettre.modulefriend.pojo.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VisitorService {

    @Autowired
    public VisitorMapper visitorMapper;

    @Autowired
    public UserRpcService userFeign;


    public int deleteByPrimaryKey(Long visitorId) {

        int type = visitorMapper.deleteByPrimaryKey(visitorId);
        return ReturnType.ReturnType(type, "访客记录删除失败");
    }

    public int insert(Visitor record) {
        int type = 0;
        if (!record.getUserId().equals(record.getVisitorsUser())) {
            ReturnType.ReturnType(userFeign.findUserInfo(record.getUserId()), userFeign.findUserInfo(record.getVisitorsUser()));
            type = visitorMapper.insert(record);
        }
        return ReturnType.ReturnType(type, "访客记录插入失败");
    }

    public Visitor selectByPrimaryKey(Long visitorId) {

        return (Visitor) ReturnType.ReturnType(visitorMapper.selectByPrimaryKey(visitorId), "访客记录插入失败");
    }

    public Page<Visitor> myVisitorPageVo(Page<Visitor> page, String visitorsUesr) {
        List<Visitor> visitorList = (List<Visitor>) visitorMapper.myVisitorPageVo(page, visitorsUesr);
        page = page.setRecords(visitorList);
        return page;
    }


    public int deleteAllVisitorFromVisitorId(Long visitorId) {
        String userId = SecurityContextStore.getContext().getUserId();
        Visitor visitor = selectByPrimaryKey(visitorId);
        if (!userId.equals(visitor.getVisitorsUser())) {
            throw new CustomerException("无权删除");
        }
        int type = visitorMapper.deleteAllVisitorFromVisitorId(visitorId);
        return ReturnType.ReturnType(type, "访客记录删除失败");
    }

    public int deleteAllVisitor(String userId, String visitorsUesr) {
        int type = visitorMapper.deleteAllVisitor(userId, visitorsUesr);
        return ReturnType.ReturnType(type, "访客记录删除失败");
    }
}
