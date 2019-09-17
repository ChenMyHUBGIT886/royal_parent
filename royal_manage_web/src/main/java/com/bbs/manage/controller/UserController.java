package com.bbs.manage.controller;

import com.bbs.domain.UserInfo;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    public String login(@RequestParam("userName") String userName, @RequestParam("userPass") String userPass) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPass(userPass);
//        ModelAndView mv = new ModelAndView();
        if ((userName != null && userName.length() > 0) && (userPass != null && userPass.length() > 0)) {
            UserInfo resultUser = userService.managerLogin(userInfo);

            if (resultUser != null) {
                if (userName.equals(resultUser.getUserName()) && userPass.equals(resultUser.getUserPass())) {
    //                mv.addObject("msg","true");
    //                mv.setViewName("login");
                    return "{\"msg\":\"0\"}";
                } else if (resultUser.getRole() != 3) {
    //                mv.addObject("msg","权限不足");
    //                mv.setViewName("login");
                    return "{\"msg\":\"1\"}";
                } else {
    //                mv.addObject("msg","账号或密码错误");
    //                mv.setViewName("login");
                    return "{\"msg\":\"2\"}";
                }
            }
            return "{\"msg\":\"2\"}";
        }
        return null;
    }
}
