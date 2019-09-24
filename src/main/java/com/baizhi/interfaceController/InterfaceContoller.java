package com.baizhi.interfaceController;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @program: code
 * @description: control接口
 * @author: 爱喝汽水的王机智
 * @create: 2019-09-23 17:16
 **/
@RestController
public class InterfaceContoller {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("first_page")
    public HashMap<String, Object> first_page(String uid,String type,String sub_type){
        HashMap<String, Object> map = new HashMap<>();

        if(uid!=null){
            //说明用户登录成功 可以显示首页及其它页面
            if("all".equals(type)){
                //展示所有页面
                //轮播图数据
                List<Banner> banners = bannerService.queryAll();
                map.put("banner",banners);
                //专辑数据
                HashMap<String, Object> albumMap = albumService.selectAll(1, 6);
                List<Album> albums = (List<Album>) albumMap.get("rows");
                map.put("album",albums);
                //文章数据
                HashMap<String, Object> articleMap = articleService.queryBypage(1, 20);
                List<Article> articles = (List<Article>) articleMap.get("rows");
                map.put("article",articles);
            }
            if("wen".equals(type)){
                //展示专辑页面
                //专辑数据
                HashMap<String, Object> albumMap = albumService.selectAll(1, 6);
                List<Album> albums = (List<Album>) albumMap.get("rows");
                map.put("album",albums);
            }
            if("si".equals(type)){
                //展示文章页面
                if("ssyj".equals(sub_type)){
                    //展示上师言教
                    HashMap<String, Object> articleMap = articleService.queryBypage(1, 2);
                    List<Article> articles = (List<Article>) articleMap.get("rows");
                    map.put("article",articles);
                }
                if("xmfy".equals(sub_type)){
                    //展示机密法要
                    HashMap<String, Object> articleMap = articleService.queryBypage(1, 20);
                    List<Article> articles = (List<Article>) articleMap.get("rows");
                    map.put("article",articles);
                }
            }
        }
        return map;
    }


}
