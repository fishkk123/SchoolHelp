package com.zgdr.schoolhelp.repository;

import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.zgdr.schoolhelp.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * @创建者 fishkk
 * @创建时间 描述
 */
public interface CommentRepository extends JpaRepository<Comment, Integer>{
      /**
       * @10 放回贴子的评论列表
       * @author fishkk
       * @修改时间 2019/4/27
       *
       * @参数 post_id 贴子id
       * @返回值   评论列表List<Comment>
       */
      @Query(value = "SELECT * FROM comment WHERE post_id=?1", nativeQuery = true)
      public List<Comment> findName2(Integer id);

      /**
       * @10获取贴子的评论用户列表
       * @author fishkk
       * @修改时间 2019/4/27
       *
       * @参数  post_id 贴子id
       * @返回值   Set 评论用户id集合
       */
      @Query(value = "SELECT user_id FROM comment WHERE post_id=?1", nativeQuery = true)
      public Set<Integer> getListCommentUser(Integer id);
}
