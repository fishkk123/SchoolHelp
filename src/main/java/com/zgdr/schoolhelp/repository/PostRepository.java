package com.zgdr.schoolhelp.repository;


import com.zgdr.schoolhelp.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * PostDao
 *
 * @author fishkk
 * @version 1.0
 * @since 2019/4/23
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
    /**
     * @10 通过贴子类型来获得贴子列表
     * @author fishkk
     * @修改时间 2019/4/27
     *
     * @参数 post_type 贴子类型id
     * @返回值   List<Post>
     */
    @Query(value = "SELECT * FROM post WHERE post_type=?1", nativeQuery = true)
    public List<Post> findPostsByPost_type(Integer id);



    /**
     * @10 通过关键词获得贴子列表
     * @author fishkk
     * @修改时间 2019/4/27
     *
     * @参数 keyword 关键词
     * @返回值   List<Post>
     */
    @Query(value = "SELECT * FROM post WHERE title LIKE %?1%", nativeQuery = true)
    public List<Post> findPostsByKeyword(String keyword);

}
