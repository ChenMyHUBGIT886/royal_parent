package com.bbs.manage.controller;

import com.bbs.domain.UserInfo;
import com.bbs.service.UserService;
import com.github.pagehelper.PageInfo;
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


//    @RequestMapping("/findByPage.do")
//    public ModelAndView findByPage() throws Exception {
//        ModelAndView mv = new ModelAndView();
//        List<UserInfo> user = userService.findByPage();
//        mv.addObject("userList", user);
//        mv.setViewName("UserPage");
//        return mv;
//    }
    //分页查询
    @RequestMapping("/findByPage.do")
    public ModelAndView findByPage(@RequestParam(name="page",required =true,defaultValue ="1") int page,
                                   @RequestParam(name="size",required =true,defaultValue ="4") int size)throws Exception{
        ModelAndView mv = new ModelAndView();
        List<UserInfo> user = userService.findByPage(page,size);
        PageInfo pageInfo = new PageInfo(user);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("UserPage");
        return mv;
    }

   //(升级用户)用户组管理
    @RequestMapping("/changeRole.do")
    public String changeRole(int page,UserInfo userInfo,
            @RequestParam(name = "id",required = true) Integer id,
            @RequestParam(name = "roles",required = true) Integer roles)throws Exception{
        String userName = userInfo.getUserName();
        Integer role = userInfo.getRole();
        userService.changeRole(id,roles);


//      return "redirect:findByPage.do";
        return "redirect:userSearchForm.do?page="+page+"&userName="+userName+"&role="+role;

    }
    //降级用户
    @RequestMapping("/downgradeRole.do")
    public String downgradeRole(int page,UserInfo userInfo,
                             @RequestParam(name = "id",required = true) Integer id,
                             @RequestParam(name = "roles",required = true) Integer roles)throws Exception{
        String userName = userInfo.getUserName();
        Integer role = userInfo.getRole();
        userService.downgradeRole(id,roles);


//      return "redirect:findByPage.do";
        return "redirect:userSearchForm.do?page="+page+"&userName="+userName+"&role="+role;

    }



    //用户禁言功能
    @RequestMapping("/changeTalkStatus")
    public String changeTalkStatus(int page,UserInfo userInfo,
            @RequestParam(name = "id",required = true) Integer id,
            @RequestParam(name = "talkStatus",required = true) Integer talkStatus) throws Exception {
        String userName = userInfo.getUserName();
        Integer role = userInfo.getRole();
        userService.changeTalkStatus(id,talkStatus);

        return "redirect:userSearchForm.do?page="+page+"&userName="+userName+"&role="+role;
    }
    // 用户模糊查询和分页
    @RequestMapping("/userSearchForm.do")
    public String userSearchForm(UserInfo userInfo, Model model,
                                 @RequestParam(name="page",required =true,defaultValue ="1") int page,
                                 @RequestParam(name="size",required =true,defaultValue ="4") int size)throws Exception{
//        ModelAndView mv = new ModelAndView();
        List<UserInfo> user = userService.userSearchForm(userInfo,page,size);
        PageInfo pageInfo = new PageInfo(user);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("condition",userInfo);
//        mv.setViewName("UserPage");
        return "UserPage";
    }

    // 驳回
    @RequestMapping("/isupdating")
    public String isupdating(int page,UserInfo userInfo,
                                   @RequestParam(name = "id",required = true) Integer id,
                                   @RequestParam(name = "isupdating",required = true) Integer isupdating) throws Exception {
        String userName = userInfo.getUserName();
        Integer role = userInfo.getRole();
        userService.isupdating(id,isupdating);

        return "redirect:userSearchForm.do?page="+page+"&userName="+userName+"&role="+role;
    }


}
