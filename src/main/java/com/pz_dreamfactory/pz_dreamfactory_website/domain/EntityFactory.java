package com.pz_dreamfactory.pz_dreamfactory_website.domain;

/**
 *  统一的 实体类 创建工厂
 */
public class EntityFactory {
    public final static Visitor REPEAT_VISITOR = new Visitor("-1", "null");

    public static Visitor creatVisitor(String ip, String name){
        return new Visitor(ip, name);
    }

    public static LikeLog likeLog(String ip, int id){
        return new LikeLog(ip, id);
    }
}
