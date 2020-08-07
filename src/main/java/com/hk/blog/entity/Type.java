package com.hk.blog.entity;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;


//分类
@Data
public class Type {

    private Long id;
    @NotEmpty(message = "分类名称不能为空")
    private String typename;  // 分类名字
    private List<Blog> blogs;
}
