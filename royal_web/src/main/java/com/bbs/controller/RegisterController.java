package com.bbs.controller;

import com.bbs.domain.UserInfo;
import com.bbs.service.RegisterService;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    /**
     * 添加新用户
     *
     * @param userInfo
     * @return
     */
    @RequestMapping("/register.do")
    public String save(UserInfo userInfo) {
        registerService.save(userInfo);
        return "";
    }

    @RequestMapping("/login.do")
    public ModelAndView login(List<UserInfo> list) {
        ModelAndView mv = new ModelAndView();
        return mv;
    }

//  @RequestMapping("/query.do")
//    public @ResponseBody String testResponseJson(@String string) {
//        System.out.println(info);
//        UserInfo info = registerService.findByName(in.getUserName());
//        return info;
//    }

}
