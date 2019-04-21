package com.pz_dreamfactory.pz_dreamfactory_website.dao;

import com.pz_dreamfactory.pz_dreamfactory_website.domain.BlogPost;
import com.pz_dreamfactory.pz_dreamfactory_website.domain.Blogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogPostDao extends JpaRepository<BlogPost, Integer> {
    /**
     * 通过id查询存活状态的博文
     * @param id
     * @return
     */
    @Query(nativeQuery = true,
            value = "select p.*, b.blogger_name from blog_post p, blogger b " +
                    "where p.blog_post_id = ?1 and p.blogger_fid = b.blogger_id and p.is_alive = true;")
    public BlogPost findByIdAndLAndAliveTrue(int id);

    /**
     * 通过 字段 查询类似字段的作者名的存活的博文目录
     * @param s1
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true,
        value = "select p.*, b.blogger_name from blog_post p, blogger b " +
                "where b.blogger_name like ?1 and p.blogger_fid = b.blogger_id and p.is_alive = true;" +
                "\n#pageable\n")
    public Page<BlogPost> findAllByBloggerNameLikeAndAliveTrue(String s1, Pageable pageable);

    /**
     * 通过 组别（int） 查询组别的存活的博文目录
     * @param group
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true,
        value = "select p.*, b.blogger_name from blog_post p, blogger b " +
                "where p.pz_group = ?1 and p.blogger_fid = b.blogger_id and p.is_alive = true;" +
                "\n#pageable\n")
    public Page<BlogPost> findAllByGroupAndAliveTrue(int group, Pageable pageable);

    /**
     * 查询所有存活的博文目录
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true,
            value = "select p.*, b.blogger_name from blog_post p, blogger b " +
                    "where p.blogger_fid = b.blogger_id and p.is_alive = true;" +
                    "\n#pageable\n")
    public Page<BlogPost> findAllByAliveTrue(Pageable pageable);

    @Query(nativeQuery = true,
            value = "select p.*, b.blogger_name from blog_post p, blogger b " +
                    "where p.pz_group = ?1 and p.pz_group = ?2" +
                    "and p.blogger_fid = b.blogger_id and p.is_alive = true;" +
                    "\n#pageable\n")
    public Page<BlogPost> findAllByBloggerNameLikeAndGroupAndAliveTrue(String s1, int group, Pageable pageable);

    /**
     * 通过博主id 查询所有该博主(包含非存活)的博文目录
     * @param id
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true,
            value = "select p.*, b.blogger_name from blog_post p, blogger b " +
                    "where b.bloger_fid = ?1 and p.blogger_fid = b.blogger_id and p.is_alive = true;" +
                    "\n#pageable\n")
    public Page<BlogPost> findAllByBloggerfId(int id, Pageable pageable);
    
}
