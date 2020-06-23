package com.mettre.modulefriend.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulecommon.base.ReturnType;
import com.mettre.modulecommon.enum_.ResultEnum;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.enums.DynamicTypeEnum;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulefriend.mapper.ReplyMapper;
import com.mettre.modulefriend.pojo.Reply;
import com.mettre.modulefriend.pojoVM.ReplyVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mettre.modulecommon.enums.DynamicTypeEnum.NEWS;

@Service
@Transactional
public class ReplyService {

    private static final Logger logger = LoggerFactory.getLogger(ReplyService.class);


    @Autowired
    public ReplyMapper replyMapper;

    @Autowired
    public NewsService newsService;

    @Autowired
    public MomentsService momentsService;


    public int deleteByPrimaryKey(String replyId) {

        String userId = SecurityContextStore.getContext().getUserId();
        int type = replyMapper.deleteByPrimaryKey(replyId);
        return ReturnType.ReturnType(type, "删除失败");
    }

    public int deleteByReplyIdAndDynamicUserId(String replyId, String dynamicUserId) {
        Reply reply = replyMapper.selectByPrimaryKey(replyId);
        if (reply == null) {
            throw new CustomerException("该评论不存在或已被删除");
        }
        if (!dynamicUserId.equals(reply.getDynamicUserId())) {
            throw new CustomerException("暂无权限删除该评论");
        }
        int type = replyMapper.deleteByPrimaryKey(replyId);
        return ReturnType.ReturnType(type, "删除失败");
    }

    public int insert(ReplyVM replyVM) {
        String userId = SecurityContextStore.getContext().getUserId();
        if (!DynamicTypeEnum.contains(replyVM.getDynamicType().name())) {
            throw new CustomerException("资讯类型不能为空");
        }
        switch (replyVM.getDynamicType()) {
            case NEWS:
            case MOMENTS:
                if (replyVM.getDynamicType() == NEWS) {
                    newsService.selectByPrimaryKey(replyVM.getDynamicId(), null, null);
                } else {
                    momentsService.selectByPrimaryKey(replyVM.getDynamicId());
                }
                if (StrUtil.isNotBlank(replyVM.getReplyParentId())) {
                    Reply reply = selectByPrimaryKey(replyVM.getReplyParentId());

                    if (StrUtil.isNotBlank(reply.getSecondDynamicId())) {
                        return replyMapper.insert(new Reply(replyVM, reply.getReplyId(), reply.getDynamicUserId(), reply.getSecondDynamicId(), userId));
                    } else {
                        return replyMapper.insert(new Reply(replyVM, reply.getReplyId(), reply.getDynamicUserId(), reply.getReplyId(), userId));
                    }

                } else {
                    return replyMapper.insert(new Reply(replyVM, userId));
                }
            case FORUM:
                return replyMapper.insert(new Reply(replyVM, userId));

            default:
                return replyMapper.insert(new Reply(replyVM, userId));
        }
    }

    public Reply selectByPrimaryKey(String replyId) {
        Reply reply = replyMapper.selectByPrimaryKey(replyId);
        if (reply == null) {
            throw new CustomerException("该条评论不存在");
        }
        return reply;
    }

    public Page<Reply> selectPageVo(Page<Reply> page, String dynamicId, String userId) {
        List<Reply> addressList = (List<Reply>) replyMapper.selectPageVo(page, dynamicId, userId);
        page = page.setRecords(addressList);
        return page;
    }

    public List<Reply> selectMomentsReply(String dynamicId, String userId) {
        List<Reply> replyList = (List<Reply>) replyMapper.selectMomentsReply(dynamicId, userId);
        return replyList;
    }
}
