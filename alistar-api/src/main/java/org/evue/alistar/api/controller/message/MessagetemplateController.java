package org.evue.alistar.api.controller.message;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.evue.alistar.bean.constant.factory.PageFactory;
import org.evue.alistar.bean.core.BussinessLog;
import org.evue.alistar.bean.dictmap.CommonDict;
import org.evue.alistar.bean.entity.message.MessageTemplate;
import org.evue.alistar.bean.enumeration.BizExceptionEnum;
import org.evue.alistar.bean.enumeration.Permission;
import org.evue.alistar.bean.exception.ApplicationException;
import org.evue.alistar.bean.vo.front.Rets;
import org.evue.alistar.service.message.MessagetemplateService;
import org.evue.alistar.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/message/template")
public class MessagetemplateController {
    @Autowired
    private MessagetemplateService messagetemplateService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.MSG_TPL})
    public Object list() {
        Page<MessageTemplate> page = new PageFactory<MessageTemplate>().defaultPage();
        page = messagetemplateService.queryPage(page);
        page.setRecords(page.getRecords());
        return Rets.success(page);
    }

    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑消息模板", key = "name", dict = CommonDict.class)
    @RequiresPermissions(value = {Permission.MSG_TPL_EDIT})
    public Object save(@ModelAttribute @Valid MessageTemplate messageTemplate) {
        if (messageTemplate.getId() == null) {
            messagetemplateService.insert(messageTemplate);
        } else {
            messagetemplateService.update(messageTemplate);
        }
        return Rets.success();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除消息模板", key = "id", dict = CommonDict.class)
    @RequiresPermissions(value = {Permission.MSG_TPL_DEL})
    public Object remove(Long id) {
        if (id == null) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        messagetemplateService.delete(id);
        return Rets.success();
    }
}