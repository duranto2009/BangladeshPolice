package com.peoplentech.bangladeshpolice;

/**
 * Created by User on 1/23/2018.
 */

public class Dmp {
    private int id;
    private String email;
    private String designation;
    private String mobile;
    private String phone;
    private String fax;

    public Dmp(int id, String email, String designation, String mobile, String phone, String fax) {
        this.id = id;
        this.email = email;
        this.designation = designation;
        this.mobile = mobile;
        this.phone = phone;
        this.fax = fax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String fax) {
        this.phone = phone;
    }
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
