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
      * 获取贴子详情
      * @author fishkk
      * @since 2019/4/27
      *
      * @param  id 贴子id
      * @return 贴子以及评论的信息
      */
     @GetMapping (value = "/id/{id}")
     public Result  getPostAllById(@PathVariable("id") Integer id){
         if(postService.isnull(id)){
             return Result.error(GlobalResultEnum.NOTFOUND);
         }
         return Result.success(postService.getPostAndComment(id) );
     }


    /**
     * 2通过id获取贴子简要信息
     * @author fishkk
     * @since 2019/4/25
     *
     * @param  id
     * @return 贴子的相关信息
     */
    @GetMapping(value = "/id/brief/{id}")
    public Result getPostByID(@PathVariable("id") Integer id){
        if(postService.isnull(id)){
            return Result.error(GlobalResultEnum.NOTFOUND);
        }
        return Result.success(postService.readPostById(id));
    }

    /**
       * 3 获取最新的xxx条帖子列表
       * @author fishkk
       * @since 2019/4/25
       *
       * @param num
       * @return 返回最新的num条消息 num>贴子总数则返回全部贴子
       */
    @GetMapping(value = "/num/{num}")
    public Result  getLastPostByNum(@PathVariable("num") Integer num){

        return Result.success(postService.getLastPostByNum(num));
    }

    /**
        * 4 获取类别id为xxx的帖子列表
        * @author fishkk
        * @since 2019/4/25
        *
        * @param typeId
        * @return 放回类别id为typeid的贴子列表
        */
    @GetMapping(value = "/type/{typeId}")
    public Result  getPostByTypeId(@PathVariable("typeId") Integer typeId){
        return  Result.success(postService.findPostsByPostType(typeId));
    }

    /**
       * 5搜索关键字为xxxx的帖子，返回列表
       * @author fishkk
       * @since 2019/4/25
       *
       * @param  keyword 关键词
       * @return title中含关键词的贴子列表
       */
    @GetMapping(value = "/search/{keyword}")
    public Result getPostByKeyword(@PathVariable("keyword") String keyword){

        return  Result.success(postService.findPostByKeyword(keyword));
    }


    /**
      * 6获取热门搜索关键词
      *
      * TODO
      *
      * @author fishkk
      * @since 2019/4/25
      *
      * @param
      * @return 热词列表
      */
    @GetMapping(value = "/search/hot")
    public Result getHotWord(){
        return null ;
    }


    /**
     * 获取贴子的点赞列表
     * @author fishkk
     * @since 2019/4/27
     *
     * @param  postId 贴子id
     * @return 该帖子的点赞用户id列表<Set>
     */
    @GetMapping(value = "/approval/{postId}")
    public Result getApprovalUser(@PathVariable("postId") Integer postId){
        if(postService.isnull(postId)){
            return Result.error(GlobalResultEnum.NOTFOUND);
        }
        return Result.success(postService.getApprovalList(postId));
    }
    
    /**
      * 8获取当前帖子的评论用户列表
      * @author fishkk
      * @since 2019/4/25
      *
      * @param postId 贴子id
      * @return 该帖子的评论用户id列表<Set>
      */
    @GetMapping(value = "/comment/{postId}")
    public Result getCommentUser(@PathVariable("postId") Integer postId){
        if(postService.isnull(postId)){
            return Result.error(GlobalResultEnum.NOTFOUND);
        }
        return  Result.success(postService.getCommentUserList(postId));
    }

    /**
      * 9获取当前帖子的举报用户列表
      * @author fishkk
      * @since 2019/4/25
      *
      * @param postId 贴子id
      * @return 该帖子的举报用户id列表<Set>
      */
    @GetMapping(value = "/report/{postId}")
    public Result getReportUser(@PathVariable("postId") Integer postId){
        if(postService.isnull(postId)){
            return Result.error(GlobalResultEnum.NOTFOUND);
        }
        return  Result.success(postService.getReportUserList(postId));
    }
    
    /**
      * 10获取当前帖子的评论列表
      * @author fishkk
      * @since 2019/4/25
      *
      * @param  postId 贴子id
      * @return  List<Comment> 评论列表
      */
    @GetMapping(value = "/comment/all/{postId}")
    public Result getPostComment(@PathVariable("postId") Integer postId){
        if(postService.isnull(postId)){
            return Result.error(GlobalResultEnum.NOTFOUND);
        }
        return Result.success(postService.getCommentByPostID(postId));
    }


    /**
     * 10获取当前帖子的评论列表
     * @author fishkk
     * @since 2019/4/25
     *
     * @param  approval
     * @return
     *
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
      * 12评论
      * @author fishkk
      * @since 2019/4/25
      *
      * @param comment
      * @param bindingResult 表单验证结果
      * @return
      */
    @PostMapping(value = "/comment")
    public void comment(@Valid Comment comment ,BindingResult bindingResult){
        postService.createComment(comment);
    }
    
    /**
      * 13当前用户对帖子举报
      * @author fishkk
      * @since 2019/4/25
      *
      * @param report
      * @param bindingResult 表单验证结果
      * @return
      */
    @PostMapping(value = "/report")
    public void report(@Valid Report report ,BindingResult bindingResult){
           postService.createReport(report);
    }


    /**
      * 11帖主结贴
      *
      * @author fishkk
      * @since 2019/4/27
      *
      * @param userId
      * @param  postId
      * @param submitCommentId 获得积分的评论
      * @return
      */
    @PostMapping(value = "/submit")
    public void submitPost(@RequestParam("userId") Integer userId,
                           @RequestParam("postId") Integer postId,
                           @RequestParam("submitCommentId") Integer submitCommentId){
         postService.sumbitPost(userId,postId,submitCommentId);

    }

    /**
     * 10发帖
     * @author fishkk
     * @since 2019/4/25
     *
     * @param post
     * @param bindingResult 表单验证结果
     * @return  贴子对象
     */
    @PostMapping(value = "")
    public Result crateUser(@Valid Post post, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            return Result.error(GlobalResultEnum.NODE);
        }
        if (postService.isRightPoints(post)){
            return Result.error(GlobalResultEnum.MOREPOINTS);
        }
        return Result.success(postService.createPost(post));
    }


    /**
     * 10删帖
     * @author fishkk
     * @since 2019/4/25
     *
     * @param postId 贴子id
     * @return 
     */
    @DeleteMapping(value = "")
    public void deletePostById(@RequestParam("postId") Integer postId){

        postService.deletePostById(postId);
    }

    /**
      * 14当前用户更新帖子
      * @author fishkk
      * @since 2019/4/25
      *
      * @param postId
      * @param newContent 修改的正文内容
      * @return void
      */
    @PutMapping(value = "")
    public void updatePost(@RequestParam("postId") Integer postId,
                           @RequestParam("newContent") String newContent){

        postService.updatePost(postId , newContent);
    }


}
