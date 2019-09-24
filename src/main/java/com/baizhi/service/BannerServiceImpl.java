package com.baizhi.service;

import com.baizhi.dao.BannerDAO;
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
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDAO bannerDAO;


    @Override
    public List<Banner> selectAll(Integer page, Integer rows) {
        List<Banner>list=bannerDAO.selectAll(page,rows);
        return list;
    }

    @Override
    public String  add(Banner banner) {
        banner.setId(UUIDUtil.getUUID());
        banner.setStatus("1");
        banner.setUp_date(new Date());
        bannerDAO.add(banner);
        return banner.getId();

    }

    @Override
    public String edit(Banner banner) {
        bannerDAO.edit(banner);
        return banner.getId();

    }

    @Override
    public void delete(Banner banner) {
        bannerDAO.delete(banner);

    }

    @Override
    public Integer selectCount() {
       Integer count= bannerDAO.selectCount();
        return count;
    }

    @Override
    public HashMap<String, Object> updateStatus(String id, String status) {
        HashMap<String, Object> map = new HashMap<>();

        if(status.equals("1")){
            status="2";
        }else if(status.equals("2")){
            status="1";
        }
        Banner banner = new Banner();
        banner.setId(id);
        banner.setStatus(status);
        System.out.println("要执行修改的banner是"+banner);
        bannerDAO.edit(banner);
        map.put("success","200");

        return map;
    }

    @Override
    public List<Banner> queryAll() {
        List<Banner>list= bannerDAO.queryAll();
        return list;
    }
}
