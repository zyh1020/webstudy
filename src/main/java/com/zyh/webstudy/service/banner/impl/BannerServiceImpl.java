package com.zyh.webstudy.service.banner.impl;

import com.zyh.webstudy.domain.banner.Banner;
import com.zyh.webstudy.mapper.banner.BannerMapper;
import com.zyh.webstudy.service.banner.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 轮播图
 * @author: zyh
 * @date: 2021年05月01日 21:36
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;
    @Override
    public List<Banner> getAllBanners() {
        return bannerMapper.findAllBanners();
    }
}
