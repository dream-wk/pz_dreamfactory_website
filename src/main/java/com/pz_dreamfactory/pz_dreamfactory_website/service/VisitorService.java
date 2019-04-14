package com.pz_dreamfactory.pz_dreamfactory_website.service;

import com.pz_dreamfactory.pz_dreamfactory_website.dao.VisitorDao;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.EntityFactory;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorService {
    @Autowired
    private VisitorDao visitorDao;

    /**
     * 从ip中查询使用该ip被注册，如果没有，使用name注册一个新的ip
     * 如果name被注册，返回 EntityFactory.REPEAT_VISITOR；
     * @param ip
     * @param name
     * @return
     */
    public Visitor addVisitor(String ip, String name){
        Visitor repeatName = visitorDao.findByName(name);
        if(repeatName != null){
            return EntityFactory.REPEAT_VISITOR;
        }

        Visitor result = visitorDao.findByIp(ip);

        if(result == null){
            result = visitorDao.save(EntityFactory.creatVisitor(ip, name));
        }
        return result;
    }

    /**
     * 从ip中查询一个游客对象。因为会存在返回null，建议使用getVisitor(ip, name)
     * @param ip
     * @return  当ip没注册的时候，返回null
     */
    public Visitor getVisitor(String ip){
        return visitorDao.findByIp(ip);
    }
}
