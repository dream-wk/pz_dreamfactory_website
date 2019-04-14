package com.pz_dreamfactory.pz_dreamfactory_website.domain;

import javax.persistence.*;

@Entity
public class LikeLog {
    @Column(name = "ip")
    private String ip;

    @Column(name = "blog_post_id")
    private int postId;

    private LikeLog() {
    }

    protected LikeLog(String ip, int postId) {
        this.ip = ip;
        this.postId = postId;
    }

    public String getIp() {
        return ip;
    }

    public int getPostId() {
        return postId;
    }
}
