package com.hk.blog.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDAO<T,K> {


    void save(T t);

    void update(T t);

    void delete(K k);

    T getOne(K k);

    T getOneByName(String name);

    List<T> listAll();

    List<T> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    Integer findTotals();

}
