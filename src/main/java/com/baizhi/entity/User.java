package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Excel(name="ID")
    private String id;
    @Excel(name="头像",type=2,imageType = 1)
    private String avater;//头像
    @Excel(name="手机号")
    private String phone;//手机号
    @Excel(name="密码")
    private String password;//密码
    @ExcelIgnore
    private String salt;//盐
    @Excel(name="状态")
    private String status;//状态
    @Excel(name="姓名")
    private String name;//姓名
    @Excel(name="法名")
    private String law_name;//法名
    @Excel(name="性别")
    private String sex;//性别
    @Excel(name="所在地")
    private String city;//所在地
    @Excel(name="签名")
    private String sign;//签名
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name="注册时间",format="yyyy年MM月dd日")
    private Date crea_date;//注册时间
    @Excel(name="上师id")
    private String guru_id;//上师id



}
