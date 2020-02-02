package org.evue.alistar.dao.system;

import org.evue.alistar.bean.entity.system.Cfg;
import org.evue.alistar.dao.BaseRepository;

/**
 * 全局参数dao
 *
 * @author ：enilu
 * @date ：Created in 2019/6/29 12:50
 */
public interface CfgRepository extends BaseRepository<Cfg, Long> {

    Cfg findByCfgName(String cfgName);
}
