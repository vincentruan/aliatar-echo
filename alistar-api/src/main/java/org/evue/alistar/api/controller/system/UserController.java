package org.evue.alistar.api.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.evue.alistar.api.controller.BaseController;
import org.evue.alistar.bean.constant.Const;
import org.evue.alistar.bean.constant.factory.PageFactory;
import org.evue.alistar.bean.constant.state.ManagerStatus;
import org.evue.alistar.bean.core.BussinessLog;
import org.evue.alistar.bean.dictmap.UserDict;
import org.evue.alistar.bean.dto.UserDto;
import org.evue.alistar.bean.entity.system.User;
import org.evue.alistar.bean.enumeration.BizExceptionEnum;
import org.evue.alistar.bean.enumeration.Permission;
import org.evue.alistar.bean.exception.ApplicationException;
import org.evue.alistar.bean.vo.front.Rets;
import org.evue.alistar.bean.vo.query.SearchFilter;
import org.evue.alistar.core.factory.UserFactory;
import org.evue.alistar.service.system.UserService;
import org.evue.alistar.utils.BeanUtil;
import org.evue.alistar.utils.MD5;
import org.evue.alistar.utils.RandomUtil;
import org.evue.alistar.utils.StringUtil;
import org.evue.alistar.utils.factory.Page;
import org.evue.alistar.warpper.UserWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * UserController
 *
 * @author enilu
 * @version 2018/9/15 0015
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.USER})
    public Object list(@RequestParam(required = false) String account,
                       @RequestParam(required = false) String name) {
        Page page = new PageFactory().defaultPage();
        if (StringUtil.isNotEmpty(name)) {
            page.addFilter(SearchFilter.build("name", SearchFilter.Operator.LIKE, name));
        }
        if (StringUtil.isNotEmpty(account)) {
            page.addFilter(SearchFilter.build("account", SearchFilter.Operator.LIKE, account));
        }
        page.addFilter(SearchFilter.build("status", SearchFilter.Operator.GT, 0));
        page = userService.queryPage(page);
        List list = (List) new UserWarpper(BeanUtil.objectsToMaps(page.getRecords())).warp();
        page.setRecords(list);
        return Rets.success(page);
    }

    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑账号", key = "name", dict = UserDict.class)
    @RequiresPermissions(value = {Permission.USER_EDIT})
    public Object save(@Valid UserDto user, BindingResult result) {
        if (user.getId() == null) {
            // 判断账号是否重复
            User theUser = userService.findByAccount(user.getAccount());
            if (theUser != null) {
                throw new ApplicationException(BizExceptionEnum.USER_ALREADY_REG);
            }
            // 完善账号信息
            user.setSalt(RandomUtil.getRandomString(5));
            user.setPassword(MD5.md5(user.getPassword(), user.getSalt()));
            user.setStatus(ManagerStatus.OK.getCode());
            userService.insert(UserFactory.createUser(user, new User()));
        } else {
            User oldUser = userService.get(user.getId());
            userService.update(UserFactory.updateUser(user, oldUser));
        }
        return Rets.success();
    }

    @BussinessLog(value = "删除账号", key = "userId", dict = UserDict.class)
    @RequestMapping(method = RequestMethod.DELETE)
    @RequiresPermissions(value = {Permission.USER_DEL})
    public Object remove(@RequestParam Long userId) {
        if (userId == null) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        if (userId.intValue() <= 2) {
            return Rets.failure("不能删除初始用户");
        }
        User user = userService.get(userId);
        user.setStatus(ManagerStatus.DELETED.getCode());
        userService.update(user);
        return Rets.success();
    }

    @BussinessLog(value = "设置账号角色", key = "userId", dict = UserDict.class)
    @RequestMapping(value = "/setRole", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.USER_EDIT})
    public Object setRole(@RequestParam("userId") Long userId, @RequestParam("roleIds") String roleIds) {
        if (BeanUtil.isOneEmpty(userId, roleIds)) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能修改超级管理员
        if (userId.intValue() == Const.ADMIN_ID.intValue()) {
            throw new ApplicationException(BizExceptionEnum.CANT_CHANGE_ADMIN);
        }
        User user = userService.get(userId);
        user.setRoleid(roleIds);
        userService.update(user);
        return Rets.success();
    }

    @BussinessLog(value = "冻结/解冻账号", key = "userId", dict = UserDict.class)
    @RequestMapping(value = "changeStatus", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.USER_EDIT})
    public Object changeStatus(@RequestParam Long userId) {
        if (userId == null) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = userService.get(userId);
        user.setStatus(user.getStatus().intValue() == ManagerStatus.OK.getCode() ? ManagerStatus.FREEZED.getCode() : ManagerStatus.OK.getCode());
        userService.update(user);
        return Rets.success();
    }

}
