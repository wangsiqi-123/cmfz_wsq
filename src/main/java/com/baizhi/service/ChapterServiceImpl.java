package com.baizhi.service;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Chapter;
import com.baizhi.util.UUIDUtil;
import org.apache.commons.io.IOUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDAO chapterDAO;

    @Override
    public HashMap<String, Object> selectAll(Integer page, Integer rows, String albumId) {
        HashMap<String, Object> map = new HashMap<>();
        //records 总条数
        Integer records= chapterDAO.queryRecords();
        map.put("records",records);
        //total 总页数
        Integer total=records%rows==0?records/rows:records/rows+1;
        map.put("total",total);
        //page  当前页
        map.put("page",page);
        //rows 每页展示的数据
        List<Chapter> chapters=chapterDAO.queryByPage((page-1)*rows,rows,albumId);
        map.put("rows",chapters);
        System.out.println("----Service----map===="+map);
        return map;
    }

    @Override
    public String add(Chapter chapter, String albumId) {
        chapter.setAlbum_id(albumId);
        chapter.setId(UUIDUtil.getUUID());
        chapter.setUp_date(new Date());
        chapterDAO.add(chapter);
        return chapter.getId();
    }

    @Override
    public void edit(Chapter chapter) {
        chapterDAO.edit(chapter);

    }

    @Override
    public void delete(Chapter chapter) {
        chapterDAO.delete(chapter.getId());
    }

    @Override
    public HashMap<String, Object> uploadChapter(MultipartFile url, String id, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        String realPath = request.getSession().getServletContext().getRealPath(("/uoload/music"));
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String filename = url.getOriginalFilename();
        String name=new Date().getTime()+"-"+filename;
        try {
            url.transferTo(new File(realPath,name));
            //获取文件上传的大小和时长
            //获取音频大小    zijie
            long size = url.getSize();
            System.out.println("文件大小 :"+size);

            //=========1========
            String sizes = size/1024/1024+"MB";

            System.out.println("文件大小 :"+sizes);
            //=========2========
            DecimalFormat format = new DecimalFormat("0.00");
            String str = String.valueOf(size);
            Double dd = Double.valueOf(str)/1024/1024;
            String sizess = format.format(dd)+"MB";

            System.out.println("文件大小 :"+sizess);

            //------获取文件的时长--------
            //获取文件时长   分

                //获取文件时长   分
            AudioFile audioFile = AudioFileIO.read(new File(realPath, name));
            AudioHeader audioHeader = audioFile.getAudioHeader();
            int length = audioHeader.getTrackLength();
            String duration=length/60+"分"+length%60+"秒";





            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setSize(sizess);
            chapter.setDuration(duration);
            chapter.setUrl(name);
            chapterDAO.edit(chapter);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success","400");
            map.put("message","上传失败");
        }
        map.put("success","200");
        map.put("message","上传成功");

        return map;
    }



    public void downloadAudio(String url, HttpServletRequest request, HttpServletResponse response) {
//        //通过文件名获取文件
//        String realPath = request.getSession().getServletContext().getRealPath("/uoload/music");
//        //读取文件的输入流
//        try {
//            FileInputStream fileInputStream = new FileInputStream(new File(realPath, url));
//            //设置响应的方式 获取响应头 以附件形势打开
//            response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(url, "UTF-8"));
//            //下载文件
//            IOUtils.copy(fileInputStream,response.getOutputStream());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

}
