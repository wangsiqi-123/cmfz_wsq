package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;

import java.util.HashMap;
import java.util.List;

public interface AlbumService {
    HashMap<String, Object> selectAll(Integer page, Integer rows);
    String add(Album album);

    void edit(Album album);

    void delete(Album album);

    Album queryById(String id);
}
