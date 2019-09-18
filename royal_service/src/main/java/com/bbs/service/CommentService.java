package com.bbs.service;

import com.bbs.domain.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment);

    List<Comment> findLikeComment(String comment);
}
