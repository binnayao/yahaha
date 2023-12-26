package com.yahaha.controller;

import com.yahaha.constants.SystemConstants;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.entity.Comment;
import com.yahaha.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("articleId") Integer articleId) {
        return commentService.commentList(SystemConstants.COMMENT_FOR_PAGE, pageNum, pageSize, articleId);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }


    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return commentService.commentList(SystemConstants.COMMENT_FOR_FRIEND_LINK, pageNum, pageSize, null);

    }
}
