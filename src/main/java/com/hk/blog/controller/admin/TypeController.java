package com.hk.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hk.blog.entity.Type;
import com.hk.blog.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 约定，page从第一页读取时，前端没有给page传值，故page==null
     * 前端、后台代码page从1开始，
     * 数据库查询默认是从0开始，
     * 所以调用接口时page-1
     * @param model
     * @param page
     * @return
     */
    @GetMapping("types")
    public String listpage(Model model,Integer page){
        if (page==null) page=1;
        PageHelper.startPage(page,3);
        List<Type> types = typeService.listType();
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        model.addAttribute("page", pageInfo);
        return "admin/types";
    }

    /**
     * 进入新增页面
     * @param model
     * @return
     */
    @GetMapping("types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/typesinput";
    }

    /**
     * 进入修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/typesinput";
    }

    /**
     * 添加分类
     * @param type  分类
     * @param result  向前端传回异常
     * @param attributes
     * @return
     */
    @PostMapping("types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        log.info("type:"+type.toString());
        Type typeDB = typeService.getOneByName(type.getTypename());
        if (typeDB != null){
            result.rejectValue("typename","typenameError","不能重复添加分类");
        }
        if (result.hasErrors()){
            return "admin/typesinput";
        }
        attributes.addFlashAttribute("message","操作成功");
        typeService.saveType(type);
        return "redirect:/admin/types";
    }

    /**
     * 按id修改分类
     * @param type
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("types/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes){
        log.info("type:"+type.toString());
        Type typeDB = typeService.getOneByName(type.getTypename());
        if (typeDB != null){
            result.rejectValue("typename","typenameError","不能重复添加分类");
        }
        if (result.hasErrors()){
            return "admin/typesinput";
        }
        attributes.addFlashAttribute("message","操作成功");
        typeService.updateType(type);
        return "redirect:/admin/types";
    }

    /**
     * 按id删除分类
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

}
