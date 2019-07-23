package com.wl.common.exception;

import com.wl.common.enummodel.JrsfEnum;

/**
 * @program: blog
 * @description: 自定义错误类
 * @author: WangLei
 * @create: 2019-07-22 17:42
 **/
public class JrsfException extends RuntimeException {
    private int code;

    public JrsfException(int code, Exception e) {
        super(e);
        this.code = JrsfEnum.ErrCode.业务异常.getValue();
        this.code = code;
    }

    public JrsfException(int code, String msg) {
        super(msg);
        this.code = JrsfEnum.ErrCode.业务异常.getValue();
        this.code = code;
    }

    public JrsfException() {
        this.code = JrsfEnum.ErrCode.业务异常.getValue();
    }

    public JrsfException(Exception e) {
        super(e);
        this.code = JrsfEnum.ErrCode.业务异常.getValue();
    }

    public JrsfException(String msg) {
        super(msg);
        this.code = JrsfEnum.ErrCode.业务异常.getValue();
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}