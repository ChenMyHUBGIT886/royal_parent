package com.bbs.service.impl;

import com.bbs.dao.WordDao;
import com.bbs.domain.Word;
import com.bbs.service.WordService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordDao wordDao;

    /**
     * 查询所有敏感词
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<Word> findByPage(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        return wordDao.findAll();
    }

    /**
     * 添加敏感词
     *
     * @param word
     */
    @Override
    public void addWord(String word) throws Exception {
        if (word != "") {
            wordDao.addWord(word);
        }
    }

    @Override
    public List<String> findByWordStatus() {
        return wordDao.findByWordStatus();
    }

    /**
     * 敏感词启用和禁用
     *
     * @param wordId
     * @param status
     */
    @Override
    public void changeStatus(Integer wordId, Integer status) {
        wordDao.changeStatus(wordId, status);
    }
}
