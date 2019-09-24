package com.baizhi.service;

import com.baizhi.entity.City;
import com.baizhi.entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserService {
    HashMap<String, Object> queryBypage(Integer page, Integer rows);
    HashMap<String,Object> editStatus(String id,String status);


    HashMap<String, Object> exportUsers();

    HashMap<String, Object> selectByMonth();

    ArrayList<City> selectByCity(String sex);

    String add(User user);
}
