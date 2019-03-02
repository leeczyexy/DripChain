package com.dali.DripChain.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_devicegroup", schema = "db_dripchain", catalog = "")
public class DeviceGroup {
    private int id; //分组id
    private Company company;//公司  （外键关联iComPanyId）
    private String sGroupName;//设备分组名称
    private Set<Device> devices;//改设备组下的设备集合

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

    @Basic
    @Column(name = "sGroupName")
    public String getsGroupName() {
        return sGroupName;
    }

    public void setsGroupName(String sGroupName) {
        this.sGroupName = sGroupName;
    }

    @OneToMany(mappedBy = "deviceGroup",cascade = CascadeType.ALL)
    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceGroup that = (DeviceGroup) o;
        return id == that.id &&
                Objects.equals(company, that.company) &&
                Objects.equals(sGroupName, that.sGroupName) &&
                Objects.equals(devices, that.devices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, sGroupName, devices);
    }

    @Override
    public String toString() {
        return "DeviceGroup{" +
                "id=" + id +
                ", company=" + company +
                ", sGroupName='" + sGroupName + '\'' +
                '}';
    }
}
