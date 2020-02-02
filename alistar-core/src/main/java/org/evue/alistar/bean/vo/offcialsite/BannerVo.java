package org.evue.alistar.bean.vo.offcialsite;

import lombok.Data;
import org.evue.alistar.bean.entity.cms.Banner;

import java.util.List;

@Data
public class BannerVo {
    private Integer index = 0;
    private List<Banner> list;

}
