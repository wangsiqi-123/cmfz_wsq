package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDAO {
    Integer queryRecords();

    List<Chapter> queryByPage(@Param("i") int i, @Param("rows") Integer rows,@Param("albumid") String albumId);

    void add(Chapter chapter);

    void edit(Chapter chapter);

    void delete(String id);
}
