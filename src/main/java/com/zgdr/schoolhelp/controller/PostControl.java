package com.zgdr.schoolhelp.controller;

import com.zgdr.schoolhelp.domain.*;
import com.zgdr.schoolhelp.enums.GlobalResultEnum;
import com.zgdr.schoolhelp.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * PostControl
 * TODO
 *
 * @author fishkk
 * @version 1.0
 * @since 2019/4/24
 */


@RestController
@RequestMapping(value = "/post")

public class PostControl {

    @Autowired
    private PostService postService;

    private final static Logger logger = LoggerFactory.getLogger(PostControl.class);

    /**
      * @获取贴子详情
      * @author fishkk
      * @修改时间 2019/4/27
      *
      * @参数  id 贴子id
      * @返回值  贴子以及评论的信息
      */
     @GetMapping (value = "/id/{id}")
     public Result  getPostAllById(@PathVariable("id") Integer id){
         if(postService.isnull(id)){
             return Result.error(GlobalResultEnum.NOTFOUND);
         }
         return Result.success(postService.getPostAndComment(id) );
     }


    /**
     * @2通过id获取贴子简要信息
     * @author fishkk
     * @修改时间 2019/4/25
     *
     * @参数  贴子id
     * @返回值  贴子的相关信息
     */
    @GetMapping(value = "/id/brief/{id}")
    public Result getPostByID(@PathVariable("id") Integer id){
        if(postService.isnull(id)){
            return Result.error(GlobalResultEnum.NOTFOUND);
        }
        return Result.success(postService.readPostById(id));
    }

    /**
       * @3 获取最新的xxx条帖子列表
       * @author fishkk
       * @修改时间 2019/4/25
       *
       * @参数  num
       * @返回值  返回最新的num条消息 num>贴子总数则返回全部贴子
       */
    @GetMapping(value = "/num/{num}")
    public Result  getLastPostByNum(@PathVariable("num") Integer num){

        return Result.success(postService.getLastPostByNum(num));
    }

    /**
        * @4 获取类别id为xxx的帖子列表
        * @author fishkk
        * @修改时间 2019/4/25
        *
        * @参数  typeid
        * @返回值  放回类别id为typeid的贴子列表
        */
    @GetMapping(value = "/type/{typeid}")
    public Result  getPostByTypeId(@PathVariable("typeid") Integer typeid){
        return  Result.success(postService.findPostsByPost_type(typeid));
    }

    /**
       * @5搜索关键字为xxxx的帖子，返回列表
       * @author fishkk
       * @修改时间 2019/4/25
       *
       * @参数  keyword 关键词
       * @返回值  title中含关键词的贴子列表
       */
    @GetMapping(value = "/search/{keyword}")
    public Result getPostByKeyword(@PathVariable("keyword") String keyword){

        return  Result.success(postService.findPostByKeyword(keyword));
    }


    /**
      * @6获取热门搜索关键词
     * TODO
      * @author fishkk
      * @修改时间 2019/4/25
      *
      * @参数
      * @返回值
      */
    @GetMapping(value = "/search/hot")
    public Result getHotWord(){
        return null ;
    }


    /**
     * @获取贴子的点赞列表
     * @author fishkk
     * @修改时间 2019/4/27
     *
     * @参数  post_id 贴子id
     * @返回值  该帖子的点赞用户id列表<Set>
     */
    @GetMapping(value = "/approval/{post_id}")
    public Result getApprovalUser(@PathVariable("post_id") Integer post_id){

        return Result.success(postService.getApprovalList(post_id));
    }
    
    /**
      * @8获取当前帖子的评论用户列表
      * @author fishkk
      * @修改时间 2019/4/25
      *
      * @参数 post_id 贴子id
      * @返回值  该帖子的评论用户id列表<Set>
      */
    @GetMapping(value = "/comment/{post_id}")
    public Result getCommentUser(@PathVariable("post_id") Integer post_id){
        return  Result.success(postService.getCommentUserList(post_id));
    }

    /**
      * @9获取当前帖子的举报用户列表
      * @author fishkk
      * @修改时间 2019/4/25
      *
      * @参数 post_id 贴子id
      * @返回值 该帖子的举报用户id列表<Set>
      */
    @GetMapping(value = "/report/{post_id}")
    public Result getReportUser(@PathVariable("post_id") Integer post_id){
        return  Result.success(postService.getReportUserList(post_id));
    }
    
    /**
      * @10获取当前帖子的评论列表
      * @author fishkk
      * @修改时间 2019/4/25
      *
      * @参数  post_id 贴子id
      * @返回值   List<Comment> 评论列表
      */
    @GetMapping(value = "/comment/all/{post_id}")
    public Result getPostComment(@PathVariable("post_id") Integer post_id){

        return Result.success(postService.getCommentByPostID(post_id));
    }


    /**
     * @10点赞
     * @author fishkk
     * @修改时间 2019/4/25
     *
     * @参数  一个Approval对象 token
     * @返回值
     */
    @PostMapping(value = "/approval")
    public void approval(@Valid Approval approval,BindingResult bindingResult){
        if(postService.isnull(approval.getPostId())){
            //return Result.error(GlobalResultEnum.NOTFOUND);
        }
        postService.addPostApproval(approval);
        //return  null;
    }
    
    /**
      * @12评论
      * @author fishkk
      * @修改时间 2019/4/25
      *
      * @参数  一个Comment对象 token
      * @返回值  
      */
    @PostMapping(value = "/comment")
    public void comment(@Valid Comment comment ,BindingResult bindingResult){
        postService.createComment(comment);
    }
    
    /**
      * @13当前用户对帖子举报
      * @author fishkk
      * @修改时间 2019/4/25
      *
      * @参数   一个Report对象 token
      * @返回值  
      */
    @PostMapping(value = "/report")
    public void report(@Valid Report report ,BindingResult bindingResult){
           postService.createReport(report);
    }


    /**
      * @11帖主结贴
      *
      * @author fishkk
      * @修改时间 2019/4/27
      *
      * @参数  贴子id  评论的id token
      * @返回值
      */
    @PostMapping(value = "/submit")
    public void submitPost(@RequestParam("user_id") Integer user_id,
                           @RequestParam("post_id") Integer post_id,
                           @RequestParam("submit_comment_id") Integer submit_comment_id){
         postService.sumbitPost(user_id,post_id,submit_comment_id);

    }

    /**
     * @10发帖
     * @author fishkk
     * @修改时间 2019/4/25
     *
     * @参数  一个Post对象和token
     * @返回值   贴子对象
     */
    @PostMapping(value = "")
    public Result crateUser(@Valid Post post, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            /*
             * TODO 修改错误类型为表单校验错误
             *
             * @author hengyumo
             * @date 2019/4/17
             */

            return Result.error(GlobalResultEnum.UNKNOW_ERROR);
        }
        return Result.success(postService.createPost(post));
    }


    /**
     * @10删帖
     * @author fishkk
     * @修改时间 2019/4/25
     *
     * @参数 post_id 贴子id
     * @返回值
     */
    @DeleteMapping(value = "")
    public void deletePostById(@RequestParam("post_id") Integer post_id){

        postService.deletePostById(post_id);
    }

    /**
      * @14当前用户更新帖子
      * @author fishkk
      * @修改时间 2019/4/25
      *
      * @参数
      * @返回值  void
      */
    @PutMapping(value = "")
    public void updatePost(@RequestParam("post_id") Integer post_id,
                           @RequestParam("new_content") String newcontent){

        postService.updatePost(post_id , newcontent);
    }


}
