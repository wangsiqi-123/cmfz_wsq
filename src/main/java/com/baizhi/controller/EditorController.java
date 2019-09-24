package com.baizhi.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.cert.Extension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @program: code
 * @description: 富文本编辑器
 * @author: 爱喝汽水的王机智
 * @create: 2019-09-18 14:53
 **/
@RestController
@RequestMapping("editor")
public class EditorController {
    @RequestMapping("uploadEditor")
    public HashMap<String, Object> uploadEditor(MultipartFile photo, HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();

        try {
            String realPath = request.getSession().getServletContext().getRealPath("/upload/editor");
            File file = new File(realPath);
            if(!file.exists()){
                file.mkdirs();
            }
            String filename = photo.getOriginalFilename();
            String name = new Date().getTime() + "-" + filename;
            photo.transferTo(new File(realPath,name));

            String scheme = request.getScheme();//http
            String serverName = request.getServerName();//localhost
            int serverPort = request.getServerPort();//端口号
            String contextPath = request.getContextPath();//获取项目名字
            String url=scheme+"://"+serverName+":"+serverPort+contextPath+"/upload/editor/"+name;
            map.put("error",0);
            map.put("url",url);


        } catch (Exception e) {
            e.printStackTrace();
            map.put("error",1);
            map.put("message","上传失败");

        }
         return map;

    }

    @RequestMapping("queryPhotos")
    public HashMap<String, Object> queryPhotos(HttpServletRequest request){
//        HashMap<String, Object> maps = new HashMap<>();
//        ArrayList<Object> list = new ArrayList<>();
//
//        //获取文件的绝对路径
//        String realPath = request.getSession().getServletContext().getRealPath("/upload/editor/");
//        //获取文件
//        File file = new File(realPath);
//        //获取文件夹中所有的文件名
//        String[] names = file.list();
//        for(int i=0;i< names.length;i++){
//            String name=names[i];
//
//            HashMap<String, Object> map1 = new HashMap<>();
//
//            map1.put("is_dir",false);//是否是文件夹
//            map1.put("has_file",false);//是否有文件
//            File file1 = new File(realPath,name);
//            map1.put("filesize",file1.length());//文件的大小
//            map1.put("is_photo",true);//是否是图片
//            String extension = FilenameUtils.getExtension(name);
//            map1.put("filetype", extension);//图片的类型
//            map1.put("filename",name);//图片的名字
//            String[] strs = name.split("-");
//            String times = strs[0];
//            long time=Long.parseLong(times);
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String datetime = dateFormat.format(time);
//            map1.put("datetime",datetime);//图片上传时间
//            list.add(map1);
//        }
//            maps.put("current_url","http://localhost:8989/cmfz_wsq/upload/editor/");
//            maps.put("total_count",list.size());
//            maps.put("file_list",list);
//            return maps;
//
//
//    }
//

        HashMap<String, Object> maps = new HashMap<>();

        ArrayList<Object> lists = new ArrayList<>();

        //获取文件的绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/editor");

        //获取文件
        File file = new File(realPath);

        //获取文件夹中所有的   文件名
        String[] names = file.list();

        //遍历文件名
        for (int i = 0; i < names.length; i++) {
            //文件名
            String name = names[i];

            /*
            *
                {
                  "is_dir": false,
                  "has_file": false,
                  "filesize": 14966,
                  "dir_path": "",
                  "is_photo": true,
                  "filetype": "jpg",
                  "filename": "1_192040_1.jpg",
                  "datetime": "2018-06-06 00:36:39"
                }
            * */
            HashMap<String, Object> map = new HashMap<>();

            map.put("is_dir",false);  //是否是文件夹
            map.put("has_file",false);  //是否有文件
            File file1 = new File(realPath, name);
            map.put("filesize",file1.length());  //文件的大小
            map.put("is_photo",true);  //是否是图片
            String extension = FilenameUtils.getExtension(name);
            map.put("filetype",extension);  //图片的类型
            map.put("filename",name);  //图片的名字

            //字符串拆分
            String[] strs = name.split("-");
            String times =strs[0];
            long time = Long.parseLong(times);
            //指定一个日期格式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String datetime = dateFormat.format(time);

            /*"2018-06-06 00:36:39"*/
            map.put("datetime",datetime);  //图片上传时间

            //将数据放入集合
            lists.add(map);
        }

        /*
        *   "moveup_dir_path": "",
              "current_dir_path": "",
              "current_url": "/ke4/php/../attached/",
              "total_count": 5,
              "file_list":
        * */
        maps.put("current_url","http://localhost:8989/cmfz_wsq/upload/editor/");
        maps.put("total_count",lists.size());
        maps.put("file_list",lists);

        return maps;
    }

}





















