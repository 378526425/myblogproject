package com.wl.common.dao;

import com.wl.common.entity.BaseObject;
import com.wl.common.exception.JrsfException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

/**
 * @program: blog
 * @description: 通用Dao层
 * @author: WangLei
 * @create: 2019-07-22 17:39
 **/
@Service
@Component
public class BaseDao {
    @PersistenceContext
    EntityManager entityManager;

    public BaseDao() {
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T extends BaseObject> void save(T model) {
        try {
            this.entityManager.persist(model);
        } catch (Exception var3) {
            throw new JrsfException(var3);
        }
    }

    public void delete(BaseObject bo) {
        try {
            this.entityManager.remove(bo);
        } catch (Exception var3) {
            throw new JrsfException(var3);
        }
    }

    public <T extends BaseObject> void update(T model) {
        try {
            this.entityManager.merge(model);
        } catch (Exception var3) {
            throw new JrsfException(var3);
        }
    }

    public <T> T getObjectByKey(Class<T> clazz, String id) {
        try {
            T obj = this.entityManager.find(clazz, id);
            return obj;
        } catch (Exception var4) {
            throw new JrsfException(var4);
        }
    }

    public <T extends BaseObject> Query createQuery(Class<T> clazz) {
        try {
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(clazz);
            Root<T> root = cq.from(clazz);
            cq.select(root);
            Query q = this.entityManager.createQuery(cq);
            return q;
        } catch (Exception var6) {
            throw new JrsfException(var6);
        }
    }

    public <T extends BaseObject> List<T> getObjects(Class<T> clazz) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        cq.select(root);
        Query q = this.entityManager.createQuery(cq);
        List<T> result = q.getResultList();
        return result;
    }
}