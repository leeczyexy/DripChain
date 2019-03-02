package com.dali.DripChain.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tb_alarm", schema = "db_dripchain", catalog = "")
public class Alarm {
    //一对一：
    //一对多：联系人Contact
    //多对一：公司Company 设备Device 数据点Datapoint  数据模板Datatemplate
    //多对多：
    private int id;//警报id
    private Company company;//企业id（用户id）(外键关联iCompanyId)
    private Device device;//设备id (外键关联iDeviceId)
    private DataTemplate dataTemplate;//数据模板ID  (外键关联iDatatemplateId)
    private DataPoint dataPoint;//数据点id (外键关联iDatapointId)
    private String sTriggerName;//触发器名称
    private String sTriggerCondition;//触发条件(0:开关ON、1:开关OFF、2:数值低于、3:数值高于、4:数值介于、5:数值高于max低于min)
    private int iInsertType;//数据存储方式(0:存储阈值、1:不存储、2:存储所有报警)
    private List<Integer> alarmModes = new ArrayList<Integer>();//存放报警模式(0:短信报警、1:微信报警、2:邮件报警)的集合
    private int iMax;//最大值
    private int iMin;//最小值
    private Set<Contact> contacts = new HashSet<Contact>();//存放报警联系人或报警组的数组
    private String sAbnormalContent;//报警触发时推送内容
    private String sNomalContent;//报警恢复时推送内容
    private Date dUpdateTime;//更新时间

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "iCompanyId")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne
    @JoinColumn(name = "iDeviceId")
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @ManyToOne
    @JoinColumn(name = "iDataTemplateId")
    public DataTemplate getDataTemplate() {
        return dataTemplate;
    }

    public void setDataTemplate(DataTemplate dataTemplate) {
        this.dataTemplate = dataTemplate;
    }

    @ManyToOne
    @JoinColumn(name = "iDataPointId")
    public DataPoint getDataPoint() {
        return dataPoint;
    }

    public void setDataPoint(DataPoint dataPoint) {
        this.dataPoint = dataPoint;
    }

    @Basic
    @Column(name = "sTriggerName")
    public String getsTriggerName() {
        return sTriggerName;
    }

    public void setsTriggerName(String sTriggerName) {
        this.sTriggerName = sTriggerName;
    }

    @Basic
    @Column(name = "sTriggerCondition")
    public String getsTriggerCondition() {
        return sTriggerCondition;
    }

    public void setsTriggerCondition(String sTriggerCondition) {
        this.sTriggerCondition = sTriggerCondition;
    }

    @Basic
    @Column(name = "iInsertType")
    public int getiInsertType() {
        return iInsertType;
    }

    public void setiInsertType(int iInsertType) {
        this.iInsertType = iInsertType;
    }

    @ElementCollection(fetch=FetchType.LAZY,targetClass=Integer.class) //加载策略  懒加载//指定元素中集合的类型
    @CollectionTable(name="Alarm_alarmModes_list",//指定集合生成的表
            joinColumns = @JoinColumn(name ="iAlarmId",nullable = false))//指定外键列的名字，以及不为空
    @OrderColumn(name="list_order") //指定排序列的名称
    @Column(name = "alarmModes")
    public List<Integer> getAlarmModes() {
        return alarmModes;
    }

    public void setAlarmModes(List<Integer> alarmModes) {
        this.alarmModes = alarmModes;
    }

    @Basic
    @Column(name = "iMax")
    public int getiMax() {
        return iMax;
    }

    public void setiMax(int iMax) {
        this.iMax = iMax;
    }

    @Basic
    @Column(name = "iMin")
    public int getiMin() {
        return iMin;
    }

    public void setiMin(int iMin) {
        this.iMin = iMin;
    }

    @ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,targetEntity=Contact.class) //延迟加载//级联删除//目标对象
    /** 中间表*/
    @JoinTable(name="Alarm_Contact_PK", //指定中间表名称
            joinColumns=@JoinColumn(name="iAlarmId"), //@ManyToMany中没有加mappedby的主键列
            inverseJoinColumns=@JoinColumn(name="iContactId")) //@ManyToMany中加mappedby的主键列
    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Basic
    @Column(name = "sAbnormalContent")
    public String getsAbnormalContent() {
        return sAbnormalContent;
    }

    public void setsAbnormalContent(String sAbnormalContent) {
        this.sAbnormalContent = sAbnormalContent;
    }

    @Basic
    @Column(name = "sNomalContent")
    public String getsNomalContent() {
        return sNomalContent;
    }

    public void setsNomalContent(String sNomalContent) {
        this.sNomalContent = sNomalContent;
    }

    @Basic
    @Column(name = "dUpdateTime")
    public Date getdUpdateTime() {
        return dUpdateTime;
    }

    public void setdUpdateTime(Date dUpdateTime) {
        this.dUpdateTime = dUpdateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alarm alarm = (Alarm) o;
        return id == alarm.id &&
                iInsertType == alarm.iInsertType &&
                iMax == alarm.iMax &&
                iMin == alarm.iMin &&
                Objects.equals(company, alarm.company) &&
                Objects.equals(device, alarm.device) &&
                Objects.equals(dataTemplate, alarm.dataTemplate) &&
                Objects.equals(dataPoint, alarm.dataPoint) &&
                Objects.equals(sTriggerName, alarm.sTriggerName) &&
                Objects.equals(sTriggerCondition, alarm.sTriggerCondition) &&
                Objects.equals(alarmModes, alarm.alarmModes) &&
                Objects.equals(contacts, alarm.contacts) &&
                Objects.equals(sAbnormalContent, alarm.sAbnormalContent) &&
                Objects.equals(sNomalContent, alarm.sNomalContent) &&
                Objects.equals(dUpdateTime, alarm.dUpdateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, device, dataTemplate, dataPoint, sTriggerName, sTriggerCondition, iInsertType, alarmModes, iMax, iMin, contacts, sAbnormalContent, sNomalContent, dUpdateTime);
    }
}
