package com.wl.common.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-15 13:46
 **/

public class ResultSet<T extends BaseObject> {
    long total;
    List<T> rows = new ArrayList();

    public ResultSet() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}