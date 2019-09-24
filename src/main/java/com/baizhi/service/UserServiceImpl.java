package com.baizhi.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.City;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import com.baizhi.util.UUIDUtil;
import jdk.nashorn.internal.runtime.arrays.IteratorAction;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;


    @Override
    public HashMap<String, Object> queryBypage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //records 总条数
        Integer records = userDAO.selectRecords();
        map.put("records", records);
        //total 总页数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);
        //page  当前页
        map.put("page", page);
        //rows 每页展示的数据
        List<User> users = userDAO.queryByPage((page - 1) * rows, rows);
        map.put("rows", users);


        return map;
    }

    @Override
    public HashMap<String, Object> editStatus(String id, String status) {
        HashMap<String, Object> map = new HashMap<>();
        try {

            if (status.equals("1")) {
                status = "2";
            }
            else if (status.equals("2")) {
                status = "1";
            }
            User user = new User();
            user.setId(id);
            user.setStatus(status);
            userDAO.edit(user);
            System.out.println("service执行的修改状态" + user);
            map.put("success", 200);
            map.put("message", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return map;
    }

    @Override
    public HashMap<String, Object> exportUsers() {
        HashMap<String, Object> map = new HashMap<>();
        try{
            List<User> users=userDAO.selectAll();

            ArrayList<User> list = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                user.setAvater("F:\\后期项目2\\code\\cmfz_wsq\\src\\main\\webapp\\upload\\photo\\"+user.getAvater());
                list.add(user);
            }
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息表","表1"),User.class, users);
            workbook.write(new FileOutputStream(new File("F:\\后期项目2\\Day7 Poi\\笔记\\EasyPoi.xls")));
            workbook.close();

            map.put("message","导出成功");
        }catch(Exception e){
            e.printStackTrace();
            map.put("message","导出失败");
        }



        return map;
    }

    @Override
    public HashMap<String, Object> selectByMonth() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        ArrayList<Integer> boys = new ArrayList<>();
        ArrayList<Integer> grils = new ArrayList<>();
        //查询出所有有人的月份
        ArrayList<String> month = userDAO.selectMonths();



        //遍历集合  拿出所有的月份  分别传入性别进行查询 返回boys 和grils
        for(int i=0;i<month.size();i++){
            String m = month.get(i);
             Integer b=userDAO.selectBySexMonth("男",m);
             boys.add(b);
            Integer g= userDAO.selectBySexMonth("女",m);
            grils.add(g);
        }
        map.put("month",month);
        map.put("boys",boys);
        map.put("grils",grils);
        System.out.println("map==="+map);


        return map;


    }

    @Override
    public ArrayList<City> selectByCity(String sex) {
        ArrayList<City> citys=userDAO.selectByCity(sex);
        return citys;
    }

    @Override
    public String add(User user) {
        user.setId(UUIDUtil.getUUID());
        user.setAvater("-----");
        user.setCrea_date(new Date());
        try {
            userDAO.add(user);
        }catch (Exception e){
            e.printStackTrace();
        }

        return user.getId();
    }
}