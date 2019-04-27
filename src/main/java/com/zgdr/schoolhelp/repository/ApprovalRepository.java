package com.zgdr.schoolhelp.repository;

import com.zgdr.schoolhelp.domain.*;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * @创建者 fishkk
 * @创建时间 描述
 */
public interface ApprovalRepository extends JpaRepository<Approval, Integer>{
    /**
     * @10获取贴子的点赞用户列表
     * @author fishkk
     * @修改时间 2019/4/27
     *
     * @参数 post_id 贴子id
     * @返回值   Set 点赞用户id集合
     */
    @Query(value = "SELECT user_id FROM approval WHERE post_id=?1", nativeQuery = true)
    public Set<Integer> getListApprovalUser(Integer id);
}
