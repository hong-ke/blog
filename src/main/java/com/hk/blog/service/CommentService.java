package com.hk.blog.service;


import com.hk.blog.entity.Comment;

import java.util.List;

/**
 * Created by limi on 2017/10/22.
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    void saveComment(Comment comment);
}
