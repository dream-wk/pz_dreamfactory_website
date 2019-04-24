package com.pz_dreamfactory.pz_dreamfactory_website.controller;

import com.pz_dreamfactory.pz_dreamfactory_website.dao.AdminDao;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.Admin;
import com.pz_dreamfactory.pz_dreamfactory_website.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    final static int KEEP_LOGIN_SESSION_TIME = 60 * 60 * 3;

    @Autowired
    AdminService adminService;

    /**
     * 用户注册
     * @param userName  用户名
     * @param password  密码
     * @return  注册成功—> 登陆 注册失败—> 返回信息
     */
    @RequestMapping(value = "registered")
    public HashMap registered(String userName, String password, HttpServletRequest request){
        HashMap resulut = new HashMap();

        HashMap registered = adminService.registered(userName, password);
        if((boolean) registered.get("successfylly_registered")){
            updataLoginStatus(request);
        }

        resulut.putAll(registered);
        return resulut;
    }

    /**
     *  登陆
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "login")
    public HashMap login(String userName, String password, HttpServletRequest request){
        HashMap result = new HashMap();

        Admin user = adminService.login(userName, password);

        if(user != null){
            updataLoginStatus(request);
            result.put("successfylly_login", true);
            result.put("user", user);
        }else {
            result.put("successfylly_login", false);
        }

        return result;
    }

    /**
     * 编辑该用户的博主信息表
     * @param name
     * @param img
     * @param 自我介绍
     * @param motto
     * @param personalExperience
     * @param recommend
     * @return
     */
    @PostMapping(value = "edit_blogger")
    public HashMap edit(String name, CommonsMultipartFile img, String 自我介绍, String motto, String personalExperience, String recommend){
        HashMap result = new HashMap();

        result.put("blogger", null);
        return result;
    }

    /**
     * 上传博文
     * @param type 博文组别/类型
     * @param title 标题
     * @param synopsis 简介
     * @param context 正文
     * @return 返回博文
     */
    @PostMapping(value = "add_post")
    public HashMap addPost(String type, String title, String synopsis, String context){
        HashMap result = new HashMap();

         result.put("blog_post", null);
         return result;
    }

    /**
     * 删除评论
     * @param ids
     * @return
     */
    @PostMapping(value = "delt_comment")
    public HashMap deltComment(int[] ids){
        HashMap result = new HashMap();

        result.put("comments", null);
        return result;
    }

    /**
     * 设置博文是否可见
     * @param id
     * @param display
     * @return
     */
    @PostMapping(value = "set_display_blog_post")
    public HashMap SetDisplayBlogPost(int id, boolean display){
        return null;
    }

    /**
     * 设置博文是否可评论
     * @param id
     * @param canCommnent
     * @return
     */
    @PostMapping(value = "set_comment_blog_post")
    public HashMap setCommentBlogPost(int id, boolean canCommnent){
        return null;
    }

    /**
     * 博文目录
     * @param bloggerName
     * @param group
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "directory")
    public HashMap directory(String bloggerName, int group, int page, int size){
        HashMap result = new HashMap();

        result.put("blog_posts", null); // 存储post的数组
        result.put("page", null);   // 页码
        result.put("size", null);   // 表示页码中展示的数量
        result.put("total_pages", null);   // 页码总数
        return null;
    }

    /**
     * 检查Session 中是否存在登陆状态，没有返回falsh 有则延长状态存活时间。
     * @param request
     * @return
     */
    private boolean isLoginStatus(HttpServletRequest request){
        HttpSession session = request.getSession();
        String loginStatus = (String) session.getAttribute("login_status");

        if(loginStatus == null){
            return false;
        }
        session.setAttribute("login_status", loginStatus);
        session.setMaxInactiveInterval(KEEP_LOGIN_SESSION_TIME);
        return true;
    }

    private void updataLoginStatus(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("login_status", "true");
        session.setMaxInactiveInterval(KEEP_LOGIN_SESSION_TIME);
    }
}
