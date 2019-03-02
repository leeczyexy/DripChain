package com.dali.DripChain.dao;

import com.dali.DripChain.entity.Alarm;
import com.dali.DripChain.entity.Announcement;
import com.dali.DripChain.entity.Company;
import com.dali.DripChain.entity.DataPoint;
import com.dali.DripChain.entity.Device;
import com.dali.DripChain.entity.DeviceDeviceslavePk;
import com.dali.DripChain.entity.DeviceGroup;
import com.dali.DripChain.entity.DeviceSlave;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository(value="andDao")
public class AndDao extends HibernateDaoSupport {
    
    public Company findByEntity(Company company){
        List<Company> list= new ArrayList<Company>();
        list=  this.getHibernateTemplate().findByExample(company);
        //.find("from Company u where sUserName=? and sPassword=?",company.getsUserName(),company.getsPassword());
        if(list.size()==1){
            return list.get(0);
        }
        else{
            return null;
        }
    }
    
    public List<Alarm> getAlarmListById(Company company){
        List<Alarm>list=new ArrayList<Alarm>();
        list= (List<Alarm>) this.getHibernateTemplate().find("from Alarm as a where company=?",company);
        System.out.println("123112312132132");
        System.out.println(list.size());
        if(list.size()>0){
            return list;
        }
        else{
            return null;
        }
    }

    public List<Device> getDeviceListById(Company company){
        List<Device> list=new ArrayList<Device>();
        list= (List<Device>) this.getHibernateTemplate().find("from Device as d where company=?",company);
        if(list.size()>0){
            return list;
        }
        else{
            return null;
        }
    }

    public Device GetDeviceById(int deivceId,Company company){
        Device device=new Device();
        System.out.println("*********");
        System.out.println(deivceId);
        List<Device> list=new ArrayList<Device>();
        list= (List<Device>) this.getHibernateTemplate().find("from Device as d where id=? and company=?",deivceId,company);
        if(list.size()>0){
            return list.get(0);
        }
        else{
            return null;
        }

    }

    public DeviceDeviceslavePk getDeviceSlaveId(int deviceId){
        DeviceDeviceslavePk deviceDeviceslavePk=new DeviceDeviceslavePk();
        List<DeviceDeviceslavePk> list=new ArrayList<DeviceDeviceslavePk>();
        list= (List<DeviceDeviceslavePk>) this.getHibernateTemplate().find("from DeviceDeviceslavePk as ddp where iDeviceId=?",deviceId);
        if(list.size()>0){
            return list.get(0);
        }
        else{
            return null;
        }
    }

    public DeviceSlave getDeviceSlave(int id){
        List<DeviceSlave> list=new ArrayList<DeviceSlave>();
        list= (List<DeviceSlave>) this.getHibernateTemplate().find("from DeviceSlave  as ds where id=?",id);
        if(list.size()>0){
            return list.get(0);
        }
        else{
            return null;
        }
    }

    public DataPoint getDataPointIdBysName(String sName){
        List<DataPoint> list=new ArrayList<DataPoint>();
        list= (List<DataPoint>) this.getHibernateTemplate().find("from DataPoint as dp where sName=?",sName);
        if(list.size()>0){
            return list.get(0);
        }
        else{
            return null;
        }
    }
    public int addDevice(Device device){
        int iReturn = (int) this.getHibernateTemplate().save(device);
        return iReturn;
    }

    public DeviceGroup getDeviceGroup(int id){
        List<DeviceGroup> list=new ArrayList<DeviceGroup>();
        list= (List<DeviceGroup>) this.getHibernateTemplate().find("from DeviceGroup as dg where id =?",id);
        if(list==null)
            return null;
        else
            return list.get(0);
    }

    public int addCompany(Company company){
        return (int) this.getHibernateTemplate().save(company);
    }

    public List<Announcement> getAnnouncements(){
        return (List<Announcement>) this.getHibernateTemplate().find("from Announcement order by sTime");
    }

    public String updateCompany(Company company){
        this.getHibernateTemplate().update(company);
        return "1";
    }
}
