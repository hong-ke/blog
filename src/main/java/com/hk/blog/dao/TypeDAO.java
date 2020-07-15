package com.hk.blog.dao;


import com.hk.blog.entity.Tag;
import com.hk.blog.entity.Type;
import com.hk.blog.vo.TypeTopVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeDAO extends BaseDAO<Type, Long>{

    List<TypeTopVO> findTop(Integer high);
}
