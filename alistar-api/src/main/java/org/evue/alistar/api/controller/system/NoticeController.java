package org.evue.alistar.api.controller.system;

import com.google.common.base.Strings;
import org.evue.alistar.api.controller.BaseController;
import org.evue.alistar.bean.entity.system.Notice;
import org.evue.alistar.bean.vo.front.Rets;
import org.evue.alistar.service.system.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * NoticeController
 *
 * @author enilu
 * @version 2018/12/17 0017
 */
@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 获取通知列表
     */
    @RequestMapping(value = "/list")
    public Object list(String condition) {
        List<Notice> list = null;
        if (Strings.isNullOrEmpty(condition)) {
            list = noticeService.queryAll();
        } else {
            list = noticeService.findByTitleLike(condition);
        }
        return Rets.success(list);
    }
}
