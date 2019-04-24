package com.pz_dreamfactory.pz_dreamfactory_website.util;

import org.springframework.util.DigestUtils;

import java.util.Date;

public class MD5Password {
    public static String md5Password(String userName, String password){
        Date date = new Date();
        String before = userName + date.getTime() + password + ".pz_dreamfactory";
        String after = DigestUtils.md5DigestAsHex(before.getBytes());

        return after;
    }
}
