package com.hk.blog.controller.admin;

import com.hk.blog.entity.User;
import com.hk.blog.service.UserService;
import com.hk.blog.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("admin")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, RedirectAttributes attributes){
        try{
            // MD5加密与数据库同步
            password = MD5Utils.code(password);
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            User userDB = userService.login(user);
            userDB.setPassword(null);
            session.setAttribute("user",userDB);
            return "admin/index";
        }catch (Exception e){
            e.printStackTrace();
            attributes.addAttribute("message","用户名和密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
