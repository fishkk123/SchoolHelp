package com.zgdr.schoolhelp.service;
import com.zgdr.schoolhelp.domain.*;
import com.zgdr.schoolhelp.enums.GlobalResultEnum;
import com.zgdr.schoolhelp.enums.UserResultEnum;
import com.zgdr.schoolhelp.exception.*;
import com.zgdr.schoolhelp.repository.*;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zgdr.schoolhelp.repository.PostRepository;
import com.zgdr.schoolhelp.domain.User;
import com.zgdr.schoolhelp.enums.UserResultEnum;
import com.zgdr.schoolhelp.exception.UserException;
import com.zgdr.schoolhelp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.*;

/**
 * PostService
 * TODO
 *
 * @author fishkk
 * @version 1.0
 * @since 2019/4/23
 */


@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private UserRepository userRepository;


    /**
     * 返回全部贴子信息
     * @author fishkk
     * @since 2019/4/24
     *
     * @param
     * @return List <post>
     */
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    /**
     * 创建贴子贴子
     * @author fishkk
     * @since 2019/4/24
     *
     * @param post
     * @return post
     */
    public Post createPost(Post post){
//        积分计算的另一种方式
//        User user = userRepository.findById(post.getUserId()).orElse(null);
//        user.setPoints(user.getPoints()-post.getPoints());
//        userRepository.save(user);
        post.setPoints(post.getPoints());
        post.setPostType(post.getPostType());
        post.setCommentNum(0);
        post.setReportNum(0);
        post.setViewNum(0);
        post.setApprovalNum(0);
        Date date = new Date();
        post.setIssueTime(date);
        return postRepository.save(post);
    }

    /**
     * 作者查看贴子
     * @author fishkk
     * @since 2019/4/24
     *
     * @param id 贴子id
     * @return post
     */
    public Post readPostById(Integer id){
        Post post = postRepository.findById(id).orElse(null);
        if (post == null){
            throw new PostException(GlobalResultEnum.NOTFOUND);
        }

        return post;
    }

    /**
     * 其他用户查看贴子功能
     * @author fishkk
     * @since 2019/4/24
     *
     * @param id 贴子id
     * @return void
     */
    public Post otherReadPostById(Integer id){
        Post post=postRepository.findById(id).orElse(null);

        if(post != null){
            post.setViewNum(post.getViewNum()+1);
            return postRepository.save(post);
        }
        return null;
    }

    /**
     * 点赞功能
     * @author fishkk
     * @since 2019/4/24
     *
     * @param approval
     * @return App
     */
    public Approval addPostApproval(Approval approval){
        Post post=postRepository.findById(approval.getPostId()).orElse(null);
        if(post == null){
            return null;
        }
        post.setApprovalNum(post.getApprovalNum()+1);
        postRepository.save(post);
        Date date = new Date();
        approval.setApprovalTime(date);
        return  approvalRepository.save(approval);
    }


    /**
     * 删除贴子
     * @author fishkk
     * @since 2019/4/24
     *
     * @param id 贴子id
     * @return void
     */
    public void deletePostById(Integer id){
        Post post = this.readPostById(id);
        postRepository.delete(post);
    }

    /**
     * 更新贴子
     * @author fishkk
     * @since 2019/4/24
     *
     * @param post_id ,new_content更新内容
     * @return void
     */
    public void updatePost(Integer post_id , String new_content){
        Post post = postRepository.findById(post_id).orElse(null);
        post.setContent(new_content);
        postRepository.save(post);
    }

    /**
     * 判断贴子是否为空
     * @author fishkk
     * @since 2019/4/24
     *
     * @param id 贴子id
     * @return Boolean
     */
    public Boolean isnull(Integer id){
        if(postRepository.findById(id).orElse(null) == null){
            return true;
        }
        return false;
    }

    /**
     * 创建举报
     * @author fishkk
     * @since 2019/4/24
     *
     * @param report
     * @return void
     */
    public void  createReport(Report report){
        Post post=postRepository.findById(report.getPostId()).orElse(null);
        post.setReportNum(post.getReportNum()+1);
        postRepository.save(post);
        Date date = new Date();
        report.setReportTime(date);
        reportRepository.save(report);
    }


    /**
     * 创建评论
     * @author fishkk
     * @since 2019/4/24
     *
     * @param comment
     * @return void
     */
    public void  createComment(Comment comment){
        Post post=postRepository.findById(comment.getPostId()).orElse(null);

        post.setCommentNum((post.getCommentNum()+1));
        postRepository.save(post);
        Date date = new Date();
        comment.setCommentTime(date);
        commentRepository.save(comment);
    }

    /**
      * @获取全部评论详情  and 获取一堆列表
      * @author fishkk
      * @修改时间 2019/4/25
      *
      * @参数
      * @返回值
      */
    public List<Comment> getCommentByPostID(Integer post_id){
       return commentRepository.getCommentByPostId(post_id);
    }

    public Set<Integer> getApprovalList(Integer post_id){
        return approvalRepository.getListApprovalUser(post_id);
    }

    public Set<Integer> getCommentUserList(Integer post_id){
        return commentRepository.getListCommentUser(post_id);
    }

    public Set<Integer> getReportUserList(Integer post_id){
        return reportRepository.getListReportUser(post_id);
    }

    public HashMap getPostAndComment(Integer id){
        Post post = this.readPostById(id);
        HashMap hashMap = new HashMap();
        List<Comment> commentList = this.getCommentByPostID(id);
        hashMap.put("comments",commentList);
        hashMap.put("post",post);
        return hashMap;
    }

    public List<Post>  getLastPostByNum(Integer num){
        List<Post> posts= this.getAll();
        List<Post> posts1 =new ArrayList<>() ;
        int lenth = posts.size();
        if(lenth < num){
            return posts;
        }
        posts.toArray();
        for (int i=lenth-num, k =0 ;i<lenth ; i++,k++){
           posts1.add(posts.get(i));
        }
        return posts1;
    }

    public List<Post>  findPostsByPost_type(Integer post_type){
        return postRepository.findPostsByPost_type(post_type);
    }

    public List<Post>  findPostByKeyword(String keyword){
        return postRepository.findPostsByKeyword(keyword);
    }


    /**
     * 结贴
     * @author fishkk
     * @since 2019/4/24
     *
     * @param user_id  post_id comment_id
     * @return void
     */
    public void sumbitPost(Integer user_id ,Integer post_id ,Integer comment_id){
//        获取支付积分的用户
        User user =  userRepository.findById(user_id).orElse(null);
        Comment comment = commentRepository.findById(comment_id).orElse(null);
        //       获取获得积分的用户
        Integer user1 =  comment.getUserId();
        User userget =  userRepository.findById(user1).orElse(null);
//        获取贴子积分
        Post post = postRepository.findById(post_id).orElse(null);
        Integer points = post.getPoints();

        user.setPoints(user.getPoints()-points);
        userget.setPoints(userget.getPoints()+points);
        userRepository.save(user);
        userRepository.save(userget);

    }

    public Boolean isRightPoints(Post post){
        User user = userRepository.findById(post.getUserId()).orElse(null);
        Integer points = user.getPoints();
        if(points<post.getPoints()){
            return true;
        }
        return false;
    }
}
