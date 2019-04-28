package com.zgdr.schoolhelp.repository;


import com.zgdr.schoolhelp.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *@创建 post dao
 *@创建人  fishkk
 *@创建时间2 019/4/28
 *@修改人和其它信息
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
