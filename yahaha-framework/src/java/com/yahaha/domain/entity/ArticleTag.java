package com.yahaha.domain.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 文章标签关联表(SgArticleTag)表实体类
 *
 * @author makejava
 * @since 2024-01-04 17:36:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_article_tag")
public class ArticleTag {
    //文章id
    @TableId
    private Long articleId;
    //标签id
    private Long tagId;


}
