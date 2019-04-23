package com.pz_dreamfactory.pz_dreamfactory_website.service;

import com.pz_dreamfactory.pz_dreamfactory_website.dao.BlogPostDao;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.BlogContent;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.BlogPost;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BlogPostService {
    @Autowired
    BlogPostDao blogPostDao;

    @Autowired
    MongoTemplate mongoTemplate;

    public static final Sort sortByDate = Sort.by(Sort.Direction.DESC, "reportDate");

    public Page<BlogPost> directoryByAlive(String bloggerName, int group, int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size, sortByDate);
        Page<BlogPost> reslut;

        if(bloggerName != null && group > 0){
            reslut = blogPostDao.findAllByBloggerNameLikeAndGroupAndAliveTrue(bloggerName, group, pageable);
        } else if(bloggerName != null){
            reslut = blogPostDao.findAllByBloggerNameLikeAndAliveTrue(bloggerName, pageable);
        } else if(group > 0){
            reslut = blogPostDao.findAllByGroupAndAliveTrue(group, pageable);
        } else {
            reslut = blogPostDao.findAllByAliveTrue(pageable);
        }

        return reslut;
    }

    public Page<BlogPost> directoryByBloggerIdAndAlive(int bloggerId, int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size, sortByDate);
        Page<BlogPost> reslut = blogPostDao.findAllByBloggerfIdAndAliveTrue(bloggerId, pageable);
        return reslut;
    }

    public HashMap getPostByAlive(int id){
        HashMap result = new HashMap();
        BlogPost blogPost = blogPostDao.findByIdLAndAliveTrue(id);
        if(blogPost == null){
            result.put("have_post", false);
            return result;
        }

        result.put("have_post", true);
        result.put("blog_post", blogPost);

        BlogContent blogContent = mongoTemplate.findById(blogPost.getMongdbKey(), BlogContent.class);
        result.put("blog_post_content", blogContent.getContent());

        return result;
    }

    public BlogPost addPost(int bloggerId, int type, String title, String synopsis, String context){
        BlogContent blogContent = EntityFactory.createBlogContent(context);
        blogContent = mongoTemplate.save(blogContent);

        BlogPost blogPost = EntityFactory.createBlogPost(bloggerId, type, title, synopsis, blogContent.getId());
        blogPost = blogPostDao.save(blogPost);
        return blogPost;
    }

    public void setPostAlive(int id, boolean alive){
        blogPostDao.updateAlive(id, alive);
    }

    public void setPostCanComment(int id, boolean canComment){
        blogPostDao.updateCanComment(id, canComment);
    }

    public Page<BlogPost> directoryAllByBloggerId(int bloggerId, int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size, sortByDate);
        Page<BlogPost> result = blogPostDao.findAllByBloggerfId(bloggerId, pageable);
        return result;
    }
}
