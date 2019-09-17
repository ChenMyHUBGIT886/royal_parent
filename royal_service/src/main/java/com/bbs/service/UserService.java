package com.bbs.service;

import com.bbs.domain.UserInfo;

public interface UserService {
    UserInfo managerLogin(UserInfo userInfo);

    UserInfo login(UserInfo userInfo);
}
