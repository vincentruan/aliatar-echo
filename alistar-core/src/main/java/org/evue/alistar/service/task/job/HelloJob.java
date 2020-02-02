package org.evue.alistar.service.task.job;

import org.evue.alistar.bean.entity.system.Cfg;
import org.evue.alistar.service.system.CfgService;
import org.evue.alistar.service.task.JobExecuter;
import org.evue.alistar.utils.DateUtil;
import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * HelloJob
 *
 * @author zt
 * @version 2018/12/30 0030
 */
@Component
public class HelloJob extends JobExecuter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CfgService cfgService;

    @Override
    public void execute(Map<String, Object> dataMap) throws Exception {
        Cfg cfg = cfgService.get(1L);
        cfg.setCfgDesc("update by " + DateUtil.getTime());
        cfgService.update(cfg);
        logger.info("hello :" + Json.toJson(dataMap));
    }
}
