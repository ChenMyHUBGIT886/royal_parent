package com.bbs.service;

import com.bbs.domain.UserInfo;

public interface UserInfoService {
    void update(UserInfo userInfo);

    UserInfo findById(String userId);

    void updatePsw(String userId,String newPassword);

    void apply(String userId);
}
