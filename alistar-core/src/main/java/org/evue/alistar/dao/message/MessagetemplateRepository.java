package org.evue.alistar.dao.message;


import org.evue.alistar.bean.entity.message.MessageTemplate;
import org.evue.alistar.dao.BaseRepository;

import java.util.List;


public interface MessagetemplateRepository extends BaseRepository<MessageTemplate, Long> {
    MessageTemplate findByCode(String code);

    List<MessageTemplate> findByIdMessageSender(Long idMessageSender);
}

