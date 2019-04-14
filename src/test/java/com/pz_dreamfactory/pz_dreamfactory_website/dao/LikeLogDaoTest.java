package com.pz_dreamfactory.pz_dreamfactory_website.dao;

import com.pz_dreamfactory.pz_dreamfactory_website.domain.EntityFactory;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.LikeLog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LikeLogDaoTest {
    @Autowired
    LikeLogDao likeLogDao;

    LikeLog l1, l2, l3, l4;

    @Before
    public void before(){
        l1 = EntityFactory.creatLikeLog("test1Ip", 1);
        l2 = EntityFactory.creatLikeLog("test2Ip", 1);
        l3 = EntityFactory.creatLikeLog("test3Ip", 2);
        l4 = EntityFactory.creatLikeLog("test4Ip", 3);

        likeLogDao.save(l1);
        likeLogDao.save(l2);
        likeLogDao.save(l3);
    }

    @After
    public void after(){
        likeLogDao.deleteAll();
    }

    @Test
    public void countByPostId(){
        int count1 = likeLogDao.countByPostId(1);
        int count2 = likeLogDao.countByPostId(2);
        int count3 = likeLogDao.countByPostId(3);

        assertEquals(2, count1);
        assertEquals(1, count2);
        assertEquals(0, count3);
    }

    @Test
    public void findByAndPostId(){
        LikeLog likeLog1 = likeLogDao.findByIpAndPostId(l1.getIp(), l1.getPostId()); // 测试2个值都匹配

        LikeLog likeLog2 = likeLogDao.findByIpAndPostId(l2.getIp(), 3); // 测试postid值不匹配
        LikeLog likeLog3 = likeLogDao.findByIpAndPostId("testnull", l3.getPostId()); // 测试ip值不匹配
        LikeLog likeLog4 = likeLogDao.findByIpAndPostId(l4.getIp(), l4.getPostId()); // 测试2个值都不匹配

        assertEquals(l1.getIp(), likeLog1.getIp());
        assertEquals(l1.getPostId(), likeLog1.getPostId());

        assertEquals(null, likeLog2);
        assertEquals(null, likeLog3);
        assertEquals(null, likeLog4);
    }

    @Test
    public void deleteByIpAndPostId(){
        likeLogDao.deleteByIpAndPostId(l1.getIp(), l1.getPostId()); // 测试2个值都匹配

        likeLogDao.deleteByIpAndPostId(l2.getIp(), 3); // 测试postid值不匹配
        likeLogDao.deleteByIpAndPostId("testnull", l3.getPostId()); // 测试ip值不匹配
        likeLogDao.deleteByIpAndPostId(l4.getIp(), l4.getPostId()); // 测试2个值都不匹配

        LikeLog likeLog1 = likeLogDao.findByIpAndPostId(l1.getIp(), l1.getPostId());
        LikeLog likeLog2 = likeLogDao.findByIpAndPostId(l2.getIp(), l2.getPostId());
        LikeLog likeLog3 = likeLogDao.findByIpAndPostId(l3.getIp(), l3.getPostId());
        LikeLog likeLog4 = likeLogDao.findByIpAndPostId(l4.getIp(), l4.getPostId());

        assertEquals(null, likeLog1);
        assertEquals(l2.getPostId(), likeLog2.getPostId());
        assertEquals(l3.getPostId(), likeLog3.getPostId());
        assertEquals(null, likeLog4);
    }
}