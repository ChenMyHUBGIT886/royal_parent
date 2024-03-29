package com.bbs.service.impl;



import com.bbs.dao.UserInfoDao;
import com.bbs.domain.UserInfo;
import com.bbs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
   @Autowired
   private UserInfoDao userInfoDao;

    @Override
    public void update(UserInfo userInfo) {
        userInfoDao.update(userInfo);
    }
}
