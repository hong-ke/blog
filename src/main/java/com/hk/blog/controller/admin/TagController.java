package com.hk.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hk.blog.entity.Tag;
import com.hk.blog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 约定，page从第一页读取时，前端没有给page传值，故page==null
     * 前端、后台代码page从1开始，
     * 数据库查询默认是从0开始，
     * 所以调用接口时page-1
     * @param model
     * @param page
     * @return
     */
    @GetMapping("tags")
    public String listpage(Model model,Integer page){
        if (page==null) page=1;
        PageHelper.startPage(page,3);
        List<Tag> types = tagService.listTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(types);
        model.addAttribute("page", pageInfo);
        return "admin/tags";
    }

    /**
     * 渲染页面
     * @param model
     * @return
     */
    @GetMapping("tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    /**
     *进入修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }

    /**
     * 新增tag
     * @param tag
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        log.info("tag:"+tag.toString());
        Tag typeDB = tagService.getOneByName(tag.getTagname());
        if (typeDB != null){
            result.rejectValue("tagname","typenameError","不能重复添加分类");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        attributes.addFlashAttribute("message","操作成功");
        tagService.saveTag(tag);
        return "redirect:/admin/tags";
    }

    /**
     * 更新tag
     * @param tag
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes attributes){
        log.info("tag:"+tag.toString());
        Tag typeDB = tagService.getOneByName(tag.getTagname());
        if (typeDB != null){
            result.rejectValue("tagname","typenameError","不能重复添加分类");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        attributes.addFlashAttribute("message","操作成功");
        tagService.updateTag(tag);
        return "redirect:/admin/tags";
    }

    /**
     * 删除tag
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
