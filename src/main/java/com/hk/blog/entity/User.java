package com.hk.blog.entity;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//用户
@Data
public class User {
    private Long id;
    private String nickname;  //昵称
    private String username;  //用户名
    private String password;  //密码
    private String email;  //邮箱
    private String avatar;  //头像
    private Integer type;  //用户类型
    private Date createTime;  //创建时间
    private Date updateTime;  //更新时间
    private List<Blog> blogs;
}
