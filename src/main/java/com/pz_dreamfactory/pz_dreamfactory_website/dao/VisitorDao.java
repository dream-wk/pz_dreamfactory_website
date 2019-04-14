package com.pz_dreamfactory.pz_dreamfactory_website.dao;

import com.pz_dreamfactory.pz_dreamfactory_website.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorDao extends JpaRepository<Visitor, Integer> {
    public Visitor findByIp(String ip);
    public Visitor findByName(String name);
}
