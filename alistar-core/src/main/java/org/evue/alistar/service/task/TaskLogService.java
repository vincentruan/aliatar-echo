package org.evue.alistar.service.task;


import org.evue.alistar.bean.entity.system.TaskLog;
import org.evue.alistar.dao.system.TaskLogRepository;
import org.evue.alistar.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 定时任务日志服务类
 *
 * @author enilu
 * @date 2019-08-13
 */
@Service
public class TaskLogService extends BaseService<TaskLog, Long, TaskLogRepository> {
}
