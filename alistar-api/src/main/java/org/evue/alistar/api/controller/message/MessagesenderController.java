package org.evue.alistar.api.controller.message;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.evue.alistar.bean.constant.factory.PageFactory;
import org.evue.alistar.bean.core.BussinessLog;
import org.evue.alistar.bean.dictmap.CommonDict;
import org.evue.alistar.bean.entity.message.MessageSender;
import org.evue.alistar.bean.enumeration.Permission;
import org.evue.alistar.bean.vo.front.Rets;
import org.evue.alistar.service.message.MessagesenderService;
import org.evue.alistar.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/message/sender")
public class MessagesenderController {
    @Autowired
    private MessagesenderService messagesenderService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.MSG_SENDER})
    public Object list() {
        Page<MessageSender> page = new PageFactory<MessageSender>().defaultPage();
        page = messagesenderService.queryPage(page);
        page.setRecords(page.getRecords());
        return Rets.success(page);
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.MSG_SENDER})
    public Object queryAll() {
        return Rets.success(messagesenderService.queryAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑消息发送者", key = "name", dict = CommonDict.class)
    @RequiresPermissions(value = {Permission.MSG_SENDER_EDIT})
    public Object save(@ModelAttribute @Valid MessageSender tMessageSender) {
        messagesenderService.save(tMessageSender);
        return Rets.success();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除消息发送者", key = "id", dict = CommonDict.class)
    @RequiresPermissions(value = {Permission.MSG_SENDER_DEL})
    public Object remove(Long id) {

        try {
            messagesenderService.delete(id);
            return Rets.success();
        } catch (Exception e) {
            return Rets.failure(e.getMessage());
        }

    }
}