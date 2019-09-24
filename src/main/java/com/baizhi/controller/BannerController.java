package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

@RequestMapping("queryByBanner")//当前页       每页显示的条数
    public HashMap<String, Object> selectAll(Integer page,Integer rows){
    HashMap<String, Object> map = new HashMap<>();

    List<Banner>list=bannerService.selectAll(page,rows);
    Integer count=bannerService.selectCount();
    Integer total=count%rows==0?count/rows:count/rows+1;
    map.put("rows",list);
    map.put("records",count);
    map.put("page",page);
    map.put("total",total);
    System.out.println("查询出来的集合是"+list);
        return map;
    }
    @RequestMapping("edit")
    public String edit(String oper,Banner banner){
        String id=null;
    //执行添加
        if(oper.equals("add")){
            id = bannerService.add(banner);
            System.out.println("controller接受的banner"+banner);
            return id;
        }
        //执行修改操作
        if(oper.equals("edit")){
            id = bannerService.edit(banner);
            System.out.println("controller接受的banner"+banner);
            return null;
        }
        //执行删除操作
        if(oper.equals("del")){
            bannerService.delete(banner);
            System.out.println("controller接受的banner"+banner);
        }
        return id;

    }

    @RequestMapping("bannerUpload")
    public void bannerUpload(MultipartFile img_path, String id, HttpServletRequest request){
    //根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/photo");
        //获取文件夹
        File file = new File(realPath);
        //判断文件夹是否存在
        if(!file.exists()){
            file.mkdirs();//创建文件夹
        }
        //获取文件名
        String filename = img_path.getOriginalFilename();
        //给文件加一个时间戳
        String name=new Date().getTime()+"-"+filename;
        //文件上传
        try {
            img_path.transferTo(new File(realPath,name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //执行修改文件路径
        Banner banner = new Banner();
        banner.setId(id);
        banner.setImg_path(name);
        System.out.println("执行修改操作"+banner);
        bannerService.edit(banner);


    }
    @RequestMapping("uploadStatus")
    public HashMap<String,Object> uploadStatus(String id,String status){
        System.out.println("接收到的修改的id和状态为"+id+"-----"+status);
        HashMap<String,Object>map= bannerService.updateStatus(id,status);
        return map;
    }

}
