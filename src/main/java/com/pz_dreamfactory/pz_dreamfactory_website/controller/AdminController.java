package com.pz_dreamfactory.pz_dreamfactory_website.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.HashMap;

@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    /**
     * 用户注册
     * @param userName  用户名
     * @param password  密码
     * @return  注册成功—> 登陆 注册失败—> 返回信息
     */
    @RequestMapping(value = "registered")
    public HashMap registered(String userName, String password){
        HashMap resulut = new HashMap();

        resulut.put("core", null);
        return resulut;
    }

    /**
     *  登陆
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "login")
    public HashMap login(String userName, String password){
        return null;
    }

    /**
     * 编辑该用户的博主信息表
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
    public HashMap addPost(int type, String title, String synopsis, String context){
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
     * @param blogger
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "directory")
    public HashMap directory(int page, int size){
        HashMap result = new HashMap();

        result.put("blog_posts", null); // 存储post的数组
        result.put("page", null);   // 页码
        result.put("size", null);   // 表示页码中展示的数量
        result.put("total_pages", null);   // 页码总数
        return null;
    }
}
