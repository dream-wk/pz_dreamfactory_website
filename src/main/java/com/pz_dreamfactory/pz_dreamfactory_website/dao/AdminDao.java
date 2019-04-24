package com.pz_dreamfactory.pz_dreamfactory_website.dao;

import com.pz_dreamfactory.pz_dreamfactory_website.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AdminDao extends JpaRepository<Admin, Integer> {
    @Query(nativeQuery = true,
        value = "select a.* from admin a where user_name = ?1 and user_password = ?2 ;")
    public Admin findByUserNameAndPassword(String userNaem, String password);

    @Transactional
    @Query(nativeQuery = true,
        value = "update admin a set a.user_password = ?1 and a.admin_id = ? 2 ;")
    public void updataByPassword(String password, int id);
}
