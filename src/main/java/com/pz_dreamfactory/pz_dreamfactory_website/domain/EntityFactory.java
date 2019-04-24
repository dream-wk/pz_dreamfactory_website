package com.pz_dreamfactory.pz_dreamfactory_website.domain;

import java.util.Date;

/**
 *  统一的 实体类 创建工厂
 */
public class EntityFactory {
    public static Admin createAdmin(String userName, String password){
        Date date = new Date();
        Admin admin = new Admin(1, date, userName);
        return admin;
    }
}
