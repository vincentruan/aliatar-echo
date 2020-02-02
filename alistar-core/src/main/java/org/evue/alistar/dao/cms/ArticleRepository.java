package org.evue.alistar.dao.cms;

import org.evue.alistar.bean.entity.cms.Article;
import org.evue.alistar.dao.BaseRepository;

import java.util.List;

public interface ArticleRepository extends BaseRepository<Article, Long> {
    /**
     * 查询指定栏目下所有文章列表
     *
     * @param idChannel
     * @return
     */
    List<Article> findAllByIdChannel(Long idChannel);
}
