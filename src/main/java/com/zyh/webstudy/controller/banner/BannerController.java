package com.zyh.webstudy.controller.banner;

/**
 * @Description: 轮播图
 * @author: zyh
 * @date: 2021年05月01日 21:37
 */

import com.zyh.webstudy.domain.banner.Banner;
import com.zyh.webstudy.service.banner.BannerService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ban")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @ApiOperation("查询所有banner")
    @GetMapping("/getAllBanners")
    public ResultUtil getAllBanners(){
        List<Banner> allBanners = bannerService.getAllBanners();
        return ResultUtil.success("查询所有banner成功",allBanners);
    }
}
