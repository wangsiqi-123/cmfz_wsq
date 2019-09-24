package com.baizhi.service;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDAO albumDAO;


    @Override
    public  HashMap<String, Object> selectAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //records 总条数
        Integer records= albumDAO.queryRecords();
        map.put("records",records);
        //total 总页数
        Integer total=records%rows==0?records/rows:records/rows+1;
        map.put("total",total);
        //page  当前页
        map.put("page",page);
        //rows 每页展示的数据
        List<Album> albums=albumDAO.queryByPage((page-1)*rows,rows);
        map.put("rows",albums);
        System.out.println("----Service----map===="+map);
        return map;
    }

    @Override
    public String  add(Album album) {
        album.setId(UUIDUtil.getUUID());
        album.setCrea_date(new Date());
        albumDAO.add(album);
        return album.getId();

    }

    @Override
    public void edit(Album album) {
        albumDAO.edit(album);

    }

    @Override
    public void delete(Album album) {
        albumDAO.delete(album);

    }

    @Override
    public Album queryById(String id) {
        Album album=albumDAO.queryById(id);
        return album;
    }


}























