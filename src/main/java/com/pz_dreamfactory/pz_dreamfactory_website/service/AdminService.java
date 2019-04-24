package com.pz_dreamfactory.pz_dreamfactory_website.service;

import com.pz_dreamfactory.pz_dreamfactory_website.dao.AdminDao;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.Admin;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.EntityFactory;
import com.pz_dreamfactory.pz_dreamfactory_website.util.MD5Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AdminService {
    @Autowired
    AdminDao adminDao;

    final static int USER_NAME_MIN_LENGTH = 6;
    final static int USER_NAME_MAX_LENGTH = 12;
    final static int USER_PASSWRD_MIN_LENGTH = 6;
    final static int USER_PASSWORD_MAX_LENGTH = 12;

    public HashMap registered(String userName, String password){
        HashMap result = new HashMap();
        result.put("userName_duplicated", false);
        result.put("userName_length_erro", false);
        result.put("userName_length_erro", false);
        result.put("successfylly_registered", false);

        if(userName.length() > USER_NAME_MAX_LENGTH || userName.length() < USER_NAME_MIN_LENGTH){
            result.put("userName_length_erro", true);
            return result;
        }
        if(password.length() > USER_PASSWORD_MAX_LENGTH || password.length() < USER_PASSWRD_MIN_LENGTH){
            result.put("userName_length_erro", true);
            return result;
        }

        int count = adminDao.countAdminByUserName(userName);
        if(count > 0){
            result.put("userName_duplicated", true);
            return result;
        }

        Admin user = EntityFactory.createAdmin(userName, password);
        user = adminDao.save(user);
        String md5Passwird = MD5Password.md5Password(userName, password);
        adminDao.updataByPassword(password, user.getId());

        result.put("successfylly_registered", true);
        result.put("user", user);

        return result;
    }

    public Admin login(String userName, String password){
        Admin user = adminDao.findByUserNameAndPassword(userName, password);

        return user;
    }


}
