package com.bbs.dao;

import com.bbs.domain.UserInfo;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Select("select * from bbs_user_table where userName = #{userName}")
    UserInfo login(UserInfo userInfo);
}
