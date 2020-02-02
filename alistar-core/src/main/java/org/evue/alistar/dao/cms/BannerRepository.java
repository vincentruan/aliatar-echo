package org.evue.alistar.dao.cms;

import org.evue.alistar.bean.entity.cms.Banner;
import org.evue.alistar.dao.BaseRepository;

import java.util.List;

public interface BannerRepository extends BaseRepository<Banner, Long> {
    /**
     * 查询指定类别的banner列表
     *
     * @param type
     * @return
     */
    List<Banner> findAllByType(String type);
}
