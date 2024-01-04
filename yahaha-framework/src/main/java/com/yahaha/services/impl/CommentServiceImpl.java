package com.yahaha.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.constants.SystemConstants;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.VO.CommentVo;
import com.yahaha.domain.VO.PageVo;
import com.yahaha.domain.entity.Comment;
import com.yahaha.domain.entity.User;
import com.yahaha.enums.AppHttpCodeEnum;
import com.yahaha.exception.SystemException;
import com.yahaha.mapper.CommentMapper;
import com.yahaha.services.CommentService;
import com.yahaha.services.UserService;
import com.yahaha.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(Integer type, Integer pageNum, Integer pageSize, Integer articleId) {
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 查询对应文章的根评论
        commentLambdaQueryWrapper.eq(Comment::getType, type);
        // 当articleId是文章评论时候,才需要加这个条件
        commentLambdaQueryWrapper.eq(SystemConstants.COMMENT_FOR_PAGE == type, Comment::getArticleId, articleId);
        // 根评论是-1
        commentLambdaQueryWrapper.eq(Comment::getRootId, SystemConstants.COMMENT_ROOT_ID);
        // 分页查询
        Page<Comment> pageComment = new Page<>(pageNum, pageSize);
        page(pageComment, commentLambdaQueryWrapper);

        List<CommentVo> commentVoList = toCommentVoList(pageComment.getRecords());


        for (CommentVo commentVo : commentVoList) {
            List<CommentVo> children = getChildren(commentVo.getId());
            commentVo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(commentVoList, pageComment.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        boolean save = save(comment);
        if (save) {
            return ResponseResult.okResult("新建成功");
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByDesc(Comment::getCreateTime);
        List<Comment> commentVos = list(queryWrapper);
        return toCommentVoList(commentVos);
    }

    private List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);

        for (CommentVo commentVo : commentVos) {
            User createUser = userService.getById(commentVo.getCreateBy());
            // 获取用户昵称
            if (Objects.nonNull(createUser)) {
                commentVo.setUsername(createUser.getNickName());
            }
            // 获取用户头像
            if (Objects.nonNull(createUser)) {
                commentVo.setAvatar(createUser.getAvatar());
            }
            // 通过toCommentUserId查询用户的昵称并赋值
            // 如果toCommentUserId不为-1才进行查询
            if (commentVo.getToCommentId() != -1) {
                String nickNameForComment = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(nickNameForComment);
            }
        }

        return commentVos;
    }
}
