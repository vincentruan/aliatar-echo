package org.evue.alistar.dao.message;


import org.evue.alistar.bean.entity.message.Message;
import org.evue.alistar.dao.BaseRepository;

import java.util.ArrayList;


public interface MessageRepository extends BaseRepository<Message, Long> {
    void deleteAllByIdIn(ArrayList<String> list);
}

