package com.zgdr.schoolhelp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 *@创建comment表映射对象
 *@映射表   comment
 *@创建人  fishkk
 *@创建时间2 019/4/28
 *@修改人和其它信息
 */
@Entity(name = "comment")
public class Comment {
    /* 评论ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //使用默认生成方式（MySQL）：自增
    private Integer commentId;

    /* 评论用户ID */
    private Integer userId ;


    /* 被评论贴子的ID */
    private Integer postId;

    /* 评论内容 */
    @NotBlank
    private String commentContent;

    /* 评论日期*/
    private Date commentTime;

    public Comment() {
    }

    public Comment(Integer userId, Integer postId, String commentContent, Date commentTime) {
        this.userId = userId;
        this.postId = postId;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", postId=" + postId +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime=" + commentTime +
                '}';
    }
}
