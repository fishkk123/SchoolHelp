package com.zgdr.schoolhelp.repository;

import com.zgdr.schoolhelp.domain.*;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 *@创建 report dao
 *@创建人  fishkk
 *@创建时间2 019/4/28
 *@修改人和其它信息
 */
public interface ReportRepository extends JpaRepository<Report, Integer>{
    /**
     * @10 通过贴子id来获得贴子的举报用户id列表
     * @author fishkk
     * @修改时间 2019/4/27
     *
     * @参数 post_id 贴子id
     * @返回值   Set<Integer>
     */
    @Query(value = "SELECT user_id FROM report WHERE post_id=?1", nativeQuery = true)
    public Set<Integer> getListReportUser(Integer id);
}
