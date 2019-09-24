package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDAO {
    List<Banner> selectAll(@Param("page") Integer page, @Param("rows") Integer rows);

    void add(Banner banner);

    void edit(Banner banner);

    void delete(Banner banner);

    Integer selectCount();

    List<Banner> queryAll();
}
