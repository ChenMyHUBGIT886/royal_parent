package com.bbs.dao;

import com.bbs.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface UserInfoDao {
   @Update("update bbs_user_table set picUrl=#{picUrl},email=#{email} where userId=#{userId}")
    void update(UserInfo userInfo);

    @Select("select * from bbs_user_table where userName = #{userName}")
    UserInfo findByUserName(String userName);
    //查询数据
    @Select("select * from bbs_user_table where userId=#{userId}")
    UserInfo findById(String userId);
    //更新数据
    @Update("update bbs_user_table set userPass=#{newPassword} where userId=#{userId}")
    void updatePsw(@Param(value = "userId") String userId,@Param(value = "newPassword") String newPassword);
    //用户升级
 @Update("update bbs_user_table set role=2 where userId=#{userId}")
 void apply(String userId);
}

