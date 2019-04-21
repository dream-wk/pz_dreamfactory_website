package com.pz_dreamfactory.pz_dreamfactory_website.dao;

import com.pz_dreamfactory.pz_dreamfactory_website.domain.BlogPost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogPostDaoTest {

    @Autowired
    BlogPostDao blogPostDao;


    @Test
    public void test2(){
        Pageable pageable = PageRequest.of(0, 2);
        Page<BlogPost> b1 = blogPostDao.findAllByBloggerNameLikeAndAliveTrue("%g%", pageable);
        assertEquals(1, b1.getTotalElements());
        assertEquals("blogger", b1.getContent().get(0).getBloggerName());
    }
}