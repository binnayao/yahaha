package com.yahaha.domain.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LinkVo {
    private Long id;
    private String name;
    private String logo;
    private String description;
    //网站地址
    private String address;
}
