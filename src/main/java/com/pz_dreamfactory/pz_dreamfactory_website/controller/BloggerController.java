package com.pz_dreamfactory.pz_dreamfactory_website.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "blogger")
public class BloggerController {

    /**
     * 按id 寻址博主信息
     * @param id
     * @return
     */
    @PostMapping(value = "get_blogger")
    public HashMap getBlogger(int id){
        HashMap result = new HashMap();

        result.put("blogger", null);
        return result;
    }

    /**
     * 按博主id 索引博文目录
     * @param id
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "directory")
    public HashMap directory(int id, int page, int size){
        HashMap result = new HashMap();

        result.put("blog_posts", null); // 存储post的数组
        result.put("page", null);   // 页码
        result.put("size", null);   // 表示页码中展示的数量
        result.put("total_pages", null);   // 页码总数
        return null;
    }
}
