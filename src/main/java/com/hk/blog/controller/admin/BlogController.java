package com.hk.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.hk.blog.entity.Blog;
import com.hk.blog.entity.User;
import com.hk.blog.service.BlogService;
import com.hk.blog.service.TagService;
import com.hk.blog.service.TypeService;
import com.hk.blog.vo.BlogQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 初始化分页展示
     * @param page 第几页
     * @param blogQuery
     * @param model
     * @return
     */
    @GetMapping("blogs")
    public String blogs(@Param("page") Integer page, BlogQuery blogQuery, Model model){
        PageInfo<Blog> pageInfo = blogService.listBlog(page,blogQuery);
        model.addAttribute("page",pageInfo);
        model.addAttribute("types",typeService.listType());
        return "admin/blogs";
    }

    /**
     * 分页搜索
     * @param page
     * @param blogQuery
     * @param model
     * @return
     */
    @PostMapping("blogs/search")
    public String search(@Param("page") Integer page, BlogQuery blogQuery, Model model){
        PageInfo<Blog> pageInfo = blogService.listBlog(page, blogQuery);
        model.addAttribute("page",pageInfo);
        return "admin/blogs::blogList";
    }

    /**
     * 写博客
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String inupt(Model model){
        model.addAttribute("blog",new Blog());
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
    }

    /**
     * 修改博客(进入input页面)
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    /**
     * 发布博客（包括新增、修改后发布）
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        User user = (User) session.getAttribute("user");
        blog.setUserId(user.getId());
        blogService.saveBlog(blog);
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:/admin/blogs";
    }

    /**
     * 删除博客
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }

}
