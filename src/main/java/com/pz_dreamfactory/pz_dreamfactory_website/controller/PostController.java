package com.pz_dreamfactory.pz_dreamfactory_website.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "blogger_post")
public class PostController {

    /**
     * 目录
     *  blogerName group 某一个值为空的时候，表示不需要为这个值做索引。
     * @param  bloggerName  可能为空
     * @param group 可能为空
     * @param page  表示页码
     * @param size  表示页码中展示的数量
     * @return
     */
    @PostMapping(value = "directory")
    public HashMap directory(String bloggerName, int group, int page, int size){
        HashMap result = new HashMap();

        result.put("blog_posts", null); // 存储post的数组
        result.put("page", null);   // 页码
        result.put("size", null);   // 表示页码中展示的数量
        result.put("total_ages", null);   // 页码总数
        return null;
    }

    @PostMapping(value = "blog_post")
    public HashMap post(int id){
        HashMap result = new HashMap();

        result.put("blogger", null); // 博主信息 / 博主实体
        result.put("blog_post", null); // 博文信息 / 博文实体
        result.put("blog_post_content", null); // 博文内容 / 博文内容实体
        return result;
    }
}
