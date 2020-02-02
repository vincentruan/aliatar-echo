package org.evue.alistar.api.controller.message;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.evue.alistar.bean.constant.factory.PageFactory;
import org.evue.alistar.bean.core.BussinessLog;
import org.evue.alistar.bean.entity.message.Message;
import org.evue.alistar.bean.enumeration.Permission;
import org.evue.alistar.bean.vo.front.Rets;
import org.evue.alistar.bean.vo.query.SearchFilter;
import org.evue.alistar.service.message.MessageService;
import org.evue.alistar.utils.DateUtil;
import org.evue.alistar.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.MSG})
    public Object list(@RequestParam(required = false) String startDate,
                       @RequestParam(required = false) String endDate) {
        Page<Message> page = new PageFactory<Message>().defaultPage();
        page.addFilter("createTime", SearchFilter.Operator.GTE, DateUtil.parse(startDate, "yyyyMMddHHmmss"));
        page.addFilter("createTime", SearchFilter.Operator.LTE, DateUtil.parse(endDate, "yyyyMMddHHmmss"));
        page = messageService.queryPage(page);
        page.setRecords(page.getRecords());
        return Rets.success(page);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "清空所有历史消息")
    @RequiresPermissions(value = {Permission.MSG_CLEAR})
    public Object clear() {
        messageService.clear();
        return Rets.success();
    }
}