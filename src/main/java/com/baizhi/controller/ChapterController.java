package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("queryByPage")
    public HashMap<String, Object> queryByPage(Integer page, Integer rows,String albumId){
        HashMap<String, Object> map = chapterService.selectAll(page, rows,albumId);
        System.out.println("----controller----map===="+map);
        return map;
    }
    @RequestMapping("edit")
    public String edit(String oper, Chapter chapter,String albumId){
        String id=null;
        //执行添加
        if(oper.equals("add")){
            id = chapterService.add(chapter,albumId);
            System.out.println("controller接受的chapter===================="+chapter);
            System.out.println("返回的id为==="+id);
        }
        //执行修改操作
        if(oper.equals("edit")){
            chapterService.edit(chapter);
            System.out.println("controller接受的chapter=================="+chapter);
        }
        //执行删除操作
        if(oper.equals("del")){
            chapterService.delete(chapter);
            System.out.println("controller接受的chapter==========="+chapter);
        }
        return id;

    }

        @RequestMapping("uploadChapter")
    public HashMap<String,Object> uploadChapter(MultipartFile url, String id, HttpServletRequest request){
            System.out.println("上传中的id为==="+id);
        HashMap<String,Object> map=chapterService.uploadChapter(url,id,request);
            System.out.println("上传文件过后返回"+map);
        return map;


    }


    //-----------文件的下载----------
    @RequestMapping("downloadAudio")
    public void downloadAudio(String url, HttpServletRequest request, HttpServletResponse response){
        //通过文件名获取文件
        String realPath = request.getSession().getServletContext().getRealPath("/uoload/music");
        //读取文件的输入流
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(realPath, url));
            //设置响应的方式 获取响应头 以附件形势打开
            response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(url, "UTF-8"));
            //下载文件
            IOUtils.copy(fileInputStream,response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
