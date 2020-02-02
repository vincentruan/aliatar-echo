package org.evue.alistar.bean.exception;

/**
 * 定义通用异常
 * code 存储异常代码
 *
 * @author czhou
 */
public class XSException extends Exception {

    private static final long serialVersionUID = 1L;

    private String code;

    public XSException(String code, String message, Throwable t) {
        super(message, t);
        this.code = code;
    }

    public XSException(String code, String message) {
        this(code, message, null);
    }

    public String getCode() {
        return this.code;
    }

}
