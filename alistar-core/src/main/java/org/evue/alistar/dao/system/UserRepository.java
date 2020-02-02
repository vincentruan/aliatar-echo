package org.evue.alistar.dao.system;


import org.evue.alistar.bean.entity.system.User;
import org.evue.alistar.dao.BaseRepository;

/**
 * Created  on 2018/3/21 0021.
 *
 * @author enilu
 */
public interface UserRepository extends BaseRepository<User, Long> {
    User findByAccount(String account);

    User findByAccountAndStatusNot(String account, Integer status);
}
