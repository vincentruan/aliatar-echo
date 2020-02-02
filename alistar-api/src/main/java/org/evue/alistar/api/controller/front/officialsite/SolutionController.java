package org.evue.alistar.api.controller.front.officialsite;

import org.evue.alistar.api.controller.BaseController;
import org.evue.alistar.bean.entity.cms.Article;
import org.evue.alistar.bean.enumeration.cms.BannerTypeEnum;
import org.evue.alistar.bean.enumeration.cms.ChannelEnum;
import org.evue.alistar.bean.vo.front.Rets;
import org.evue.alistar.bean.vo.offcialsite.BannerVo;
import org.evue.alistar.bean.vo.offcialsite.Solution;
import org.evue.alistar.service.cms.ArticleService;
import org.evue.alistar.service.cms.BannerService;
import org.evue.alistar.utils.Maps;
import org.evue.alistar.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/offcialsite/solution")
public class SolutionController extends BaseController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Object index() {
        Map<String, Object> dataMap = Maps.newHashMap();

        BannerVo banner = bannerService.queryBanner(BannerTypeEnum.SOLUTION.getValue());
        dataMap.put("banner", banner);

        List<Solution> solutions = new ArrayList<>();
        Page<Article> articlePage = articleService.query(1, 10, ChannelEnum.SOLUTION.getId());
        for (Article article : articlePage.getRecords()) {
            solutions.add(new Solution(article.getId(), article.getTitle(), article.getImg()));
        }
        dataMap.put("solutionList", solutions);

        Map map = Maps.newHashMap("data", dataMap);
        return Rets.success(map);

    }
}
