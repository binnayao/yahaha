package com.yahaha.domain.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HotArticleVo {
    private Long id;
    private String title;
    public Long viewCount;

}
