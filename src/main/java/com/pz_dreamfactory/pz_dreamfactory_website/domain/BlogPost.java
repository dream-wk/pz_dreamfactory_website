package com.pz_dreamfactory.pz_dreamfactory_website.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "blog_post")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_post_id")
    private int id;

    @Column(name = "blogger_fid")
    private int bloggerfId;

    @Column(name = "pz_group")
    private int group;

    @Column(name = "title")
    private String title;

    @Column(name = "report_date")
    private Date reportDate;

    @Column(name = "last_update_date")
    private Date update;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "mongdb_key")
    private String mongdbKey;

    @Column(name = "is_alive")
    private boolean alive;

    @Column(name = "can_comment")
    private boolean canComment;

    private BlogPost() {
    }

    protected BlogPost(int bloggerfId, int group, String title, Date reportDate, Date update,
                       String synopsis, String mongdbKey, boolean alive, boolean canComment) {
        this.bloggerfId = bloggerfId;
        this.group = group;
        this.title = title;
        this.reportDate = reportDate;
        this.update = update;
        this.synopsis = synopsis;
        this.mongdbKey = mongdbKey;
        this.alive = alive;
        this.canComment = canComment;
    }

    public int getId() {
        return id;
    }

    public int getBloggerfId() {
        return bloggerfId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public String getMongdbKey() {
        return mongdbKey;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isCanComment() {
        return canComment;
    }

    public void setCanComment(boolean canComment) {
        this.canComment = canComment;
    }

}
