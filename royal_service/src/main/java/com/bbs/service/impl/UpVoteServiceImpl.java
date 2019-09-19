package com.bbs.service.impl;

import com.bbs.dao.UpVoteDao;
import com.bbs.domain.UpVote;
import com.bbs.service.UpVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("upVoteService")
public class UpVoteServiceImpl implements UpVoteService {
    @Autowired
    private UpVoteDao upVoteDao;

    @Override
    public void saveByBean(UpVote upVote) {
        upVoteDao.saveByBean(upVote);
    }

    @Override
    public void changeIsUpvote(UpVote upVote) {
        upVoteDao.changeIsUpvote(upVote);
    }

    @Override
    public Integer getUpVoteCount(Integer upvoteArticleId) {
        return upVoteDao.getUpVoteCount(upvoteArticleId);
    }
}
