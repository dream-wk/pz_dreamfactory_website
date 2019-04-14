package com.pz_dreamfactory.pz_dreamfactory_website.controller;

import com.pz_dreamfactory.pz_dreamfactory_website.domain.LikeLog;
import com.pz_dreamfactory.pz_dreamfactory_website.service.LikeLogService;
import com.pz_dreamfactory.pz_dreamfactory_website.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping(value = "like_log")
public class LikeLogController {
    @Autowired
    private LikeLogService likeLogService;

    @PostMapping(value = "get_like")
    public HashMap count(int postId, HttpServletRequest request){
        HashMap result = new HashMap();
        String ip = IpUtil.getIpAddr(request);
        boolean isLike = likeLogService.isLike(ip, postId);

        result.put("core", 200);
        result.put("count", likeLogService.count(postId));
        result.put("is_like", isLike);

        return result;
    }

    @PostMapping(value = "add_like")
    public HashMap addLike(int postId, HttpServletRequest request){
        String ip = IpUtil.getIpAddr(request);
        if(! likeLogService.isLike(ip, postId)){
            likeLogService.addLike(ip, postId);
        }

        return count(postId, request);
    }

    @PostMapping(name = "cance_like")
    public HashMap canceLike(int postId, HttpServletRequest request){
        String ip = IpUtil.getIpAddr(request);
        likeLogService.cancelLike(ip, postId);

        return count(postId, request);
    }

}
