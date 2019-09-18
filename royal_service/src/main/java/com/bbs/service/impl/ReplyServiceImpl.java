package com.bbs.service.impl;

import com.bbs.dao.ReplyDao;
import com.bbs.domain.Reply;
import com.bbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;

    @Override
    public void addReply(Reply reply) {
        replyDao.addReply(reply);
    }
}
