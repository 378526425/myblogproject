package com.wl.common.enummodel;

import com.wl.common.exception.JrsfException;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 17:45
 **/
public class JrsfEnum {
    public JrsfEnum() {
    }

    public static enum ErrCode {
        系统异常,
        业务异常,
        没有服务类,
        没有ViewModel类,
        没有Model类,
        用户名缺失,
        密码缺失,
        用户名或密码错误,
        用户被禁用,
        用户没有注册,
        Token缺失;

        public final int beginValue = 500;
        private final int value;

        private ErrCode() {
            this.getClass();
            this.value = 500 + this.ordinal();
        }

        public int getValue() {
            return this.value;
        }

        public JrsfEnum.ErrCode valueOf(int value) {
            int val = value > 500 ? value - 500 : value;
            int leng = values().length;
            if (val > -1 && val < leng) {
                return values()[val];
            } else {
                throw new JrsfException(value + "不存在对应JrsfErrCode枚举值");
            }
        }
    }
}
