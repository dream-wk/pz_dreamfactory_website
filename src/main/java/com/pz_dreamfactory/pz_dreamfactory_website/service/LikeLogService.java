
package com.pz_dreamfactory.pz_dreamfactory_website.service;

import com.pz_dreamfactory.pz_dreamfactory_website.dao.LikeLogDao;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.EntityFactory;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.LikeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeLogService {
    @Autowired
    private LikeLogDao likeLogDao;

    /**
     * 添加点赞
     * @param ip    游客的ip地址
     * @param postId    被点赞的博文id
     * @return  当点赞添加到日记中，返回true，否则返回false
     */
    public void addLike(String ip, int postId){
        likeLogDao.save(EntityFactory.likeLog(ip, postId));
    }

    /**
     * 取消点赞
     * @param ip    游客的ip地址
     * @param postId    被点赞的博文id
     * @return  当点赞从日记中取消，返回true，否则返回false
     */
    public void cancelLike(String ip, int postId){
        likeLogDao.deleteByIpAndPostId(ip, postId);
    }

    /**
     * 从log计数某个博文被点赞的次数
     * @param postId    博文id
     * @return  博文被点赞的次数
     */
    public int count(int postId){
        return likeLogDao.countByPostId(postId);
    }

    public boolean isLike(String ip, int postId){
        LikeLog likeLog = likeLogDao.findByIpAndPostId(ip, postId);
        return likeLog != null;
    }

}
