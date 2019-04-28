package com.zgdr.schoolhelp.service;

import com.zgdr.schoolhelp.domain.Comment;
import com.zgdr.schoolhelp.domain.Post;
import com.zgdr.schoolhelp.repository.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @创建者 fishkk
 * @创建时间 描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {


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

    @Autowired
    private PostService postService;

    @Test
    public void  createComment(){
        Date date = new Date();
        Comment comment = new Comment(2,260,"sadas",date);
        postService.createComment(comment);
    }

    @Test
    public  void createPost(){
        Date date = new Date();
        Post post = new Post(2,"das","ssdad",12,0,
                0,0,0,"1",date);
        postService.createPost(post);
    }

}