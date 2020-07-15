package com.hk.blog.vo;

import com.hk.blog.entity.Blog;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class TypeTopVO {

    private Long id;
    @NotEmpty(message = "分类名称不能为空")
    private String typename;  // 分类名字
    private List<Blog> blogs;
    private Integer size;
}
