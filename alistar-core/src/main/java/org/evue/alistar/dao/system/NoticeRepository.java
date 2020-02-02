package org.evue.alistar.dao.system;


import org.evue.alistar.bean.entity.system.Notice;
import org.evue.alistar.dao.BaseRepository;

import java.util.List;

/**
 * Created  on 2018/3/21 0021.
 *
 * @author enilu
 */
public interface NoticeRepository extends BaseRepository<Notice, Long> {
    List<Notice> findByTitleLike(String name);
}
