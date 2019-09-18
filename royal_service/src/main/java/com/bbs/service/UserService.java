package com.bbs.service;

import com.bbs.domain.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserInfo managerLogin(UserInfo userInfo);

    List<UserInfo> findByPage(int page,int size)throws Exception;

    void changeRole(Integer id, Integer role) throws Exception;

    void changeTalkStatus(Integer id, Integer talkStatus) throws Exception;

    UserInfo login(UserInfo userInfo);

    void loginStatus();

    void logoutStatus();

    List<UserInfo> findAllLoginStatus();
}
