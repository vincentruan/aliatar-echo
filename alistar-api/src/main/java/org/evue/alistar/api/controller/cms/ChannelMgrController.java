package org.evue.alistar.api.controller.cms;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.evue.alistar.api.controller.BaseController;
import org.evue.alistar.bean.core.BussinessLog;
import org.evue.alistar.bean.dictmap.CommonDict;
import org.evue.alistar.bean.entity.cms.Channel;
import org.evue.alistar.bean.enumeration.Permission;
import org.evue.alistar.bean.vo.front.Rets;
import org.evue.alistar.service.cms.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 栏目管理
 */
@RestController
@RequestMapping("/channel")
public class ChannelMgrController extends BaseController {
    @Autowired
    private ChannelService channelService;

    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑栏目", key = "name", dict = CommonDict.class)
    @RequiresPermissions(value = {Permission.CHANNEL_EDIT})
    public Object save(@ModelAttribute @Valid Channel channel) {
        if (channel.getId() == null) {
            channelService.insert(channel);
        } else {
            channelService.update(channel);
        }
        return Rets.success();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除栏目", key = "id", dict = CommonDict.class)
    @RequiresPermissions(value = {Permission.CHANNEL_DEL})
    public Object remove(Long id) {
        channelService.delete(id);
        return Rets.success();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.CHANNEL})
    public Object list() {
        List<Channel> list = channelService.queryAll();
        return Rets.success(list);
    }
}
