package com.hk.blog.controller;

import com.hk.blog.service.BlogService;
import com.hk.blog.service.TagService;
import com.hk.blog.vo.BlogQuery;
import com.hk.blog.vo.TagTopVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@Param("page") Integer page, BlogQuery blogQuery, @PathVariable Long id, Model model) {
        List<TagTopVO> tags = tagService.findTop(10000);
        if (id == -1) {
           id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(page, blogQuery));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
