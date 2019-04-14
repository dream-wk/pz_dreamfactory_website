package com.pz_dreamfactory.pz_dreamfactory_website.dao;

import com.pz_dreamfactory.pz_dreamfactory_website.domain.LikeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LikeLogDao extends JpaRepository<LikeLog, Integer> {
    public int countByPostId(int postId);
    public LikeLog findByIpAndPostId(String ip, int postId);
    public void deleteByIpAndPostId(String ip, int postId);
}
