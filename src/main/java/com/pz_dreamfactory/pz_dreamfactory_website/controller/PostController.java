package com.pz_dreamfactory.pz_dreamfactory_website.controller;

import com.pz_dreamfactory.pz_dreamfactory_website.domain.BlogPost;
import com.pz_dreamfactory.pz_dreamfactory_website.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "blogger_post")
public class PostController {
    @Autowired
    private BlogPostService blogPostService;

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
        Page<BlogPost> blogPostPage = blogPostService.directoryByAlive(bloggerName, group, page, size);

        result.put("blog_posts", blogPostPage.getContent()); // 存储post的数组
        result.put("page", blogPostPage.getNumber());   // 页码
        result.put("size", blogPostPage.getNumberOfElements());   // 表示页码中展示的数量
        result.put("total_pages", blogPostPage.getTotalPages());   // 页码总数
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
    public HashMap directoryByBloggerId(int id, int page, int size){
        HashMap result = new HashMap();

        Page<BlogPost> blogPostPage = blogPostService.directoryByBloggerIdAndAlive(id, page, size);

        result.put("blog_posts", blogPostPage.getContent()); // 存储post的数组
        result.put("page", blogPostPage.getNumber());   // 页码
        result.put("size", blogPostPage.getNumberOfElements());   // 表示页码中展示的数量
        result.put("total_pages", blogPostPage.getTotalPages());   // 页码总数
        return result;
    }

    /**
     *  按id返回博文
     * @param id
     * @return
     */
    @PostMapping(value = "blog_post")
    public HashMap post(int id){
        HashMap result = new HashMap();

        HashMap post = blogPostService.getPostByAlive(id);
        result.putAll(post);

        result.put("blogger", null); // 博主信息 / 博主实体
        result.put("blog_post", null); // 博文信息 / 博文实体
        result.put("blog_post_content", null); // 博文内容 / 博文内容实体
        return result;
    }


}
