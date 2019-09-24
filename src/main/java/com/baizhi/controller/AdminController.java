package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    @ResponseBody
    public HashMap<String, Object > login(String enCode ,Admin admin,HttpServletRequest request){
        HashMap<String, Object> map = adminService.selectByName(enCode, admin, request);
        return map;
    }



    //验证码的方法
    @RequestMapping("getcode")
    public void getcode(HttpServletResponse response, HttpServletRequest request){
        //获取验证码随机字符
        String code = ImageCodeUtil.getSecurityCode();
        System.out.println(code);
        //储存验证码随机字符
        request.getSession().setAttribute("imagecode",code);
        BufferedImage image = ImageCodeUtil.createImage(code);
        //设置响应格式
        response.setContentType("image/png");

        try {
            //将验证码图片响应到前台
            ImageIO.write(image,"png",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //登出的方法
    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("admin");
        return "redirect:/login/login.jsp";
    }




}
