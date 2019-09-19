package com.bbs.service;

import com.bbs.domain.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserInfo managerLogin(UserInfo userInfo);

    List<UserInfo> findByPage(int page,int size)throws Exception;
    //升级用户
    void changeRole(Integer id, Integer roles) throws Exception;
    //降级用户
    void downgradeRole(Integer id, Integer roles) throws Exception;
    //是否禁言
    void changeTalkStatus(Integer id, Integer talkStatus) throws Exception;

    UserInfo login(UserInfo userInfo);

    void loginStatus();

    void logoutStatus(Integer userId);

    List<UserInfo> findAllLoginStatus();


    List<UserInfo> userSearchForm(UserInfo userInfo, int page, int size) throws Exception;

    //申请审核 驳回
    void isupdating(Integer id, Integer isupdating) throws Exception;

    boolean findTalkStatusByName(String senderName);
}
