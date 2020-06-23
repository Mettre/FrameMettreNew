package com.mettre.modulefriend.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulecommon.enum_.ResultEnum;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.enums.DynamicTypeEnum;
import com.mettre.modulefriend.mapper.NewsMapper;
import com.mettre.modulefriend.pojo.News;
import com.mettre.modulefriend.pojoVM.NewsVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsService {

    @Autowired
    public NewsMapper newsMapper;


    public int insert(NewsVM newsVM) {
        News news = new News(newsVM);
        return newsMapper.insert(news);
    }

    public News selectByPrimaryKey(String newsId, String readerId, String readerEquipment) {
        if (StrUtil.isBlank(newsId)) {
            throw new CustomerException("新闻id为空");
        }
        News news = newsMapper.selectByPrimaryKey(newsId);
        if (news == null) {
            throw new CustomerException("新闻不存在");
        }
//        if (StrUtil.isBlank(readerId) && StrUtil.isBlank(readerEquipment)) {
//
//        } else {
//            addReadNum(newsId, readerId, readerEquipment);
//        }
        return news;
    }

    //    //新增阅读
//    public void addReadNum(String dynamicId, String readerId, String readerEquipment) {
//        Read read = null;
//        if (StrUtil.isBlank(readerId) && StrUtil.isBlank(readerEquipment)) {
//            return;
//        } else if (StrUtil.isNotBlank(readerId) && StrUtil.isNotBlank(readerEquipment)) {
//            read = readMapper.selectByReaderIdOrEquipment(dynamicId, readerId, null);
//        } else {
//            read = readMapper.selectByReaderIdOrEquipment(dynamicId, readerId, readerEquipment);
//        }
//        if (read == null) {
//            readMapper.insert(new Read(dynamicId, DynamicTypeEnum.NEWS, readerId, readerEquipment));
//        }
//    }
//
    public Page<News> selectPageVo(Page<News> page, Long categoryId) {
        List<News> addressList = (List<News>) newsMapper.selectPageVo(page, categoryId);
        page = page.setRecords(addressList);
        return page;
    }
}
