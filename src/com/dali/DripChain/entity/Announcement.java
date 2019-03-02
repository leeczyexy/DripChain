package com.dali.DripChain.entity;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_announcement", schema = "db_dripchain", catalog = "")
public class Announcement {
    private int id;
    private String sGovDepartment;
    private String sContent;
    private String sTitle;
    private Integer iType;
    private Timestamp sTime;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sGovDepartment", nullable = false, length = 15)
    public String getsGovDepartment() {
        return sGovDepartment;
    }

    public void setsGovDepartment(String sGovDepartment) {
        this.sGovDepartment = sGovDepartment;
    }

    @Basic
    @Column(name = "sContent", nullable = false, length = 20000)
    public String getsContent() {
        return sContent;
    }

    public void setsContent(String sContent) {
        this.sContent = sContent;
    }

    @Basic
    @Column(name = "sTitle", nullable = false, length = 100)
    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    @Basic
    @Column(name = "iType", nullable = true)
    public Integer getiType() {
        return iType;
    }

    public void setiType(Integer iType) {
        this.iType = iType;
    }

    @Basic
    @Column(name = "sTime", nullable = false)
    public Timestamp getsTime() {
        return sTime;
    }

    public void setsTime(Timestamp sTime) {
        this.sTime = sTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return id == that.id &&
                Objects.equals(sGovDepartment, that.sGovDepartment) &&
                Objects.equals(sContent, that.sContent) &&
                Objects.equals(sTitle, that.sTitle) &&
                Objects.equals(iType, that.iType) &&
                Objects.equals(sTime, that.sTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sGovDepartment, sContent, sTitle, iType, sTime);
    }
}
