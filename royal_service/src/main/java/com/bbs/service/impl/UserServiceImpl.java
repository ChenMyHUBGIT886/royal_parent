package com.bbs.service.impl;

import com.bbs.dao.UserDao;
import com.bbs.domain.UserInfo;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public UserInfo managerLogin(UserInfo userInfo) {
        UserInfo resultUser = userDao.login(userInfo);
        return resultUser;
    }

    @Override
    public UserInfo login(UserInfo userInfo) {
        return userDao.login(userInfo);
    }
}
