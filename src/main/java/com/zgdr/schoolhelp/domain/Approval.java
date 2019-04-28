package com.zgdr.schoolhelp.domain;

import sun.security.util.Length;
import javax.persistence.*;
import java.util.Date;


/**
 *@创建approval表映射对象
 *@映射表   comment
 *@创建人  fishkk
 *@创建时间2 019/4/28
 *@修改人和其它信息
 */
@Entity(name = "approval")
public class Approval {
    /* 点赞ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //使用默认生成方式（MySQL）：自增
    private Integer approvalId;

    /* 点赞用户ID */
    private Integer userId ;


    /* 被点赞贴子的ID*/
    private Integer postId;


    /* 点赞日期*/
    private Date approvalTime;

    public Approval(Integer userId, Integer postId, Date approvalTime) {
        this.userId = userId;
        this.postId = postId;
        this.approvalTime = approvalTime;
    }

    public Approval() {
    }

    public Integer getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Integer approvalId) {
        this.approvalId = approvalId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    @Override
    public String toString() {
        return "Approval{" +
                "approvalId=" + approvalId +
                ", userId=" + userId +
                ", postId=" + postId +
                ", approvalTime=" + approvalTime +
                '}';
    }
}
