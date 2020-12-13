package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class linkman {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String tel;
    private String mail;
    private String address;
    private String qq;

    //public int t = 0;
    public linkman(String name, String tel, String mail, String address, String qq) {
        this.name = name;
        this.tel = tel;
        this.mail = mail;
        this.address = address;
        this.qq = qq;
        //this.t = t;
    }

    public linkman() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //public linkman(){}
    public String getName() {
        return this.name;
    }

    public String getTel() {
        return this.tel;
    }

    public String getMail() {
        return this.mail;
    }

    public String getAddress() {
        return this.address;
    }

    public String getQq() {
        return this.qq;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}