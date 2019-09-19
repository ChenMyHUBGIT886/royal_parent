package com.bbs.dao;

import com.bbs.domain.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterDao {

    /**
     * 注册普通的新用户
     * @param userInfo
     */
    @Insert("INSERT INTO bbs_user_table VALUES(null,#{userName},#{userPass},#{email},'../images/default.png',1,null,1,0,0,0)")
    void save(UserInfo userInfo);

    @Select("select * from bbs_user_table where userName = #{userName}")
    UserInfo findByName(String string);
}
