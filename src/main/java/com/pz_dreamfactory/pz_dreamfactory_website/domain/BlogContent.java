package com.pz_dreamfactory.pz_dreamfactory_website.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class BlogContent {
    @Id
    private String id;

    private String content;

    private BlogContent() {
    }

    protected BlogContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
