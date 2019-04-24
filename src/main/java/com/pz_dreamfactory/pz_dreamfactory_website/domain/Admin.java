package com.pz_dreamfactory.pz_dreamfactory_website.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Admin {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "authoryity_level")
    private int level;

    @Column(name = "registration_time")
    private Date registrationTime;

    @Column(name = "blogger_fid")
    private int bloggerId;

    @Column(name = "user_name")
    private String userName;

    private Admin() {
    }

    protected Admin(int level, Date registrationTime, String userName) {
        this.level = level;
        this.registrationTime = registrationTime;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public String getUserName() {
        return userName;
    }

    public int getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(int bloggerId) {
        this.bloggerId = bloggerId;
    }
}
