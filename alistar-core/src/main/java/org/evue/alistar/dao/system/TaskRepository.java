package org.evue.alistar.dao.system;


import org.evue.alistar.bean.entity.system.Task;
import org.evue.alistar.dao.BaseRepository;

import java.util.List;

public interface TaskRepository extends BaseRepository<Task, Long> {

    long countByNameLike(String name);

    List<Task> findByNameLike(String name);

    List<Task> findAllByDisabled(boolean disable);
}
