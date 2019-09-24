package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    private String id;
    private String title;
    private String cover;
    private String author;
    private Integer score;
    private String broadcast;
    private  Integer counts;
    private String content;
    private Date crea_date;

}
