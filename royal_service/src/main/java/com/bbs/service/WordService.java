package com.bbs.service;

import com.bbs.domain.Word;

import java.util.List;

public interface WordService {
    List<Word> findByPage(int pageNum, int pageSize);

    List<String> findByWordStatus();

    void addWord(String word) throws Exception;

    void changeStatus(Integer wordId, Integer status);
}
