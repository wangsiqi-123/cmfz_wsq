package com.baizhi.service;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;
    @Override
    public HashMap<String, Object > selectByName(String enCode , Admin admin, HttpServletRequest request) {
        String imagecode = (String) request.getSession().getAttribute("imagecode");
        Admin adminD = adminDAO.selectByName(admin.getUsername());
        HashMap<String, Object > map = new HashMap<>();
        if(imagecode.equals(enCode)){
            if(admin.getUsername().equals(adminD.getUsername())){
                if(admin.getPassword().equals(adminD.getPassword())){

                    request.getSession().setAttribute("admin",adminD);

                    map.put("success","200");
                    map.put("message","登陆成功");
                }else {
                    map.put("success","400");
                    map.put("message","密码输入错误");
                }

            }else{
                map.put("success","400");
                map.put("message","用户名不存在");
            }

        }else {
            map.put("success","400");
            map.put("message","验证码输入错误");
        }



        return map;

    }
}
