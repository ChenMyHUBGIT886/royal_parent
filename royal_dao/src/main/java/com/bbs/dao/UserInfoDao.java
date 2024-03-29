package com.bbs.dao;

import com.bbs.domain.UserInfo;
import org.apache.ibatis.annotations.Select;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface UserInfoDao {
   @Update("update bbs_user_table set picUrl=#{picUrl},email=#{email} where userId=#{userId}")
    void update(UserInfo userInfo);

    @Select("select * from bbs_user_table where userName = #{userName}")
    UserInfo findByUserName(String userName);
}

