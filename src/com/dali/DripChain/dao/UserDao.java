package com.dali.DripChain.dao;
import com.dali.DripChain.entity.User;

import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository(value="userDao")
public class UserDao extends HibernateDaoSupport {

    public int addUser(User user) {
        return (int) this.getHibernateTemplate().save(user);
    }

    public int updateUser(User user) {
        try {
            this.getHibernateTemplate().update(user);
            return 1;
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteUser(User user) {
        try {
            this.getHibernateTemplate().delete(user);
            return 1;
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public User findUserById(int id) {
        return this.getHibernateTemplate().get(User.class, id);
    }

    public List<User> findUsers() {
        List<User> list = (List<User>)this.getHibernateTemplate().find("from User");
        return  list;
    }

    public List<User> findUsersByPage(int begin, int pageSize) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        // 查询分页数据
        List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
        return list;
    }

    public int findCount() {
        List<Long> list = (List<Long>) this.getHibernateTemplate().find("select count(*) from User");
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    public User findByEntity(User user1){
        List<User> list= new ArrayList<User>();
        Logger log= LoggerFactory.getLogger(UserDao.class);
        log.debug(user1.getsPassword());
        log.debug(user1.getsUsername());
        list= (List<User>) this.getHibernateTemplate().find("from User u where sUsername=? and sPassword=?",user1.getsUsername(),user1.getsPassword());
        if(list.size()==1){
            return list.get(0);
        }
        else{
            return null;
        }
    }
}
