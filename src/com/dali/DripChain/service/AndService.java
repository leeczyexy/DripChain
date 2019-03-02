package com.dali.DripChain.service;

import com.dali.DripChain.dao.AndDao;
import com.dali.DripChain.entity.Alarm;
import com.dali.DripChain.entity.Announcement;
import com.dali.DripChain.entity.Company;
import com.dali.DripChain.entity.DataPoint;
import com.dali.DripChain.entity.Device;
import com.dali.DripChain.entity.DeviceDeviceslavePk;
import com.dali.DripChain.entity.DeviceGroup;
import com.dali.DripChain.entity.DeviceSlave;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@Service(value="andUserService")
public class AndService {

	@Resource
	private AndDao andDao;

	public Company userLogin(Company company){
		return andDao.findByEntity(company);
	}

	public List<Alarm> getAlarmlist(Company company){
		return andDao.getAlarmListById(company);
	}

	public List<Device> getDevicelist(Company company){ return andDao.getDeviceListById(company); }

	public List<String> getDataPrepare(int deviceId,Company company){
		Device device=new Device();
		device=andDao.GetDeviceById(deviceId,company);
		DeviceDeviceslavePk deviceDeviceslavePk=andDao.getDeviceSlaveId(deviceId);
		DeviceSlave deviceSlave=andDao.getDeviceSlave(deviceDeviceslavePk.getiDeviceSlaveId());
		int iSlaveIndex= Integer.parseInt(deviceSlave.getsSlaveIndex());
		DataPoint dataPoint=andDao.getDataPointIdBysName(deviceSlave.getsSlaveName());
		List<String> list =new ArrayList<String>();
		list.add(device.getsDeviceId());
		list.add(String.valueOf(iSlaveIndex));
		list.add(String.valueOf(dataPoint.getId()));
		return list;
	}

	public int addDevice (Device device){
	    return andDao.addDevice(device);
    }

    public DeviceGroup getDeviceGroup(int id){
	    return andDao.getDeviceGroup(id);
    }

    public int addCompany(Company company){
	    return andDao.addCompany(company);
    }

    public List<Announcement> getAnnouncements(){
	    return andDao.getAnnouncements();
    }

    public String updateCompany(Company company){
	    return andDao.updateCompany(company);
    }

}
