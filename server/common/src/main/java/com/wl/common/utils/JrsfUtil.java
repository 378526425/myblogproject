package com.wl.common.utils;

import com.querydsl.jpa.impl.JPAQuery;
import com.wl.common.entity.BaseObject;
import com.wl.common.entity.ResultSet;

import java.util.List;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-09-26 15:05
 **/
public class JrsfUtil {
    public static <T extends BaseObject> List<T> getListByJpaQuery(ResultSet<T> resultSet, JPAQuery<T> jpaQuery, int pageIndex, int pageSize) {
        resultSet.setTotal(Integer.valueOf(String.valueOf(jpaQuery.fetchCount())));
        if (pageIndex > 0 && pageSize > 0)
            jpaQuery.offset((pageIndex - 1) * pageSize).limit(pageSize);
        List<T> viewModels = jpaQuery.fetch();
        resultSet.setRows(viewModels);
        return viewModels;
    }
}
