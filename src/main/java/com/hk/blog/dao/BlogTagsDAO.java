package com.hk.blog.dao;

import com.hk.blog.entity.BlogTags;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogTagsDAO extends BaseDAO<BlogTags,Long>{

    List<BlogTags> getOneByBlog(Long id);
    void saveList(List list);
}
