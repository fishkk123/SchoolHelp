package com.zgdr.schoolhelp.service;

import com.zgdr.schoolhelp.domain.Approval;
import com.zgdr.schoolhelp.domain.Comment;
import com.zgdr.schoolhelp.domain.Post;
import com.zgdr.schoolhelp.domain.Report;
import com.zgdr.schoolhelp.repository.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @创建者 fishkk
 * @创建时间 描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //按方法名字典顺序进行顺序测试
public class PostServiceTest {

//    private static Logger logger = LoggerFactory.getLogger(PostServiceTest.class);
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    @Autowired
//    private ReportRepository reportRepository;
//
//    @Autowired
//    private ApprovalRepository approvalRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PostService postService;
//
//    @Test
//    public void  createComment(){
//        Date date = new Date();
//        Comment comment = new Comment(2,262,"sadas",date);
//        postService.createComment(comment,2);
//    }
//
//    @Test
//    public  void createPost(){
//        Date date = new Date();
//        Post post = new Post();
//        post.setPoints(12);
////        postService.createPost(post,2);
//    }
//
//    @Test
//    public void otherReadPostById() {
//        Integer id = 262;
//        Post post = postRepository.findById(id).orElse(null);
//        post.setPoints(1);
//        logger.info(post.toString());
//    }
//
//    @Test
//    public  void addPostApproval(){
//        Approval approval = new Approval(1,262,new Date());
//        postService.addPostApproval(approval,2);
//    }
//
//    @Test
//    public  void deletePostById(){
////        Integer id =262;
////        postService.deletePostById(id);
//    }
//
//    @Test
//    public void updatePost(){
//        Integer postId = 262;
//        String newContent = "sadasdada";
//        postService.updatePost(262,newContent);
//    }
//
//    @Test
//    public void  isnull(){
//        Integer id = 262;
//        Boolean x = postService.isnull(262);
//        logger.info(x.toString());
//    }
////
////    @Test
////    public void createReport(){
////        Report report = new Report(3,262,"dadsdasd，new",new Date() );
////        postService.createReport(report,3);
////    }
//
//    @Test
//    public void getList(){
//
//        postService.getCommentByPostID(262);
//        postService.getApprovalList(262);
//        postService.getCommentUserList(262);
//        postService.getLastPostByNum(262);
//        postService.getPostAndComment(262);
//        postService.getReportUserList(262);
//    }
//
//    @Test
//    public void getPostAndComment(){
//        Integer postId = 262;
//        HashMap hashMap = postService.getPostAndComment(postId);
//        logger.info(hashMap.toString());
//    }
//
//    @Test
//    public void getLastPostByNum(){
//        Integer num = 3 ;
//        List<Post> list = postService.getLastPostByNum(num);
//        logger.info(list.toString());
//    }
//
//    @Test
//    public  void getListByKeyword(){
//        String keyword = "ww";
//        List<Post> list = postService.findPostByKeyword(keyword);
//        logger.info(list.toString());
//    }
//
    @Test
    public  void submitPost(){

    }


    }

