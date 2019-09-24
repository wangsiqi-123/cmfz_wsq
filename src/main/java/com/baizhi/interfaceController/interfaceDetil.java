package com.baizhi.interfaceController;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @program: code
 * @description: 闻的接口controller
 * @author: 爱喝汽水的王机智
 * @create: 2019-09-23 20:29
 **/
@RestController
@RequestMapping("detail")
public class interfaceDetil {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("wen")
    public HashMap<String, Object> wen(String id,String uid){
        HashMap<String, Object> map = new HashMap<>();
        if(uid!=null){
            //id--专辑id   uid---用户id
            Album album=albumService.queryById(id);

            HashMap<String, Object> albumMap = chapterService.selectAll(1, 100, id);
            List<Album> albums = (List<Album>) albumMap.get("rows");
            map.put("list",albums);
            map.put("introduction",album);
        }
        return map;
    }

}
