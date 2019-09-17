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
    public void save(UserInfo userInfo) {
        registerDao.save(userInfo);
    }
}
