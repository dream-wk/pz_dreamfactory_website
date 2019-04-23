package com.pz_dreamfactory.pz_dreamfactory_website.dao;

import com.pz_dreamfactory.pz_dreamfactory_website.domain.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloggerDao extends JpaRepository<Blogger, Integer> {
    public Blogger findById(int id);

}
