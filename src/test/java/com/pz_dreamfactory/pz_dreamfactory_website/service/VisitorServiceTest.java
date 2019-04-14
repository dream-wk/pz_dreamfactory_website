package com.pz_dreamfactory.pz_dreamfactory_website.service;

import com.pz_dreamfactory.pz_dreamfactory_website.dao.VisitorDao;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.EntityFactory;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.Visitor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VisitorServiceTest {
    @Mock
    private VisitorDao visitorDao;  // 创建被引入到测试类的 mock对象 bean。

    @InjectMocks
    private VisitorService visitorService;  // 创建被测试的类，并引入mock对象bean。

    private String hasIp = "has_ip", hasName = "has_name";
    private String newIp = "new_Ip", newName = "newName";

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this); // 初始化Mock

        // 模拟名字重复的时候 和 名字没有重复的时候
        when(visitorDao.findByName(hasName)).thenReturn(EntityFactory.REPEAT_VISITOR);
        when(visitorDao.findByName(newName)).thenReturn(null);
        // 模拟ip已经命名 和 ip 没有命名
        when(visitorDao.findByIp(hasIp)).thenReturn(EntityFactory.creatVisitor(hasIp, hasName));
        when(visitorDao.findByIp(newIp)).thenReturn(null);
        // 模拟成功调用save
        when(visitorDao.save(any())).thenReturn(EntityFactory.creatVisitor(newIp, newName));
    }

    @Test
    public void addVisitor() {
        Visitor testRepeatName = visitorService.addVisitor(newIp, hasName);
        Visitor testHasIp = visitorService.addVisitor(hasIp, newName);
        Visitor testNew = visitorService.addVisitor(newIp, newName);

        assertEquals(EntityFactory.REPEAT_VISITOR, testRepeatName);
        assertEquals(hasIp, testHasIp.getIp());
        assertEquals(newIp, testNew.getIp());
    }

    @Test
    public void getVisitor() {
        Visitor testHasIp = visitorService.getVisitor(hasIp);
        Visitor testNewIp = visitorService.getVisitor(newIp);

        assertEquals(hasIp, testHasIp.getIp());
        assertEquals(null, testNewIp);
    }
}
