package org.evue.alistar.api.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.evue.alistar.api.controller.BaseController;
import org.evue.alistar.bean.core.BussinessLog;
import org.evue.alistar.bean.dictmap.DictMap;
import org.evue.alistar.bean.entity.system.Dict;
import org.evue.alistar.bean.enumeration.BizExceptionEnum;
import org.evue.alistar.bean.enumeration.Permission;
import org.evue.alistar.bean.exception.ApplicationException;
import org.evue.alistar.bean.vo.front.Rets;
import org.evue.alistar.service.system.DictService;
import org.evue.alistar.utils.BeanUtil;
import org.evue.alistar.utils.StringUtil;
import org.evue.alistar.warpper.DictWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DictController
 *
 * @author enilu
 * @version 2018/11/17 0017
 */
@RestController
@RequestMapping("/dict")
public class DictController extends BaseController {
    @Autowired
    private DictService dictService;

    /**
     * 获取所有字典列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.DICT})
    public Object list(String name) {

        if (StringUtil.isNotEmpty(name)) {
            List<Dict> list = dictService.findByNameLike(name);
            return Rets.success(new DictWarpper(BeanUtil.objectsToMaps(list)).warp());
        }
        List<Dict> list = dictService.findByPid(0L);
        return Rets.success(new DictWarpper(BeanUtil.objectsToMaps(list)).warp());
    }

    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "添加字典", key = "dictName", dict = DictMap.class)
    @RequiresPermissions(value = {Permission.DICT_EDIT})
    public Object add(String dictName, String dictValues) {
        if (BeanUtil.isOneEmpty(dictName, dictValues)) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        dictService.addDict(dictName, dictValues);
        return Rets.success();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @BussinessLog(value = "修改字典", key = "dictName", dict = DictMap.class)
    @RequiresPermissions(value = {Permission.DICT_EDIT})
    public Object update(Long id, String dictName, String dictValues) {
        if (BeanUtil.isOneEmpty(dictName, dictValues)) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        dictService.editDict(id, dictName, dictValues);
        return Rets.success();
    }


    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除字典", key = "id", dict = DictMap.class)
    @RequiresPermissions(value = {Permission.DICT_EDIT})
    public Object delete(@RequestParam Long id) {
        dictService.delteDict(id);
        return Rets.success();
    }

}
