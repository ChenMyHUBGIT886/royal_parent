package com.bbs.service.impl;

import com.bbs.dao.UserDao;
import com.bbs.domain.UserInfo;
import com.bbs.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<UserInfo> findByPage(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return userDao.findByPage();
    }

    @Override
    public void changeRole(Integer id, Integer role) throws Exception {
//        Integer roles =null;
//        if (role==0){
//            roles=1;
//        }else if (role==1){
//            roles=1;
//        }


        userDao.changeRole(id, role);
    }

    @Override
    public void changeTalkStatus(Integer id, Integer talkStatus) throws Exception {
        userDao.changeTalkStatus(id,talkStatus);
    }

    @Override
    public UserInfo login(UserInfo userInfo) {
        return userDao.login(userInfo);
    }
}
