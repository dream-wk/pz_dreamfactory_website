package com.pz_dreamfactory.pz_dreamfactory_website.controller;

import com.pz_dreamfactory.pz_dreamfactory_website.dao.AdminDao;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.Admin;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.BlogPost;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.Blogger;
import com.pz_dreamfactory.pz_dreamfactory_website.service.AdminService;
import com.pz_dreamfactory.pz_dreamfactory_website.service.BlogPostService;
import com.pz_dreamfactory.pz_dreamfactory_website.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @Autowired
    BloggerService bloggerService;
    @Autowired
    BlogPostService blogPostService;

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
            Admin user = (Admin) registered.get("user");
            updataLoginStatus(request, user.getId(), user.getBloggerId());
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
            updataLoginStatus(request, user.getId(), user.getBloggerId());
            result.put("successfylly_login", true);
            result.put("user", user);
        }else {
            result.put("successfylly_login", false);
        }

        return result;
    }

    /**
     * 编辑该用户的博主信息表
     * TODO 上传和保存图片技术未实现
     * 从session 中取到登陆状态下的管理员id 和 关联的博主id
     * 如果没有博主id， 就为其创建一个 关联的博主信息
     * 当传入的值为null（没有传入） 则表示对应的信息不更新
     * @param name  名字
     * @param img   图片
     * @param selfIntroduction  自我介绍
     * @param motto 座右铭
     * @param personalExperience    个人简历
     * @param recommend 建议
     * @return
     */
    @PostMapping(value = "edit_blogger")
    public HashMap edit(String name, CommonsMultipartFile img, String selfIntroduction, String motto,
                        String personalExperience, String recommend){
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
    public HashMap addPost(HttpServletRequest request, int type, String title, String synopsis, String context){
        HashMap result = new HashMap();

        int bloggerId = (int) request.getSession().getAttribute("blogger");
        BlogPost post = blogPostService.addPost(bloggerId, type, title, synopsis, context);
        result.put("blog_post", post);
        return result;
    }

    /**
     * 删除评论
     * TODO 等待评论模块完成
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
        blogPostService.setPostAlive(id, display);
        HashMap result = new HashMap();
        result.put("core", 200);

        return result;
    }

    /**
     * 设置博文是否可评论
     * @param id
     * @param canCommnent
     * @return
     */
    @PostMapping(value = "set_comment_blog_post")
    public HashMap setCommentBlogPost(int id, boolean canCommnent){
        HashMap result = new HashMap();
        blogPostService.setPostCanComment(id, canCommnent);

        result.put("core", 200);
        return result;
    }

    /**
     * 博文目录
     * @param request
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "directory")
    public HashMap directory(HttpServletRequest request, int page, int size){
        HashMap result = new HashMap();

        int bloggerId = (int) request.getSession().getAttribute("blogger");
        Page<BlogPost> posts = blogPostService.directoryAllByBloggerId(bloggerId, page, size);

        result.put("blog_posts", posts.getContent()); // 存储post的数组
        result.put("page", posts.getNumber());   // 页码
        result.put("size", posts.getNumberOfElements());   // 表示页码中展示的数量
        result.put("total_pages", posts.getTotalPages());   // 页码总数
        return result;
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

    /**
     * 向Session中延长登陆状态的存活时间
     * @param request
     */
    private void updataLoginStatus(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("login_status", session.getAttribute("login_status"));
        session.setAttribute("blogger", session.getAttribute("blogger"));
        session.setMaxInactiveInterval(KEEP_LOGIN_SESSION_TIME);
    }

    /**
     * 向Session 中添加新的 登陆状态
     * @param request
     * @param adminId
     * @param bloggerId
     */
    private void updataLoginStatus(HttpServletRequest request, int adminId, int bloggerId){
        HttpSession session = request.getSession();
        session.setAttribute("login_status", adminId);
        session.setAttribute("blogger", bloggerId);
        session.setMaxInactiveInterval(KEEP_LOGIN_SESSION_TIME);
    }
}
