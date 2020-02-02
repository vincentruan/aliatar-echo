package org.evue.alistar.dao.system;


import org.evue.alistar.bean.entity.system.Dict;
import org.evue.alistar.dao.BaseRepository;

import java.util.List;

public interface DictRepository extends BaseRepository<Dict, Long> {
    List<Dict> findByPid(Long pid);

    List<Dict> findByNameAndPid(String name, Long pid);

    List<Dict> findByNameLike(String name);
}
