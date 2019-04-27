package com.pz_dreamfactory.pz_dreamfactory_website.domain;

import java.sql.Date;

/**
 *  统一的 实体类 创建工厂
 */
public class EntityFactory {
    public static BlogPost createBlogPost(int blogger, int group, String title, String synopsis, String mongdbKey){
        Date date = new Date(new java.util.Date().getTime());
        return new BlogPost(blogger, group, title, date, date, synopsis, mongdbKey, true, true);
    }

    public static Blogger createBlogger(String name, String imgUrl, String selfIntroduction,
                                        String motto, String personalExperience, String recommend){
        return new Blogger(name, imgUrl, selfIntroduction, motto, personalExperience, recommend);
    }

    public static BlogContent createBlogContent(String content){
        return new BlogContent(content);
    }

    public static Admin createAdmin(String userName, String password){
        java.util.Date date = new java.util.Date();
        Admin admin = new Admin(1, date, userName);
        return admin;
    }
}
