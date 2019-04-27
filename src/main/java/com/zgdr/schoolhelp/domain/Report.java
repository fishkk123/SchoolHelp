package com.zgdr.schoolhelp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;
/**
 * @创建者 fishkk
 * @创建时间 描述
 */

@Entity(name = "report")
public class Report {
    /* 举报ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //使用默认生成方式（MySQL）：自增
    private Integer reportId;

    /* 举报者用户ID */
    private Integer  userId;

    /* 被举报贴子ID */
    private Integer postId;

    /* 举报描述 */
    @Size(min=4,max=230,message = "输入不能为空")
    private String report_des;

    /* 举报日期*/
    private Date reportTime;

    public Report(Integer userId, Integer postId,
                  @Size(min = 4, max = 230, message = "输入不能为空") String report_des, Date reportTime) {
        this.userId = userId;
        this.postId = postId;
        this.report_des = report_des;
        this.reportTime = reportTime;
    }

    public Report() {
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
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

    public String getReport_des() {
        return report_des;
    }

    public void setReport_des(String report_des) {
        this.report_des = report_des;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", userId=" + userId +
                ", postId=" + postId +
                ", report_des='" + report_des + '\'' +
                ", reportTime=" + reportTime +
                '}';
    }
}
