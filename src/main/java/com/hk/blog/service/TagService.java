package com.hk.blog.service;



import com.hk.blog.entity.Tag;

import java.util.List;

public interface TagService {

    void saveTag(Tag tag);

    Tag getTag(Long id);

    List<Tag> listTag();

    void updateTag(Tag tag);

    void deleteTag(Long id);

    List<Tag> findByPage(Integer page,Integer row);

    Integer findTotals();

    Tag getOneByName(String tagname);
}
