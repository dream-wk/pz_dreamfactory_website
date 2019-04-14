package com.pz_dreamfactory.pz_dreamfactory_website.dao;

import com.pz_dreamfactory.pz_dreamfactory_website.domain.EntityFactory;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.Visitor;
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
public class VisitorDaoTest {
    @Autowired
    private VisitorDao visitorDao;

    Visitor v1, v2;

    @Before
    public void befor(){
        v1 = EntityFactory.creatVisitor("test1Ip", "test1Name");
        v2 = EntityFactory.creatVisitor("test2Ip", "test2Name");

        visitorDao.save(v1);
    }

    @After
    public void after(){
        visitorDao.deleteAll();
    }

    @Test
    public void testFindByIp(){
        Visitor visitor1 = visitorDao.findByIp(v1.getIp()); // 测试数据库中存在的对象
        Visitor visitor2 = visitorDao.findByIp(v2.getIp()); // 测试数据库中不存在的对象

        assertEquals(v1.getIp(), visitor1.getIp());
        assertEquals(v1.getName(), visitor1.getName());
        assertEquals(null, visitor2);
    }

    @Test
    public void testFindByName(){
        Visitor visitor1 = visitorDao.findByName(v1.getName()); // 测试数据库中存在的对象
        Visitor visitor2 = visitorDao.findByName(v2.getName()); // 测试数据库中不存在的对象

        assertEquals(v1.getIp(), visitor1.getIp());
        assertEquals(v1.getName(), visitor1.getName());
        assertEquals(null, visitor2);
    }
}