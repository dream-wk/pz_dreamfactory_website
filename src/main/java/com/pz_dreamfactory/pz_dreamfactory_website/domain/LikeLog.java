package com.pz_dreamfactory.pz_dreamfactory_website.domain;

import javax.persistence.*;

@Entity
public class LikeLog {
    @Id
    @Column(name = "like_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public int getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public int getPostId() {
        return postId;
    }
}
