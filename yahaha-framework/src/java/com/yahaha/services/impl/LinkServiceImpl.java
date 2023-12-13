package com.yahaha.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.constants.SystemConstants;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.VO.LinkVo;
import com.yahaha.domain.entity.Link;
import com.yahaha.mapper.LinkMapper;
import com.yahaha.services.LinkService;
import com.yahaha.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Link::getStatus, SystemConstants.Link_STATUS_NORMAL);
        List<Link> linkList = list(lambdaQueryWrapper);
        return ResponseResult.okResult(BeanCopyUtils.copyBeanList(linkList, LinkVo.class));
    }
}
