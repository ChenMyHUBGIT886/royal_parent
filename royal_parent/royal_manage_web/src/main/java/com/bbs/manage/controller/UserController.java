package com.bbs.manage.controller;

import com.bbs.domain.UserInfo;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    public ModelAndView login(@RequestParam("userName") String userName , @RequestParam("userPass") String userPass) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPass(userPass);
        UserInfo resultUser = userService.managerLogin(userInfo);
        ModelAndView mv = new ModelAndView();
        if ((userName != null && userName.length()>0) &&(userPass !=null && userPass.length()>0) ){
            if (userName.equals(resultUser.getUserName())&&userPass.equals(resultUser.getUserPass())) {
                mv.addObject("user",resultUser);
                mv.setViewName("main");
            }else {
                mv.addObject("msg","账号或密码错误");
                mv.setViewName("login");
            }
        }
        return mv;
    }
}
