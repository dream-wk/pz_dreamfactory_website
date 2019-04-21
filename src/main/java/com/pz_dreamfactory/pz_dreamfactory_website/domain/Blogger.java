package com.pz_dreamfactory.pz_dreamfactory_website.domain;

import javax.persistence.*;

@Entity
public class Blogger {
    @Id
    @Column(name = "blogger_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "blogger_name")
    private String name;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "self_introduction")
    private String selfIntroduction;

    @Column(name = "motto")
    private String motto;

    @Column(name = "personal_experience")
    private String personalExperience;

    @Column(name = "recommend")
    private String recommend;

    private Blogger() {
    }

    protected Blogger(String name, String imgUrl, String selfIntroduction,
                   String motto, String personalExperience, String recommend) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.selfIntroduction = selfIntroduction;
        this.motto = motto;
        this.personalExperience = personalExperience;
        this.recommend = recommend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getPersonalExperience() {
        return personalExperience;
    }

    public void setPersonalExperience(String personalExperience) {
        this.personalExperience = personalExperience;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
