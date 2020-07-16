package com.hk.blog.service.serviceImp;

import com.hk.blog.dao.CommentDAO;
import com.hk.blog.entity.Comment;
import com.hk.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        return commentDAO.findByBlogId(blogId);
    }

    @Transient
    @Override
    public void saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {//回复
            comment.setParentCommentId(commentDAO.getOne(parentCommentId).getId());
            comment.setParentCommentId(comment.getParentComment().getId());
        } else {//评论
            comment.setParentComment(null);
        }

        comment.setCreateTime(new Date());
        comment.setBlogId(comment.getBlog().getId());
        commentDAO.save(comment);

    }
}
