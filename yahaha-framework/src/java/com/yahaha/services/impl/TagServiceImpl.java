package com.yahaha.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.domain.entity.Tag;
import com.yahaha.mapper.TagMapper;
import com.yahaha.services.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
