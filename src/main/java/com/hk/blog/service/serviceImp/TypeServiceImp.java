package com.hk.blog.service.serviceImp;

import com.hk.blog.dao.TypeDAO;
import com.hk.blog.entity.Type;
import com.hk.blog.service.TypeService;
import com.hk.blog.vo.TypeTopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImp implements TypeService {

    @Autowired
    private TypeDAO typeDAO;

    @Override
    public void saveType(Type type) {
        typeDAO.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDAO.getOne(id);
    }

    @Override
    public List<Type> listType() {
        return typeDAO.listAll();
    }

    @Override
    public void updateType(Type type) {
        typeDAO.update(type);
    }

    @Override
    public void deleteType(Long id) {
        typeDAO.delete(id);
    }

    @Override
    public List<Type> findByPage(Integer page, Integer row) {
        return typeDAO.findByPage(page,row);
    }

    @Override
    public Integer findTotals() {
        return typeDAO.findTotals();
    }

    @Override
    public Type getOneByName(String typename) {
        return typeDAO.getOneByName(typename);
    }

    /**
     * 首页获取博客前N标签
     * @return
     */
    @Override
    public List<TypeTopVO> findTop() {
        return typeDAO.findTop(6);
    }
}
