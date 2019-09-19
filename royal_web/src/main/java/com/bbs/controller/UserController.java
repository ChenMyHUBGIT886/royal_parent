package com.bbs.controller;

import com.bbs.domain.UserInfo;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    public String login(HttpServletRequest request, @RequestParam("userName") String userName, @RequestParam("userPass") String userPass){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPass(userPass);
        if ((userName != null && userName.length() > 0) && (userPass != null && userPass.length() > 0)) {
            UserInfo resultUser = userService.login(userInfo);
            if (resultUser != null){
                if (userName.equals(resultUser.getUserName()) && userPass.equals(resultUser.getUserPass())) {
                    userService.loginStatus();
                    request.getSession().setAttribute("user",resultUser);
                    return "{\"msg\":\"0\"}";
                } else {
                    return "{\"msg\":\"1\"}";
                }
            }
            return "{\"msg\":\"1\"}";
        }
        return null;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        request.getSession().removeAttribute("user");
        userService.logoutStatus(user.getUserId());
        return "redirect:/zone/findAll.do";
    }
}
