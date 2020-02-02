package org.evue.alistar.service.system;


import org.evue.alistar.bean.entity.system.LoginLog;
import org.evue.alistar.dao.system.LoginLogRepository;
import org.evue.alistar.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * Created  on 2018/3/26 0026.
 *
 * @author enilu
 */
@Service
public class LoginLogService extends BaseService<LoginLog, Long, LoginLogRepository> {

}
