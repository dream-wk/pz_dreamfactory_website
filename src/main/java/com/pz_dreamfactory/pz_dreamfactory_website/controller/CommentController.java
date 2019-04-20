package com.pz_dreamfactory.pz_dreamfactory_website.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    /**
     * 返回评论
     * @param postId
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/get_comment")
    public HashMap getComment(int postId, int page, int size){
        HashMap result = new HashMap();

        result.put("comments", null); // 评论列表
        result.put("page", null);   // 页码
        result.put("size", null);   // 表示页码中展示的数量
        result.put("total_pages", null);   // 页码总数
        return result;
    }

    /**
     * 添加评论，重新返回评论
     * @param postId    // 博文id
     * @param floor  // 楼层
     * @param replayName    // 被回复的人的名字
     * @param context   // 评论内容
     * @param page  // 页码
     * @param size  // 页的大小
     * @return
     */
    @PostMapping(value = "/add_comment")
    public HashMap addComment(int postId, int floor,
                              String replayName, String context, int page, int size){
        HashMap result = new HashMap();


        result.put("comments", null); // 评论列表
        result.put("page", null);   // 页码
        result.put("size", null);   // 表示页码中展示的数量
        result.put("total_pages", null);   // 页码总数
        return result;
    }
}
