package com.bbs.dao;

import com.bbs.domain.UpVote;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UpVoteDao {

    @Insert("insert into bbs_upvote_table values(#{upvoteUserName},#{upvoteArticleId},#{isUpvote})")
    void saveByBean(UpVote upVote);

    //根据用户名和帖子编号改变点赞的状态值
    @Update("update bbs_upvote_table set isupvote = #{isUpvote} where upvoteusername = #{upvoteUserName} and upvotearticleid = #{upvoteArticleId}")
    void changeIsUpvote(UpVote upVote);

    @Select("select sum(isUpvote) from bbs_upvote_table where upvoteArticleId = #{articleId}")
    Integer getUpVoteCount(Integer upvoteArticleId);

    @Select("select isUpvote from bbs_upvote_table where upvoteArticleId = #{upvoteArticleId} and upvoteUserName = #{upvoteUserName}")
    Integer findByIdAndName(@Param(value = "upvoteArticleId")Integer upvoteArticleId, @Param(value = "upvoteUserName")String upvoteUserName);
}
