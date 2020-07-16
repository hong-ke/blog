package com.hk.blog.service.serviceImp;

import com.hk.blog.dao.TagDAO;
import com.hk.blog.entity.Tag;
import com.hk.blog.service.TagService;
import com.hk.blog.vo.TagTopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImp implements TagService {

    @Autowired
    private TagDAO tagDAO;

    @Override
    public void saveTag(Tag tag) {
        tagDAO.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDAO.getOne(id);
    }

    @Override
    public List<Tag> listTag() {
        return tagDAO.listAll();
    }

    @Override
    public void updateTag(Tag tag) {
        tagDAO.update(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagDAO.delete(id);
    }

    @Override
    public List<Tag> findByPage(Integer page, Integer row) {
        return tagDAO.findByPage(page,row);
    }

    @Override
    public Integer findTotals() {
        return tagDAO.findTotals();
    }

    @Override
    public Tag getOneByName(String typename) {
        return tagDAO.getOneByName(typename);
    }

    @Override
    public List<TagTopVO> findTop(Integer high) {
        return tagDAO.findTop(high);
    }
}
