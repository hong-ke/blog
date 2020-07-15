package com.hk.blog.vo;

import lombok.Data;


//分页查询条件
@Data
public class BlogQuery {

    private String title;
    private Long typeId;
    private boolean recommend;

}
