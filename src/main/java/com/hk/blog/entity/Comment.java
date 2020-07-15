package com.hk.blog.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//评论
@Data
public class Comment {


    private Long id;
    private String nickname;  //昵称
    private String email;  //邮箱
    private String content;  //评论内容
    private String avatar;  //头像

    private Date createTime;  //评论时间
    private Blog blog;  // 博客
    private List<Comment> replyComments;
    private Comment parentComment;
    private boolean adminComment;

}
