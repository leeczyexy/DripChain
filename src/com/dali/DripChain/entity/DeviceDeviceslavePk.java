package com.dali.DripChain.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device_deviceslave_pk", schema = "db_dripchain", catalog = "")
public
class DeviceDeviceslavePk implements Serializable {
    private static final long serialVersionUID = 1L;
    private int iDeviceId;
    private int iDeviceSlaveId;

    @Id
    @Column(name = "iDeviceId", nullable = false)
    public int getiDeviceId() {
        return iDeviceId;
    }

    public void setiDeviceId(int iDeviceId) {
        this.iDeviceId = iDeviceId;
    }

    @Id
    @Column(name = "iDeviceSlaveId", nullable = false)
    public int getiDeviceSlaveId() {
        return iDeviceSlaveId;
    }

    public void setiDeviceSlaveId(int iDeviceSlaveId) {
        this.iDeviceSlaveId = iDeviceSlaveId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDeviceslavePk that = (DeviceDeviceslavePk) o;
        return iDeviceId == that.iDeviceId &&
                iDeviceSlaveId == that.iDeviceSlaveId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(iDeviceId, iDeviceSlaveId);
    }
}
