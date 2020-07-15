package com.hk.blog.dao;


import com.hk.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagDAO extends BaseDAO<Tag, Long>{

}
