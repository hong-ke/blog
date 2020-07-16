package com.hk.blog.dao;

import com.hk.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDAO extends BaseDAO<Comment,Long>{
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId);

    List<Comment> findByBlogId(Long blogId);
}
