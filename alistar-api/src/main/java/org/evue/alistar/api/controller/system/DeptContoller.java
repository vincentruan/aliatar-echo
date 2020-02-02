package org.evue.alistar.api.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.evue.alistar.api.controller.BaseController;
import org.evue.alistar.bean.core.BussinessLog;
import org.evue.alistar.bean.dictmap.DeptDict;
import org.evue.alistar.bean.entity.system.Dept;
import org.evue.alistar.bean.enumeration.BizExceptionEnum;
import org.evue.alistar.bean.enumeration.Permission;
import org.evue.alistar.bean.exception.ApplicationException;
import org.evue.alistar.bean.vo.front.Rets;
import org.evue.alistar.bean.vo.node.DeptNode;
import org.evue.alistar.service.system.DeptService;
import org.evue.alistar.service.system.LogObjectHolder;
import org.evue.alistar.utils.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * DeptContoller
 *
 * @author enilu
 * @version 2018/9/15 0015
 */
@RestController
@RequestMapping("/dept")
public class DeptContoller extends BaseController {
    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.DEPT})
    public Object list() {
        List<DeptNode> list = deptService.queryAllNode();
        return Rets.success(list);
    }

    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑部门", key = "simplename", dict = DeptDict.class)
    @RequiresPermissions(value = {Permission.DEPT_EDIT})
    public Object save(@ModelAttribute @Valid Dept dept) {
        if (BeanUtil.isOneEmpty(dept, dept.getSimplename())) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        if (dept.getId() != null) {
            Dept old = deptService.get(dept.getId());
            LogObjectHolder.me().set(old);
            old.setPid(dept.getPid());
            old.setSimplename(dept.getSimplename());
            old.setFullname(dept.getFullname());
            old.setNum(dept.getNum());
            old.setTips(dept.getTips());
            deptService.deptSetPids(old);
            deptService.update(old);
        } else {
            deptService.deptSetPids(dept);
            deptService.insert(dept);
        }
        return Rets.success();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除部门", key = "id", dict = DeptDict.class)
    @RequiresPermissions(value = {Permission.DEPT_DEL})
    public Object remove(@RequestParam Long id) {
        logger.info("id:{}", id);
        if (id == null) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        deptService.deleteDept(id);
        return Rets.success();
    }
}
