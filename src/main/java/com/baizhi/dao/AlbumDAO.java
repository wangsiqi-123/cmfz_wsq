package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDAO {
    Integer queryRecords();

    List<Album> queryByPage(@Param("i") int i, @Param("rows") Integer rows);

    void add(Album album);

    void edit(Album album);

    void delete(Album album);

    Album queryById(String id);
}
