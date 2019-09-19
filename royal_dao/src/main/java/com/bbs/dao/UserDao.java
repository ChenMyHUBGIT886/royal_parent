package com.bbs.dao;

import com.bbs.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {
    @Update("update bbs_user_table set role=#{role}+1  where userId=#{id} ")
      void changeRole(@Param("id") Integer id, @Param("role") Integer roles) ;

    @Update("update bbs_user_table set role=#{role}-1  where userId=#{id} ")
    void downgradeRole(@Param("id") Integer id, @Param("role") Integer roles) ;



    @Select("select * from bbs_user_table where userName = #{userName}")
    UserInfo login(UserInfo userInfo);

    @Select("select * from bbs_user_table")
    List<UserInfo> findByPage();
    @Update("update bbs_user_table set talkStatus=#{talkStatus}  where userId=#{id} ")
    void changeTalkStatus(@Param("id")Integer id,  @Param("talkStatus")Integer talkStatus);
    //驳回
    @Update("update bbs_user_table set isupdating=#{isupdating}  where userId=#{id} ")
    void isupdating(@Param("id")Integer id, @Param("isupdating")Integer isupdating);

    @Update("update bbs_user_table set loginStatus = 1")
    void loginStatus();


//    @Results({
//            @Result(id = true,property = "userId",column = "userId"),
//            @Result(property = "userName",column = "userName"),
//            @Result(property = "userPass",column = "userPass"),
//            @Result(property = "email",column = "email"),
//            @Result(property = "picUrl",column = "picUrl"),
//            @Result(property = "role",column = "role"),
//            @Result(property = "lastLoginTime",column = "lastLoginTime"),
//            @Result(property = "loginStatus",column = "loginStatus"),
//            @Result(property = "talkStatus",column = "talkStatus"),
//            @Result(property = "isupdating",column = "isupdating"),
//            @Result(property = "loginStatus",column = "loginStatus"),
//            @Result(property = "updateStatus",column = "updateStatus")
//    })
//    @Select("select * from bbs_user_table where  userName like concat('%',#{userName},'%') and role like concat('%',#{role},'%') ")
    @Select("<script>"+"select * from bbs_user_table where  userName like concat('%',#{userName},'%')"
            +"<if test='role!=0 and role!=null'> and role=#{role}</if>"
            +"</script>")
    List<UserInfo> userSearchForm(@Param("userName")String userName,@Param("role") Integer role);


    @Update("update bbs_user_table set loginStatus = 0 where userId = #{userId}")
    void logoutStatus(Integer userId);

    @Select("select * from bbs_user_table where loginStatus = 1")
    List<UserInfo> findAllLoginStatus();

    @Select("select talkStatus from bbs_user_table where userName = #{senderName}")
    Integer findTalkStatusByName(String senderName);
}
