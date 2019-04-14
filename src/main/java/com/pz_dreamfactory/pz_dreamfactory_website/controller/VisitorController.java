package com.pz_dreamfactory.pz_dreamfactory_website.controller;

import com.pz_dreamfactory.pz_dreamfactory_website.domain.EntityFactory;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.Visitor;
import com.pz_dreamfactory.pz_dreamfactory_website.service.VisitorService;
import com.pz_dreamfactory.pz_dreamfactory_website.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping(name = "visitor")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;

    @PostMapping(name = "get_name")
    public HashMap getName(HttpServletRequest request){
        HashMap result = new HashMap();
        String ip = IpUtil.getIpAddr(request);
        Visitor visitor = visitorService.getVisitor(ip);

        if(visitor == null){
            result.put("hava_name", false);
        }else {
            result.put("have_name", true);
            result.put("name", visitor.getName());
        }

        result.put("core", 200);
        return result;
    }

    @PostMapping(name = "add_vistor")
    public HashMap addVistor(HttpServletRequest request, String name){
        HashMap result = new HashMap();
        String ip = IpUtil.getIpAddr(request);

        putVistorName(ip, name, result);
        return result;
    }

    private void putVistorName(String ip, String name, HashMap result){
        Visitor visitor = visitorService.addVisitor(ip, name);

        if(visitor == EntityFactory.REPEAT_VISITOR){
            result.put("name_repeat", true);
        }else if(visitor.getName().equals(name)){
            result.put("is_new_visitor", true);
        }else {
            result.put("is_new_visitor", false);
        }

        result.put("name", visitor.getName());
    }
}
