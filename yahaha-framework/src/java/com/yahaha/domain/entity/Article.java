package com.yahaha.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章表(Article)表实体类
 *
 * @author makejava
 * @since 2023-12-11 14:22:17
 */
@Data
@TableName("sg_article")
public class Article implements Serializable {
    @TableId
    public Long id;
    //标题
    public String title;
    //文章内容
    public String content;
    //文章摘要
    public String summary;
    //所属分类id
    public Long categoryId;
    //缩略图
    public String thumbnail;
    //是否置顶（0否，1是）
    public String isTop;
    //状态（0已发布，1草稿）
    public String status;
    //访问量
    public Long viewCount;
    //是否允许评论 1是，0否
    public String isComment;

    public Long createBy;

    public Date createTime;

    public Long updateBy;

    public Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    public Integer delFlag;
}

