package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public interface ChapterService {

    HashMap<String, Object> selectAll(Integer page, Integer rows, String albumId);
    String add(Chapter chapter, String albumId);

    void edit(Chapter chapter);

    void delete(Chapter chapter);

    HashMap<String, Object> uploadChapter(MultipartFile url, String id, HttpServletRequest request);
    public void downloadAudio(String fileName, HttpServletRequest request, HttpServletResponse response);
}
