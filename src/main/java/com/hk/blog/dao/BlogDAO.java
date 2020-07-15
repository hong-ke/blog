package com.hk.blog.dao;

import com.hk.blog.entity.Blog;
import com.hk.blog.vo.BlogQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogDAO extends BaseDAO<Blog,Long>{
    List<Blog> listAll(BlogQuery blogQuery);

    int saveReId(Blog blog);
}
