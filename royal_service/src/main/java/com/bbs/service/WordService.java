package com.bbs.service;

import com.bbs.domain.Word;

import java.util.List;

public interface WordService {
    List<Word> findByPage(int pageNum, int pageSize);

    void addWord(String word);
}
