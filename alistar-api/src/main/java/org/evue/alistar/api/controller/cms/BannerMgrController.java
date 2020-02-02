package org.evue.alistar.api.controller.cms;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.evue.alistar.api.controller.BaseController;
import org.evue.alistar.bean.core.BussinessLog;
import org.evue.alistar.bean.dictmap.CommonDict;
import org.evue.alistar.bean.entity.cms.Banner;
import org.evue.alistar.bean.enumeration.Permission;
import org.evue.alistar.bean.vo.front.Rets;
import org.evue.alistar.bean.vo.query.SearchFilter;
import org.evue.alistar.service.cms.BannerService;
import org.evue.alistar.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * banner管理
 */
@RestController
@RequestMapping("/banner")
public class BannerMgrController extends BaseController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑banner", key = "title", dict = CommonDict.class)
    @RequiresPermissions(value = {Permission.BANNER_EDIT})
    public Object save(@ModelAttribute @Valid Banner banner) {
        if (banner.getId() == null) {
            bannerService.insert(banner);
        } else {
            bannerService.update(banner);
        }
        return Rets.success();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除banner", key = "id", dict = CommonDict.class)
    @RequiresPermissions(value = {Permission.BANNER_DEL})
    public Object remove(Long id) {
        bannerService.delete(id);
        return Rets.success();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.BANNER})
    public Object list(@RequestParam(required = false) String title) {
        SearchFilter filter = null;
        if (StringUtil.isNotEmpty(title)) {
            filter = SearchFilter.build("title", SearchFilter.Operator.LIKE, title);
        }
        List<Banner> list = bannerService.queryAll(filter);
        return Rets.success(list);
    }
}
