package com.udit.bankexam.bean;

import java.io.Serializable;

/**
 * Created by zb on 2017/7/3.
 */

public class AddressBean implements Serializable{

    private static final long serialVersionUID = 474973543758042861L;

    private String uid;

    private String Name;

    private String Tel;

    private String Province;

    private String City;

    private String District;

    private String Addr;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }
}
