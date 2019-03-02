package com.dali.DripChain.service;

import com.dali.DripChain.dao.AlarmDao;
import com.dali.DripChain.entity.Alarm;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@Service(value="AndAlarmService")
public class AndAlarmService extends HibernateDaoSupport {
    @Resource
    private AlarmDao alarmDao;

    public List<Alarm> findByEntity(int id){
        List<Alarm> list= new ArrayList<Alarm>();
        list= (List<Alarm>)this.getHibernateTemplate().find("from Alarm as a where iCompanyId=?");
        if(list.size()>=1){
            return list;
        }
        else{
            return null;
        }
    }
}
