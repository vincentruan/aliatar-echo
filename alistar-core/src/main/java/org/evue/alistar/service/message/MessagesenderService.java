package org.evue.alistar.service.message;


import org.evue.alistar.bean.entity.message.MessageSender;
import org.evue.alistar.bean.entity.message.MessageTemplate;
import org.evue.alistar.dao.message.MessagesenderRepository;
import org.evue.alistar.dao.message.MessagetemplateRepository;
import org.evue.alistar.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MessagesenderService
 *
 * @author enilu
 * @version 2019/05/17 0017
 */
@Service
public class MessagesenderService extends BaseService<MessageSender, Long, MessagesenderRepository> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MessagesenderRepository messageSenderRepository;
    @Autowired
    private MessagetemplateRepository messagetemplateRepository;

    public void save(MessageSender messageSender) {
        messageSenderRepository.save(messageSender);
    }

    @Override
    public void delete(Long id) {
        List<MessageTemplate> templateList = messagetemplateRepository.findByIdMessageSender(id);
        if (templateList.isEmpty()) {
            messageSenderRepository.deleteById(id);
        } else {
            throw new RuntimeException("有模板使用该发送器，无法删除");
        }
    }

}

