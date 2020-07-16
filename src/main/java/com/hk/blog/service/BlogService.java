package com.hk.blog.service;


import com.github.pagehelper.PageInfo;
import com.hk.blog.entity.Blog;
import com.hk.blog.vo.BlogQuery;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

//    Page<Blog> listBlog(Pageable pageable,BlogQuery blog);
//
//    Page<Blog> listBlog(Pageable pageable);
//
//    Page<Blog> listBlog(Long tagId,Pageable pageable);
//
//    Page<Blog> listBlog(String query,Pageable pageable);
//
//    List<Blog> listRecommendBlogTop(Integer size);
//
//    Map<String,List<Blog>> archiveBlog();

    PageInfo<Blog> listBlog(Integer page, BlogQuery blogQuery);

    Integer countBlog();

    void saveBlog(Blog blog);

    void updateBlog(Blog blog);

    void deleteBlog(Long id);

    List<Blog> findTop();

    PageInfo<Blog> findByQuery(Integer page,String query);

    Map<String,List<Blog>> archiveBlog();

}
