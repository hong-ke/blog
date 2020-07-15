package com.hk.blog.service.serviceImp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hk.blog.dao.BlogDAO;
import com.hk.blog.dao.BlogTagsDAO;
import com.hk.blog.dao.TagDAO;
import com.hk.blog.dao.TypeDAO;
import com.hk.blog.entity.Blog;
import com.hk.blog.entity.BlogTags;
import com.hk.blog.entity.Tag;
import com.hk.blog.service.BlogService;
import com.hk.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.*;

@Service
public class BlogServiceImp implements BlogService {

    @Autowired
    private BlogDAO blogDAO;

    @Autowired
    private BlogTagsDAO blogTagsDAO;

    @Autowired
    private TagDAO tagDAO;

    @Autowired
    private TypeDAO typeDAO;

    @Transient
    @Override
    public Blog getBlog(Long id) {
        return SetTypeTag(blogDAO.getOne(id));
    }

    @Override
    public Blog getAndConvert(Long id) {
        return null;
    }

    /**
     * 将blog封装完整，如Tag、Type等封装成实体类
     * @param blogQuery
     * @return
     */
    @Transient
    @Override
    public PageInfo<Blog> listBlog(Integer page, BlogQuery blogQuery) {
        if (page == null){
            page=0;
        }
        PageHelper.startPage(page,5);
        List<Blog> blogs = blogDAO.listAll(blogQuery);//分页查询符合条件的数据
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        List<Blog> blogList = new ArrayList<>();
        for (Blog b :pageInfo.getList()) {//封装tag、type等
            b = SetTypeTag(b);
            blogList.add(b);
        }
        pageInfo.setList(blogList);
        return pageInfo;

    }

    /**
     * 封装Type、Tag
     * @param b
     * @return
     */
    private Blog SetTypeTag(Blog b){
        b.setType(typeDAO.getOne(b.getTypeId()));//封装type
        List<BlogTags> blogTagList = blogTagsDAO.getOneByBlog(b.getId());//查询该博客所有tag
        List<Tag> tagList = new ArrayList<>();
        for (BlogTags bt :blogTagList) {
            tagList.add(tagDAO.getOne(bt.getTagsId()));
        }
        b.setTags(tagList);//将该博客所有tag存入Bolg的tagList
        return b;
    }

    @Override
    public Long countBlog() {
        return null;
    }

    /**
     * 将字段补充完整并保存或修改博客
     * @param blog
     */
    @Transient
    @Override
    public void saveBlog(Blog blog) {
        if (blog.getId() == null){  //新增
            blog.setTypeId(blog.getType().getId());  //将type对象的typeId存入数据
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
            blogDAO.saveReId(blog);
        }else {  //修改
            blog.setTypeId(blog.getType().getId());  //将type对象的typeId存入数据
            blog.setUpdateTime(new Date());
            blogDAO.update(blog);
            blogTagsDAO.delete(blog.getId());
        }
        Long id = blog.getId();  //插入后返回博客id
        List<BlogTags> blogTags = converToList(id, blog.getTagIds());
        if (blogTags.size() != 0){
            blogTagsDAO.saveList(blogTags);
        }
    }

    private List<BlogTags> converToList(Long blogId, String ids){
        if ("".equals(ids) && ids == null) return null;
        List<BlogTags> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null){
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                BlogTags blogTags = new BlogTags();
                blogTags.setBlogsId(blogId);
                blogTags.setTagsId(new Long(idarray[i]));
                list.add(blogTags);
            }
        }
        return list;
    }

    @Override
    public void updateBlog(Blog blog) {
        blogDAO.update(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogDAO.delete(id);
    }
}
