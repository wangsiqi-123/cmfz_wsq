package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.HashMap;

public interface ArticleService {
    HashMap<String, Object> queryBypage(Integer page, Integer rows);
    HashMap<String,Object> editStatus(String id,String status);

    void delete(String id);

    void add(Article article);

    HashMap<String, Object> update(Article article);
}
