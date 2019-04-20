package com.pz_dreamfactory.pz_dreamfactory_website.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * TOOD
 */
@RestController
@RequestMapping(value = "/visitor")
public class VisitorController {
    /**
     * 取出游客的名字
     * @return
     */
    @PostMapping(value = "/get_name")
    public HashMap getName(){
        HashMap result = new HashMap();

        result.put("name", null);   //返回游客的名字
        return result;
    }

    /**
     * 添加游客的名字，如果ip已经命名了，返回已命名的名字。
     * @param naem
     * @return
     */
    @PostMapping(value = "/add_name")
    public HashMap addName(String naem){
        HashMap result = new HashMap();

        result.put("name", null);// 返回游客的名字;
        return result;
    }
}
