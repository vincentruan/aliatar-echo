package org.evue.alistar.api.controller.front.officialsite;

import org.evue.alistar.api.controller.BaseController;
import org.evue.alistar.bean.entity.cms.Article;
import org.evue.alistar.bean.enumeration.cms.BannerTypeEnum;
import org.evue.alistar.bean.enumeration.cms.ChannelEnum;
import org.evue.alistar.bean.vo.front.Rets;
import org.evue.alistar.bean.vo.offcialsite.BannerVo;
import org.evue.alistar.bean.vo.offcialsite.News;
import org.evue.alistar.service.cms.ArticleService;
import org.evue.alistar.service.cms.BannerService;
import org.evue.alistar.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/offcialsite/news")
public class NewsController extends BaseController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Object list() {
        Map<String, Object> dataMap = new HashMap<>(10);
        BannerVo banner = bannerService.queryBanner(BannerTypeEnum.NEWS.getValue());
        dataMap.put("banner", banner);

        List<News> newsList = new ArrayList<>();
        Page<Article> articlePage = articleService.query(1, 10, ChannelEnum.NEWS.getId());

        for (Article article : articlePage.getRecords()) {
            News news = new News();
            news.setDesc(article.getTitle());
            news.setUrl("/article?id=" + article.getId());
            news.setSrc("static/images/icon/user.png");
            newsList.add(news);
        }

        dataMap.put("list", newsList);

        Map map = new HashMap(2);

        map.put("data", dataMap);
        return Rets.success(map);

    }
}
