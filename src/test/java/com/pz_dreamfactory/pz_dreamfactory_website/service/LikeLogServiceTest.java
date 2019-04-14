package com.pz_dreamfactory.pz_dreamfactory_website.service;

import com.pz_dreamfactory.pz_dreamfactory_website.dao.LikeLogDao;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.EntityFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LikeLogServiceTest {

    @Mock
    private LikeLogDao likeLogDao;
    @InjectMocks
    private LikeLogService likeLogService;

    private String ip = "ip";
    private int postId = 0;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        when(likeLogDao.countByPostId(postId)).thenReturn(0);
        when(likeLogDao.findByIpAndPostId(ip, postId)).thenReturn(EntityFactory.creatLikeLog(ip, postId));
    }

    @Test
    public void count() {
        int count = likeLogService.count(postId);
        assertEquals(0, count);
    }

    @Test
    public void isLike() {
        boolean islike = likeLogService.isLike(ip, postId);
        assertEquals(true, islike);
    }

    @Test
    public void addLike() {
        likeLogService.addLike(ip, postId);
    }

    @Test
    public void cancelLike() {
        likeLogService.cancelLike(ip, postId);
    }
}