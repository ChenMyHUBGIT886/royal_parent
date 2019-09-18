package com.bbs.dao;

import com.bbs.domain.Word;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WordDao {

    @Select("select * from bbs_word_table order by status asc")
    List<Word> findAll();

    @Insert("insert into bbs_word_table(word,status) values(#{word},1)")
    void addWord(String word);
}
