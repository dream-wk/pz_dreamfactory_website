package com.pz_dreamfactory.pz_dreamfactory_website.controller;

import com.pz_dreamfactory.pz_dreamfactory_website.domain.EntityFactory;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.Visitor;
import com.pz_dreamfactory.pz_dreamfactory_website.service.VisitorService;
import com.pz_dreamfactory.pz_dreamfactory_website.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * TOOD
 */
@RestController
@RequestMapping(value = "/visitor")
public class VisitorController {
    @Autowired
    VisitorService visitorService;

    /**
     * 取出游客的名字
     * @return
     */
    @PostMapping(value = "/get_name")
    public HashMap getName(HttpServletRequest request){
        HashMap result = new HashMap();

        String ip = IpUtil.getIpAddr(request);
        Visitor visitor = visitorService.getVisitor(ip);

        if(visitor != null){
            result.put("name", visitor.getName());
        }else {
            result.put("name", null);
        }

        return result;
    }

    /**
     * 添加游客的名字，如果ip已经命名了，返回已命名的名字。
     * @param name
     * @return
     */
    @PostMapping(value = "/add_name")
    public HashMap addName(String name, HttpServletRequest request){
        HashMap result = new HashMap();

        String ip = IpUtil.getIpAddr(request);
        Visitor visitor = visitorService.getVisitor(ip);

        if(visitor == null){
            visitor = visitorService.addVisitor(ip, name);
            result.put("name", visitor.getName());
        }

        result.put("name", visitor.getName());
        return result;
    }
}
