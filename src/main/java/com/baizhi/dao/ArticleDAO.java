package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDAO {
    //分页查询所有文章信息
    List<Article> queryByPage(@Param("i") int i, @Param("rows") Integer rows);
    //修改文章状态的方法
    void edit(Article article);

//查询文章总条数的方法
    Integer selectRecords();

    void delete(String id);

    void add(Article article);

    void update(Article article);
}
