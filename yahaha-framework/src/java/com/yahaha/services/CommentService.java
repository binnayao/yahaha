package com.yahaha.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.entity.Comment;

public interface CommentService extends IService<Comment> {
    ResponseResult commentList(Integer pageNum, Integer pageSize, Integer articleId);
}
