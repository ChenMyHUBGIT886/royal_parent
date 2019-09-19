package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.UserInfo;
import com.bbs.domain.Zone;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface ArticleDao {

    @Select("select * from bbs_article_table")
    @Results(id = "map", value = {
            @Result(id = true, column = "articleId", property = "articleId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "sendTime", property = "sendTime"),
            @Result(column = "senderName", property = "senderName"),
            @Result(column = "isTop", property = "isTop"),
            @Result(column = "replyCount", property = "replyCount"),
            @Result(column = "upvoteCount", property = "upvoteCount"),
            @Result(column = "browseCount", property = "browseCount"),
            @Result(column = "isReport", property = "isReport"),
            @Result(column = "zoneId", property = "zone",one = @One(select = "com.bbs.dao.ZoneDao.findById"))
    })
    List<Article> findAll();

    @Select("select * from bbs_article_table where zoneId=#{id} and isReport = 0 order by isTop desc")
    List<Article> findByZoneId(Integer id);

    @Select("select * from bbs_article_table where articleId = #{articleId}")
    @Results({
            @Result(id = true, property = "articleId", column = "articleId"),
            @Result(property = "userInfo", column = "senderName", javaType = UserInfo.class,
                    one = @One(select = "com.bbs.dao.UserInfoDao.findByUserName")
            ),
            @Result(property = "comments", column = "articleId", many = @Many(
                    select = "com.bbs.dao.CommentDao.findAllByArticleId"
            ))
    })
    Article getArticle(Integer articleId);

    @Select("select * from bbs_article_table where articleId = #{articleId}")
    @Results({
            @Result(id = true, property = "articleId", column = "articleId"),
            @Result(property = "userInfo", column = "senderName", javaType = UserInfo.class,
                    one = @One(select = "com.bbs.dao.UserInfoDao.findByUserName")
            ),
            @Result(property = "comments", column = "articleId", many = @Many(
                    select = "com.bbs.dao.CommentDao.findAllByArticleIdDesc"
            ))
    })
    Article getArticleDesc(Integer articleId);

    @Select("select * from bbs_article_table where zoneId=#{id}")
    List<Article> findByZoneid(Integer id);

    @Insert("insert into bbs_article_table(title,content,sendTime,senderName,isTop,replyCount,upvoteCount,browseCount,zoneId,isReport) " +
            "values(#{title},#{content},null,#{senderName},0,0,0,0,#{zoneId},0)")
    @SelectKey(keyProperty = "articleId", keyColumn = "articleId", before = false, resultType = int.class, statement = "select last_insert_id()")
    void save(Article article);

    @Select("select * from bbs_article_table where articleId=#{articleId}")
    Article findById(Integer articleId);

    //查询所有帖子（后台）
    @Select("select * from bbs_article_table order by isTop desc")
    @Results({
            @Result(id = true, property = "articleId", column = "articleId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "sendTime", property = "sendTime"),
            @Result(column = "senderName", property = "senderName"),
            @Result(column = "isTop", property = "isTop"),
            @Result(column = "replyCount", property = "replyCount"),
            @Result(column = "upvoteCount", property = "upvoteCount"),
            @Result(column = "browseCount", property = "browseCount"),
            @Result(column = "isReport", property = "isReport"),
            @Result(column = "zoneId", property = "zone", one = @One(select = "com.bbs.dao.ZoneDao.findByZoneId"))
    })
    List<Article> findAllManager() throws Exception;

    //后台帖子置顶
    @Update("update bbs_article_table set isTop = #{isTop} where articleId = #{articleId}")
    void changeStatus(@Param("articleId") Integer articleId, @Param("isTop") Integer isTop) throws Exception;

    //后台根据Id删除帖子
    @Delete("delete from bbs_article_table where articleId = #{articleId}")
    void deleteArticle(Integer articleId) throws Exception;

    //后台根据Id查询帖子
    @Select("select * from bbs_article_table where articleId = #{articleId}")
    Article findByIdManager(Integer articleId) throws Exception;

    @Select("select * from bbs_article_table where title like #{title} order by isTop desc")
    List<Article> findLikeTitle(String title);

    @Select("select * from bbs_article_table where articleId=#{articleId}")
    @Results({
            @Result(property = "userInfo", column = "senderName", one = @One(
                    select = "com.bbs.dao.UserInfoDao.findByUserName"
            ))
    })
    Article findLikeComment(@Param("articleId") Integer articleId);


    @Update("update bbs_article_table set replyCount = #{replyCount+1}")
    void addReplyCount(Integer replyCount);

    @Select("select * from bbs_article_table where articleId = #{articleId}")
    Article findReplyCount(Integer articleId);


    //模糊查询
    /*条件查询*/
//    @Select("<script>"+"select * from bbs_article_table where and title like concat('%',#{title},'%')"
////            + " <if test='title != 0 and title != null'> </if> "
//            +"<if test='senderName != 0 and senderName != null'> and senderName like concat('%',#{senderName},'%')</if> "
//            +"</script>")

    @Select("<script>select * from bbs_article_table where 1=1 <if test=\"title !=null \">and title like '%${title}%' </if> <if test=\"senderName !=null \">and senderName like '%${senderName}%' </if></script>")
    @Results({
            @Result(id = true, column = "articleId", property = "articleId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "sendTime", property = "sendTime"),
            @Result(column = "senderName", property = "senderName"),
            @Result(column = "isTop", property = "isTop"),
            @Result(column = "replyCount", property = "replyCount"),
            @Result(column = "upvoteCount", property = "upvoteCount"),
            @Result(column = "browseCount", property = "browseCount"),
            @Result(column = "isReport", property = "isReport"),
            @Result(column = "zoneId", property = "zone",one = @One(select = "com.bbs.dao.ZoneDao.findById"))
    })
    List<Article> findByCondition(@Param("title") String title,@Param("senderName") String senderName);
}
