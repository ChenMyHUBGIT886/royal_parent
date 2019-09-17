package com.bbs.dao;

import com.bbs.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {
    @Update("update bbs_user_table set role=#{role}+1  where userId=#{id} ")
      void changeRole(@Param("id") Integer id, @Param("role") Integer role) ;

    @Select("select * from bbs_user_table where userName = #{userName}")
    UserInfo login(UserInfo userInfo);

    @Select("select * from bbs_user_table")
    List<UserInfo> findByPage();
    @Update("update bbs_user_table set talkStatus=#{talkStatus}  where userId=#{id} ")
    void changeTalkStatus(@Param("id")Integer id,  @Param("talkStatus")Integer talkStatus);
}
