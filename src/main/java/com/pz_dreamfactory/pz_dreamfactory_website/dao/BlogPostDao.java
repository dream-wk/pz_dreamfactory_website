package com.pz_dreamfactory.pz_dreamfactory_website.dao;

import com.pz_dreamfactory.pz_dreamfactory_website.domain.BlogPost;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.Blogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogPostDao extends JpaRepository<BlogPost, Integer> {
    public BlogPost findById(int id);

    public Page<BlogPost> findAllByGroupAndAliveTrue(int group, Pageable pageable);
    public Page<BlogPost> findAllByAliveTrue(Pageable pageable);

    public Page<BlogPost> findAllByBlogger(int id, Pageable pageable);
}
