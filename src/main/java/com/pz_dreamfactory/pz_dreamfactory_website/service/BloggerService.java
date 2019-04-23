package com.pz_dreamfactory.pz_dreamfactory_website.service;

import com.pz_dreamfactory.pz_dreamfactory_website.dao.BloggerDao;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.Blogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
public class BloggerService {
    @Autowired
    BloggerDao bloggerDao;

    public Blogger get(int id){
        return bloggerDao.findById(id);
    }

    public Blogger edit(int id, String name, String imgUrl, String selfIntroduction, String motto,
                        String personalExperience, String recommend){
        Blogger blogger = bloggerDao.findById(id);

        blogger.setName(name);
        blogger.setSelfIntroduction(selfIntroduction);
        blogger.setMotto(motto);
        blogger.setPersonalExperience(personalExperience);
        blogger.setRecommend(recommend);
        if(imgUrl != null){
            blogger.setImgUrl(imgUrl);
        }

        blogger = bloggerDao.save(blogger);
        return blogger;
    }

    public Page<Blogger> directory(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Blogger> bloggerPage = bloggerDao.findAll(pageable);
        return bloggerPage;
    }
}
