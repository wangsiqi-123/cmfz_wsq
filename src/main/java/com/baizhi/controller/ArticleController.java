package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    @RequestMapping("selectByPage")
    public HashMap<String, Object> selectByPage(Integer page, Integer rows){
        HashMap<String,Object>map=articleService.queryBypage(page,rows);
        return map;
    }
    //------------修改用户状态的方法---------------
    @RequestMapping("editStatus")
    public HashMap<String,Object> editStatus(String id,String status){
        HashMap<String, Object> map = articleService.editStatus(id, status);
        System.out.println("修改文章状态  id和status为"+id+"---"+status);
        return map;
    }
    @RequestMapping("articleEdit")
    public String articleEdit(String oper, Article article){
        String id =null;
        if(oper.equals("add")){
            System.out.println("添加了");
        }else if(oper.equals("edit")){
            System.out.println("修改了");
        }else if(oper.equals("del")){
            articleService.delete(article.getId());
        }
        return id;
    }


    @RequestMapping("add")
    public HashMap<String,Object> add(Article article){
        HashMap<String, Object> map = new HashMap<>();
        try{
            articleService.add(article);
            map.put("message","添加成功");


        }catch (Exception e){
            e.printStackTrace();
            map.put("message","添加失败");
        }
        return map;
    }
    @RequestMapping("update")
    public HashMap<String,Object> update(Article article){
        System.out.println(article);
        HashMap<String, Object> map=articleService.update(article);
        return map;
    }

@RequestMapping("delete")
    public HashMap<String,Object>delete(String id){
    HashMap<String, Object> map = new HashMap<>();
        articleService.delete(id);
        map.put("message","上传成功");
        return map;
}


}


























