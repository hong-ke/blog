package com.hk.blog.controller;

import com.github.pagehelper.PageInfo;
import com.hk.blog.entity.Blog;
import com.hk.blog.service.BlogService;
import com.hk.blog.service.TagService;
import com.hk.blog.service.TypeService;
import com.hk.blog.vo.BlogQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 主页
     * @param page
     * @param blogQuery
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(@Param("page") Integer page, BlogQuery blogQuery, Model model) {
        PageInfo<Blog> pageInfo = blogService.listBlog(page,blogQuery);
        model.addAttribute("page",pageInfo);
        model.addAttribute("types", typeService.findTop());
        model.addAttribute("tags", tagService.findTop());
        model.addAttribute("recommendBlogs", blogService.findTop());
        return "index";
    }


    /**
     * 搜索
     * @param query
     * @param model
     * @return
     */
    @PostMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("page", blogService.findByQuery(null,query));
        model.addAttribute("query", query);
        return "search";
    }

    /**
     * 详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {

        return "_fragments :: newblogList";
    }

}
