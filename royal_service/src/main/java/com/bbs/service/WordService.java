package com.bbs.service;

import com.bbs.domain.Word;

import java.util.List;

public interface WordService {
    List<Word> findByPage(int pageNum, int pageSize) throws Exception;

    void addWord(String word) throws Exception;

    void changeStatus(Integer wordId, Integer status);
}
