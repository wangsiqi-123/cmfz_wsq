package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import cn.afterturn.easypoi.excel.html.css.impl.HeightCssConverImpl;
import lombok.*;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Admin {
    private Integer id;
    private String username;
    private String password;




}
