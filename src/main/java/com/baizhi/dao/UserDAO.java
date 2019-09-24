package com.baizhi.dao;

import com.baizhi.entity.City;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface UserDAO {
    //分页查询所有用户信息
    List<User> queryByPage(@Param("i") int i, @Param("rows") Integer rows);
    //修改用户状态的方法
    void edit(User user);


    Integer selectRecords();

    List<User> selectAll();

    ArrayList<City> selectByCity(String sex);

    ArrayList<String>  selectMonths();

    Integer selectBySexMonth(@Param("sex") String sex, @Param("month")String month);

    void add(User user);
}
