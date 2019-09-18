package com.bbs.controller;

import com.bbs.domain.UserInfo;
import com.bbs.service.RegisterService;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.SystemColor.info;

@Controller
@RequestMapping("/register")
public class RegisterController {
    /**
     * 添加新用户
     *
     * @param userInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/register.do")
    public Map<String,Integer> save(UserInfo userInfo, HttpServletRequest request) {
        HashMap<String, Integer> map = new HashMap<>();
        if (userInfo.getUserName().isEmpty() || userInfo.getUserPass().isEmpty()){
            map.put("msg",1);
            return map;
        }
        UserInfo userInfo1 = registerService.save(userInfo);
        request.getSession().setAttribute("user",userInfo1);
        map.put("msg",0);
        return map;
    }

    @Autowired
    private RegisterService registerService;

    @RequestMapping("/login.do")
    public ModelAndView login(List<UserInfo> list) {
        ModelAndView mv = new ModelAndView();
        return mv;
    }

    @RequestMapping("/query.do")
    @ResponseBody
    public String testResponseJson(@RequestParam("userName") String info) {
       System.out.println(info);
        if (info != null && info.length() >= 3) {
            UserInfo byName = registerService.findByName(info);
            if (byName != null) {
                if (info.equals(byName.getUserName())) {
                    return "{\"msg\":\"0\"}";
                }
            }
            return "{\"msg\":\"1\"}";
        }
        return "{\"msg\":\"0\"}";
    }
}