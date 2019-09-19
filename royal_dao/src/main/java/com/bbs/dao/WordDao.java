package com.bbs.dao;

import com.bbs.domain.Word;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface WordDao {
    //查询所有敏感词
    @Select("select * from bbs_word_table order by status asc")
    List<Word> findAll() throws Exception;

    //添加敏感词
    @Insert("insert into bbs_word_table(word,status) values(#{word},1)")
    void addWord(String word) throws Exception;

    //敏感词启用和禁用
    @Update("update bbs_word_table set status = #{status} where wordId = #{wordId}")
    void changeStatus(@Param("wordId") Integer wordId, @Param("status") Integer status);
    void addWord(String word);

    @Select("select word from bbs_word_table where status = 1")
    List<String> findByWordStatus();
}
