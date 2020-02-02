package org.evue.alistar.service.cms;

import org.evue.alistar.bean.entity.cms.Banner;
import org.evue.alistar.bean.enumeration.cms.BannerTypeEnum;
import org.evue.alistar.bean.vo.offcialsite.BannerVo;
import org.evue.alistar.dao.cms.BannerRepository;
import org.evue.alistar.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService extends BaseService<Banner, Long, BannerRepository> {
    @Autowired
    private BannerRepository bannerRepository;

    /**
     * 查询首页banner数据
     *
     * @return
     */
    public BannerVo queryIndexBanner() {
        return queryBanner(BannerTypeEnum.INDEX.getValue());
    }

    public BannerVo queryBanner(String type) {
        BannerVo banner = new BannerVo();
        List<Banner> bannerList = bannerRepository.findAllByType(type);
        banner.setIndex(0);
        banner.setList(bannerList);
        return banner;
    }
}
