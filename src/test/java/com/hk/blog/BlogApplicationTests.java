package com.hk.blog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hk.blog.entity.Blog;
import com.hk.blog.entity.Type;
import com.hk.blog.entity.User;
import com.hk.blog.service.BlogService;
import com.hk.blog.service.TagService;
import com.hk.blog.service.TypeService;
import com.hk.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;
    @Test
    void contextLoads() {
        PageHelper.startPage(1,3);
        List<Type> types = typeService.listType();
        PageInfo<Type> pageInfo = new PageInfo<Type>(types);
        System.out.println(pageInfo);
    }

    @Test
    void blog() {
//        Blog blog = blogService.getBlog(2L);
//        System.out.println(blog);
//        blogService.updateBlog(blog);
        //System.out.println(blogService.findTop());
        System.out.println(blogService.findByQuery(null,"前序"));
    }

    @Test
    void type() {

        System.out.println(typeService.findTop(6));
    }

    @Test
    void tag() {

        System.out.println(tagService.findTop(6));
    }
}
