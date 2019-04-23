package com.pz_dreamfactory.pz_dreamfactory_website.controller;

import com.pz_dreamfactory.pz_dreamfactory_website.domain.BlogPost;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.Blogger;
import com.pz_dreamfactory.pz_dreamfactory_website.service.BlogPostService;
import com.pz_dreamfactory.pz_dreamfactory_website.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "blogger")
public class BloggerController {
    @Autowired
    BloggerService bloggerService;

    /**
     * 按id 寻址博主信息
     * @param id
     * @return
     */
    @PostMapping(value = "get_blogger")
    public HashMap getBlogger(int id){
        HashMap result = new HashMap();

        Blogger blogger = bloggerService.get(id);

        result.put("blogger", blogger);
        return result;
    }

    /**
     * 按博主id 索引博主目录
     * @param id
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "directory")
    public HashMap directory(int id, int page, int size){
        HashMap result = new HashMap();

        Page<Blogger> bloggerPage = bloggerService.directory(page, size);

        result.put("blog_posts", bloggerPage.getContent()); // 存储post的数组
        result.put("page", bloggerPage.getNumber());   // 页码
        result.put("size", bloggerPage.getNumberOfElements());   // 表示页码中展示的数量
        result.put("total_pages", bloggerPage.getTotalPages());   // 页码总数
        return result;
    }
}
