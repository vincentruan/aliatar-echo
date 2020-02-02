package org.evue.alistar.service.cms;

import org.evue.alistar.bean.entity.cms.Contacts;
import org.evue.alistar.dao.cms.ContactsRepository;
import org.evue.alistar.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ContactsService extends BaseService<Contacts, Long, ContactsRepository> {
}
