package org.evue.alistar.bean.vo.offcialsite;

import lombok.Data;

import java.util.Date;

@Data
public class Reply {
    private String content;
    private Date createAt;
    private Author author;
}
