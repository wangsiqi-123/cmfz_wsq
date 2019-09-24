package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public interface AdminService {
    //管理员的登录方法
    public HashMap<String, Object > selectByName(String enCode , Admin admin, HttpServletRequest request);
}
