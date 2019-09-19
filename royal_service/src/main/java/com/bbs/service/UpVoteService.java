package com.bbs.service;

import com.bbs.domain.UpVote;

public interface UpVoteService {

    void saveByBean(UpVote upVote);

    void changeIsUpvote(UpVote upVote);

    Integer getUpVoteCount(Integer upvoteArticleId);
}
