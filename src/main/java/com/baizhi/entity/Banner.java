package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner {
    private String id;
    private String name;
    private  String img_path;
    private String description;
    private String status;
    private Date up_date;
}
