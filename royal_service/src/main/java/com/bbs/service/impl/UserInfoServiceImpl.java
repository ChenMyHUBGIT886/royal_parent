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

    @Override
    public UserInfo findById(String userId) {
       UserInfo userInfo=userInfoDao.findById(userId);

        return userInfo;
    }

    @Override
    public void updatePsw(String userId,String newPassword) {
        userInfoDao.updatePsw(userId,newPassword);
    }

    @Override
    public void apply(String userId) {
        userInfoDao.apply(userId);
    }
}
