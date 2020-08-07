package com.hk.blog.controller;

import com.hk.blog.entity.Type;
import com.hk.blog.service.BlogService;
import com.hk.blog.service.TypeService;
import com.hk.blog.vo.BlogQuery;
import com.hk.blog.vo.TypeTopVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    /**
     * 分类首页
     * @param page
     * @param blogQuery
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}")
    public String types(@Param("page") Integer page, BlogQuery blogQuery, @PathVariable Long id, Model model) {
        List<TypeTopVO> types = typeService.findTop(10000);
        if (id == -1) {
           id = types.get(0).getId();
        }
        blogQuery.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlog(page, blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
