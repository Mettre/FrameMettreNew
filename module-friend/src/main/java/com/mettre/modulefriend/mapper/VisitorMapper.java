package com.mettre.modulefriend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulefriend.pojo.Visitor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VisitorMapper {

    int deleteByPrimaryKey(Long visitorId);

    int insert(Visitor record);

    int insertSelective(Visitor record);

    Visitor selectByPrimaryKey(Long visitorId);

    int updateByPrimaryKeySelective(Visitor record);

    int updateByPrimaryKey(Visitor record);

    List<Visitor> myVisitorPageVo(Page<Visitor> page, @Param(value = "visitorsUser") String visitorsUser);

    int deleteAllVisitor(@Param(value = "userId") String userId, @Param(value = "visitorsUser") String visitorsUser);

    int deleteAllVisitorFromVisitorId(Long visitorId);
}