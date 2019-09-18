package com.bbs.service;

import com.bbs.domain.UserInfo;

public interface RegisterService {

    UserInfo save(UserInfo userInfo);

    UserInfo findByName(String userName);
}
