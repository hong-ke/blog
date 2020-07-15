package com.hk.blog.dao;


import com.hk.blog.entity.Tag;
import com.hk.blog.vo.TagTopVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagDAO extends BaseDAO<Tag, Long>{

    List<TagTopVO> findTop(Integer high);
}
