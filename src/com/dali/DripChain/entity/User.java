package com.dali.DripChain.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_user", schema = "db_dripchain", catalog = "")
public class User {
    private int id;//二级用户id
    private Company company;//所属公司
    private String sUserRight;//用户权限
    private String sUsername;//用户名
    private String sPassword;//用户密码
    private String sEmail;//用户邮箱
    private String sTelephone;//用户电话
    private UserInfo userInfo;//用户详细信息（外键关联由UserInfo进行维护 键名：iUserId）

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
    @Column(name = "sUserRight")
    public String getsUserRight() {
        return sUserRight;
    }

    public void setsUserRight(String sUserRight) {
        this.sUserRight = sUserRight;
    }

    @Basic
    @Column(name = "sUsername")
    public String getsUsername() {
        return sUsername;
    }

    public void setsUsername(String sUsername) {
        this.sUsername = sUsername;
    }

    @Basic
    @Column(name = "sPassword")
    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    @Basic
    @Column(name = "sEmail")
    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    @Basic
    @Column(name = "sTelephone")
    public String getsTelephone() {
        return sTelephone;
    }

    public void setsTelephone(String sTelephone) {
        this.sTelephone = sTelephone;
    }

    @OneToOne(mappedBy = "user")
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(company, user.company) &&
                Objects.equals(sUserRight, user.sUserRight) &&
                Objects.equals(sUsername, user.sUsername) &&
                Objects.equals(sPassword, user.sPassword) &&
                Objects.equals(sEmail, user.sEmail) &&
                Objects.equals(sTelephone, user.sTelephone) &&
                Objects.equals(userInfo, user.userInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, sUserRight, sUsername, sPassword, sEmail, sTelephone, userInfo);
    }
}
