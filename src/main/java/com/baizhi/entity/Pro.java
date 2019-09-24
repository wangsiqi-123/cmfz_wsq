package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: code
 * @description: 省份
 * @author: 爱喝汽水的王机智
 * @create: 2019-09-20 19:43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pro {
    private String title;
    private List<City> citys;
}
