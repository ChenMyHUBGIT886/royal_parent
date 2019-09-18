package com.bbs.controller;


import com.bbs.domain.UserInfo;
import com.bbs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    //文件上传

    @RequestMapping("/update.do")
    public String update( @RequestParam(name="file" ) MultipartFile picUrl ,UserInfo userInfo,HttpServletRequest request) throws Exception {
        // 使用fileupload组件完成文件上传
        // 上传的位置
        String path = request.getSession().getServletContext().getRealPath("/upload/images");
        // 判断，该路径是否存在
        File file = new File(path);
        if(!file.exists()){
            // 创建该文件夹
            file.mkdirs();
        }
        // 说明上传文件项
        // 获取上传文件的名称
        String filename = picUrl.getOriginalFilename();
        // 把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;
        // 完成文件上传
        picUrl.transferTo(new File(path,filename));
        //图片地址
        userInfo.setPicUrl(""+"/upload/images"+filename);
        //更新用户
        userInfoService.update(userInfo);
        return "userInfo";
    }
}
