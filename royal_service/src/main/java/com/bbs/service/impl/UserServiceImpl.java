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
    public void changeRole(Integer id, Integer roles) throws Exception {
//        Integer roles =null;
//        if (role==0){
//            roles=1;
//        }else if (role==1){
//            roles=1;
//        }


        userDao.changeRole(id, roles);
        userDao.isupdating(id,0);

    }

    @Override
    public void downgradeRole(Integer id, Integer roles) throws Exception {

        userDao.downgradeRole(id, roles);
    }

    @Override
    public void changeTalkStatus(Integer id, Integer talkStatus) throws Exception {
        userDao.changeTalkStatus(id,talkStatus);
    }

    @Override
    public UserInfo login(UserInfo userInfo) {
        return userDao.login(userInfo);
    }

    @Override
    public void loginStatus() {
        userDao.loginStatus();
    }

    @Override
    public void logoutStatus(Integer userId) {
        userDao.logoutStatus(userId);
    }

    @Override
    public List<UserInfo> findAllLoginStatus() {
        return userDao.findAllLoginStatus();
    }

    @Override
    public List<UserInfo> userSearchForm(UserInfo userInfo, int page, int size) throws Exception {
        String userName = userInfo.getUserName();
        Integer role = userInfo.getRole();
        userInfo.setUserName(userName.trim());
        userInfo.setRole(role);
        PageHelper.startPage(page,size);
        return userDao.userSearchForm(userName,role);
    }
   //驳回
    @Override
    public void isupdating(Integer id, Integer isupdating) throws Exception{
        userDao.isupdating(id,isupdating);
    }

    @Override
    public boolean findTalkStatusByName(String senderName) {
        Integer i = userDao.findTalkStatusByName(senderName);
//        0代表未屏蔽发言（默认），1代表已屏蔽发言'
        if (i == 0){
            return true;
        }
        if (i == 1){
            return false;
        }
        return false;
    }
}
