package com.wl.common.util;

import com.wl.common.enummodel.JrsfEnum;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 18:03
 **/
public class JrsfReturn {
    private int code;
    private String msg;
    private Object data;

    private JrsfReturn() {
        this.setCode(200);
        this.setMsg("操作成功");
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static JrsfReturn error(String msg) {
        return error(JrsfEnum.ErrCode.业务异常.getValue(), msg);
    }

    public static JrsfReturn error(Exception ex) {
        return JrsfServiceUtils.getExpCodeMsg(ex);
    }

    public static JrsfReturn error(int code, String msg) {
        JrsfReturn r = new JrsfReturn();
        r.setCode(code);
        r.setMsg(msg);
        EvLog.error(msg);
        return r;
    }

    public static JrsfReturn okMsg(String msg) {
        JrsfReturn r = new JrsfReturn();
        r.setMsg(msg);
        return r;
    }

    public static JrsfReturn okData(Object data) {
        JrsfReturn r = new JrsfReturn();
        r.setData(data);
        return r;
    }

    public static JrsfReturn ok() {
        JrsfReturn r = new JrsfReturn();
        return r;
    }
}
