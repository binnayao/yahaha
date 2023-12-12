package com.yahaha.domain.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetCategoryListVo {
    private Long id;
    //分类名
    private String name;
}
