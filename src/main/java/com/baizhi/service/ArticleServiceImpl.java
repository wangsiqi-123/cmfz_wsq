package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import com.baizhi.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements  ArticleService {
    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public HashMap<String, Object> queryBypage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //records 总条数
        Integer records = articleDAO.selectRecords();
        map.put("records", records);
        //total 总页数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);
        //page  当前页
        map.put("page", page);
        //rows 每页展示的数据
        List<Article> articles = articleDAO.queryByPage((page - 1) * rows, rows);
        map.put("rows", articles);


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
            Article article = new Article();
            article.setId(id);
            article.setStatus(status);
            articleDAO.edit(article);
            System.out.println("service执行的修改状态" + article);
            map.put("success", 200);
            map.put("message", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return map;
    }

    @Override
    public void delete(String id) {
        articleDAO.delete(id);

    }

    @Override
    public void add(Article article) {
        article.setCrea_date(new Date());
        article.setGuru_id("1");
        article.setStatus("1");
        article.setId(UUIDUtil.getUUID());
        articleDAO.add(article);
    }

    @Override
    public HashMap<String, Object> update(Article article) {
        HashMap<String, Object> map = new HashMap<>();
        try{
            articleDAO.update(article);
            map.put("message","修改成功");


        }catch (Exception e){
            e.printStackTrace();
            map.put("message","修改失败");
        }


        return map;
    }


}
