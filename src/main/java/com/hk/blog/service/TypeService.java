package com.hk.blog.service;

import com.hk.blog.entity.Type;
import com.hk.blog.vo.TypeTopVO;

import java.util.List;

public interface TypeService {

    void saveType(Type type);

    Type getType(Long id);

    List<Type> listType();

    void updateType(Type type);

    void deleteType(Long id);

    List<Type> findByPage(Integer page,Integer row);

    Integer findTotals();

    Type getOneByName(String typename);

    List<TypeTopVO> findTop(Integer high);
}
