package com.dali.DripChain.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_contact", schema = "db_dripchain", catalog = "")
public class Contact {
    private int id;//联系人id
    private ContactGroup contactGroup;//联系人组Id   (外键关联iContactgroupId)
    private String sEmail;//邮箱
    private String sName;//联系人姓名
    private String sNote;//备注
    private String sPhone;//电话
    private String sWeChat;//微信

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
    @JoinColumn(name = "iContactGroupId")
    public ContactGroup getContactGroup() {
        return contactGroup;
    }

    public void setContactGroup(ContactGroup contactGroup) {
        this.contactGroup = contactGroup;
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
    @Column(name = "sName")
    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    @Basic
    @Column(name = "sNote")
    public String getsNote() {
        return sNote;
    }

    public void setsNote(String sNote) {
        this.sNote = sNote;
    }

    @Basic
    @Column(name = "sPhone")
    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    @Basic
    @Column(name = "sWeChat")
    public String getsWeChat() {
        return sWeChat;
    }

    public void setsWeChat(String sWeChat) {
        this.sWeChat = sWeChat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id &&
                Objects.equals(contactGroup, contact.contactGroup) &&
                Objects.equals(sEmail, contact.sEmail) &&
                Objects.equals(sName, contact.sName) &&
                Objects.equals(sNote, contact.sNote) &&
                Objects.equals(sPhone, contact.sPhone) &&
                Objects.equals(sWeChat, contact.sWeChat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contactGroup, sEmail, sName, sNote, sPhone, sWeChat);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", contactGroup=" + contactGroup +
                ", sEmail='" + sEmail + '\'' +
                ", sName='" + sName + '\'' +
                ", sNote='" + sNote + '\'' +
                ", sPhone='" + sPhone + '\'' +
                ", sWeChat='" + sWeChat + '\'' +
                '}';
    }
}
