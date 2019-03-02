package com.dali.DripChain.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDao<T> extends HibernateDaoSupport {

    protected Class<T> clazz;

    public BaseDao() {
        ParameterizedType pt=(ParameterizedType)this.getClass().getGenericSuperclass();
        this.clazz=(Class<T>)pt.getActualTypeArguments()[0];
    }
    //添加 返回插入记录的id
    public int save(T entity) {
        return (int)this.getHibernateTemplate().save(entity);
    }
    //修改
    public int update(T entity) {
        try {
            this.getHibernateTemplate().update(entity);
            return 1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("BaseDao的update方法失败");
        }
        return 0;
    }
    //保存或修改 save时不返回id
    public int saveOrUpdate(T entity) {
        try {
            this.getHibernateTemplate().saveOrUpdate(entity);
            return 1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("BaseDao的saveOrUpdate方法失败");
        }
        return 0;
    }
    //根据实体类删除
    public int delete(T entity) {
        try {
            this.getHibernateTemplate().delete(entity);
            return 1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("BaseDao的delete方法失败");
        }
        return 0;
    }
    //根据id查询
    public T findById(int id) {
        T entity=null;
        try {
            entity=(T) this.getHibernateTemplate().get(clazz, id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("BaseDao的findById方法失败");
        }
        return entity;
    }
    //查询全部
    public List<T> findAll() {
        List<T> list= null;
        try {
            list = (List<T>)this.getHibernateTemplate().find("from"+clazz.getName());
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("BaseDao的findAll方法失败");

        }
        return list;
    }
    //按条件查询
    public List<T> query(String hql, Object...params) {
        List<T> list= null;
        try {
            list = (List<T>)this.getHibernateTemplate().find(hql,params);
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("BaseDao的query方法失败");
        }
        return list;
    }
    //按条件删除
    public int delete(String hql, Object...params) {
        try {
            List<T> list=(List<T>)this.getHibernateTemplate().find(hql,params);
            if(list.size()>0){
                for(T entity:list){
                    this.getHibernateTemplate().delete(entity);
                }
                return 1;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("BaseDao的delete方法失败");
        }
        return 0;
    }
    //根据字符串数组ids批量删除
    public int delete(String[] ids,String hql) {
        T entity=null;
        try {
            for (int i = 0; i < ids.length; i++) {
                List<T> list = (List<T>)this.getHibernateTemplate().find(hql,Integer.parseInt(ids[i]));
                if(list.size()>0){
                    entity=list.get(0);
                    this.getHibernateTemplate().delete(entity);
                }
            }
            return 1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("BaseDao的delete方法失败");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("delete方法Integer.parseInt失败");
        }
        return 0;
    }

}
