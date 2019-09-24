package com.baizhi.dao;

import com.baizhi.entity.Admin;

public interface AdminDAO {
    public Admin selectByName(String username);
}
