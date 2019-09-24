package com.baizhi.service;


import com.baizhi.entity.Banner;

import java.util.HashMap;
import java.util.List;

public interface BannerService {


    List<Banner> selectAll(Integer page, Integer rows);

    String add(Banner banner);

    String edit(Banner banner);

    void delete(Banner banner);

    Integer selectCount();

    HashMap<String, Object> updateStatus(String id, String status);

    List<Banner> queryAll();
}
