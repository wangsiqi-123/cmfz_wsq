package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.*;
import com.baizhi.service.UserService;
import com.baizhi.util.PhoneMsgUtil;
import io.goeasy.GoEasy;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("addUser")
    public String addUser(String oper, User user){
        String id=null;
        //执行添加
        if(oper.equals("add")){
            id= userService.add(user);
            //触发查询方法  再次查询后得到该值返回
            HashMap<String,Object> map=userService.selectByMonth();

            //创建一个json对象
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("month", map.get("month"));
            jsonObject.put("boys", map.get("boys"));
            jsonObject.put("girls", map.get("grils"));

            //将json对象转化为json字符串
            String content = jsonObject.toJSONString();

            //配置必要参数    参数： restHost,自己的appkey
            GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-2e692eeae92a4b00bcb1912852ceb68e");

            //发布消息   参数：channel管道名称 ,发送的内容
            goEasy.publish("myChannel", content);

            System.out.println("controller接受的user"+user);
            return id;
        }
        //执行修改操作
        if(oper.equals("edit")){

        }
        //执行删除操作
        if(oper.equals("del")){

        }
        return id;

    }



    @RequestMapping("selectByPage")
    public HashMap<String, Object> selectByPage(Integer page, Integer rows){
            HashMap<String,Object>map=userService.queryBypage(page,rows);
            return map;
        }
//------------修改用户状态的方法---------------
        @RequestMapping("editStatus")
        public HashMap<String,Object> editStatus(String id,String status){
            HashMap<String, Object> map = userService.editStatus(id, status);
            System.out.println("修改用户状态  id和status为"+id+"---"+status);
            return map;
        }

        @RequestMapping("exportUsers")
        public HashMap<String,Object> exportUsers(){
        HashMap<String,Object> map=userService.exportUsers();
        return map;
        }

        @RequestMapping("queryByMonth")
        public HashMap<String,Object> queryByMonth(){
            HashMap<String,Object> map=userService.selectByMonth();
            return map;
        }

        @RequestMapping("queryByCity")
    public ArrayList<Pro> queryByCity(){
            ArrayList<Pro> pros = new ArrayList<>();

            String boy="男";
            String girl="女";

            ArrayList<City> citie1=userService.selectByCity(boy);
            ArrayList<City> citie2=userService.selectByCity(girl);
//            System.out.println("city1==="+citie1);
//            System.out.println("city2==="+citie2);
            Pro pro1 = new Pro(boy,citie1);
            Pro pro2 = new Pro(girl,citie2);
//            System.out.println("pro1===="+pro1);
//            System.out.println("pro2===="+pro2);
            pros.add(pro1);
            pros.add(pro2);

            return pros;

        }


        @RequestMapping("phoneMessage")
        public String  phoneMessage(String phone){
            String random = PhoneMsgUtil.getRandom(6);
            String message = PhoneMsgUtil.getPhones(phone, random);
            System.out.println(random);
            System.out.println(message);
            return message;

        }


}






































