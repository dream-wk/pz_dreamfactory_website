package com.pz_dreamfactory.pz_dreamfactory_website.domain;
import javax.persistence.*;

@Entity
public class Visitor {
    @Id
    @Column(name = "visitor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ip")
    private String ip;

    @Column(name = "visitor_name")
    private String name;

    private Visitor() {
    }

    protected Visitor(String ip, String name) {
        this.ip = ip;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
