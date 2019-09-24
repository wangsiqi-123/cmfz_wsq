package com.baizhi.test;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Admin;
import io.goeasy.GoEasy;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @program: code
 * @description: 测试poi
 * @author: 爱喝汽水的王机智
 * @create: 2019-09-19 14:17
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class poi {
    @Test
    public void testExportPoi(){
        Admin admin = new Admin(1, "王思琪", "123");
        Admin admin1 = new Admin(2, "宋迎香", "123");
        Admin admin2 = new Admin(3, "小红书", "123");
        Admin admin3 = new Admin(4, "小狗蛋", "123");
        List<Admin> admins = Arrays.asList(admin, admin1, admin2, admin3);



        //创建一个excel文档
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("第一个工作簿");
        //创建日期格式对象
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        //获取样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);



        //创建一行
        Row row = sheet.createRow(0);
        String[]titles={"id","姓名","密码"};
        for (int i = 0; i < titles.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(titles[i]);

        }
        //处理数据航
        for (int i = 0; i < admins.size(); i++) {
            Row row1 = sheet.createRow(i+1);
            Cell cell = row1.createCell(0);
            cell.setCellValue(admins.get(i).getId());
            row1.createCell(1).setCellValue(admins.get(i).getUsername());
            row1.createCell(2).setCellValue(admins.get(i).getPassword());
        }

        try {
            workbook.write(new FileOutputStream(new File("F:\\后期项目\\Day7 Poi\\笔记\\TestPoi.xls")));
            //释放资源
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


@Test
    public void goEasy(){

    for (int i = 0; i < 10; i++) {

        System.out.println(i);

        //创建随机数
        Random random = new Random();

        Integer[] boysRandoms={random.nextInt(100),random.nextInt(800),random.nextInt(900),
                random.nextInt(200),random.nextInt(300),random.nextInt(700)
        };
        Integer[] girlsRandoms={random.nextInt(100),random.nextInt(800),random.nextInt(900),
                random.nextInt(200),random.nextInt(300),random.nextInt(700)
        };

        //创建一个json对象
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("month", Arrays.asList("1月","2月","3月","4月","5月","6月"));
        jsonObject.put("boys", boysRandoms);
        jsonObject.put("girls", girlsRandoms);

        //将json对象转化为json字符串
        String content = jsonObject.toJSONString();

        //配置必要参数    参数： restHost,自己的appkey
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-2e692eeae92a4b00bcb1912852ceb68e");

        //发布消息   参数：channel管道名称 ,发送的内容
        goEasy.publish("myChannel", content);

//        try {
//            //线程休息
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }


}




}

















