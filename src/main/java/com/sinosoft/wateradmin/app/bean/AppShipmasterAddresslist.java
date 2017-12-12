package com.sinosoft.wateradmin.app.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.wateradmin.common.BaseBean;
import com.sinosoft.wateradmin.common.CustomDateDeserializer;
import com.sinosoft.wateradmin.common.CustomDateSerializer;

import java.util.Date;


/**
 * 采沙/运沙船主通讯录
 * added by lvzhixue 2017-11-06 11:50
 */
public class AppShipmasterAddresslist extends BaseBean {
    private Integer asaId;

    private String asaName;

    private String asaSex;

    private String asaPosition;

    private String asaIdCardNo;

    private String asaEmail;

    private String asaTel;

    private String asaType;

    private Integer countryId;

    private Integer cityId;

    private String street;

    private String zipcode;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date birthday;

    public Integer getAsaId() {
        return asaId;
    }

    public void setAsaId(Integer asaId) {
        this.asaId = asaId;
    }

    public String getAsaName() {
        return asaName;
    }

    public void setAsaName(String asaName) {
        this.asaName = asaName;
    }

    public String getAsaSex() {
        return asaSex;
    }

    public void setAsaSex(String asaSex) {
        this.asaSex = asaSex;
    }

    public String getAsaPosition() {
        return asaPosition;
    }

    public void setAsaPosition(String asaPosition) {
        this.asaPosition = asaPosition;
    }

    public String getAsaIdCardNo() {
        return asaIdCardNo;
    }

    public void setAsaIdCardNo(String asaIdCardNo) {
        this.asaIdCardNo = asaIdCardNo;
    }

    public String getAsaEmail() {
        return asaEmail;
    }

    public void setAsaEmail(String asaEmail) {
        this.asaEmail = asaEmail;
    }

    public String getAsaTel() {
        return asaTel;
    }

    public void setAsaTel(String asaTel) {
        this.asaTel = asaTel;
    }

    public String getAsaType() {
        return asaType;
    }

    public void setAsaType(String asaType) {
        this.asaType = asaType;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}