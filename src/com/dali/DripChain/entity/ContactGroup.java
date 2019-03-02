package com.dali.DripChain.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_contactgroup", schema = "db_dripchain", catalog = "")
public class ContactGroup {
    //一对一：
    //一对多：
    //多对一：联系人组ContactGroup
    //多对多：
    private int id;//联系人组id
    private String sGroupName;//联系人组名

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sGroupName")
    public String getsGroupName() {
        return sGroupName;
    }

    public void setsGroupName(String sGroupName) {
        this.sGroupName = sGroupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactGroup that = (ContactGroup) o;
        return id == that.id &&
                Objects.equals(sGroupName, that.sGroupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sGroupName);
    }
}
