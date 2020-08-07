package com.hk.blog.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;



//标签
@Data
public class Tag {

    private Long id;
    @NotEmpty(message = "标签名称不能为空")
    private String tagname;  //标签名称
    private List<Blog> blogs;

}
