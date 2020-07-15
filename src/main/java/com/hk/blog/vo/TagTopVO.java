package com.hk.blog.vo;

import com.hk.blog.entity.Blog;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class TagTopVO {
    private Long id;
    @NotEmpty(message = "标签名称不能为空")
    private String tagname;  //标签名称
    private List<Blog> blogs;
    private Integer size;
}
