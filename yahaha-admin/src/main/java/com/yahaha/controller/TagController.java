package com.yahaha.controller;

import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.VO.PageVo;
import com.yahaha.domain.entity.Tag;
import com.yahaha.services.TagService;
import com.yahaha.vo.tag.TagAddVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageVo res = tagService.list(pageNum, pageSize);
        return ResponseResult.okResult(res);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody TagAddVO tagAddVO) {
        boolean save = tagService.save(new Tag(tagAddVO.getName(), tagAddVO.getRemark()));
        return ResponseResult.okResult();
    }
}
