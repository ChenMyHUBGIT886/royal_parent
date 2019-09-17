package com.bbs.dao;

import com.bbs.domain.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterDao {

    /**
     * 注册普通用户
     * @param userInfo
     */
   // INSERT  INTO `bbs_user_table`(`userName`,`userPass`,`email`,`picUrl`,`role`,`lastLoginTime`,`loginStatus`,`talkStatus`,`isupdating`,`updateStatus`) VALUES ('admin','admin','','uploads/e44478e07cc54fdb92daf9de48dfff78.png',3,'2019-06-24 00:40:30',0,0,0,0);

    @Insert("INSERT INTO `bbs_user_table`(`userName`,`userPass`,`email`,`picUrl`,`role`,`lastLoginTime`,`loginStatus`,`talkStatus`,`isupdating`,`updateStatus`) VALUES ('#{userName}','#{userPass}','#{email}','uploads/images/cat.png',1,'2019-06-24 00:40:30',0,0,0,0)")
    void save(UserInfo userInfo);
}
