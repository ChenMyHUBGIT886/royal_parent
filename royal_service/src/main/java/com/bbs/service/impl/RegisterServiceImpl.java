package com.bbs.service.impl;

import com.bbs.dao.RegisterDao;
import com.bbs.domain.UserInfo;
import com.bbs.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterDao registerDao;

    @Override
    public UserInfo save(UserInfo userInfo) {
        registerDao.save(userInfo);
        UserInfo userInfo1 = registerDao.findByName(userInfo.getUserName());
        return userInfo1;
    }

    @Override
    public UserInfo findByName(String userName) {
        return registerDao.findByName(userName);
    }

}
