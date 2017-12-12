package com.sinosoft.wateradmin.app.bean;

import com.sinosoft.wateradmin.common.BaseBean;

/**
 * 执法人员通讯录
 * added by lvzhixue 2017-11-06 11:50
 */
public class AppTipstaffAddresslist extends BaseBean {
    private Integer tipstaffId;

    private String tipstaffName;

    private String tipstaffSex;

    private String tipstaffOrg;

    private String tipstaffDepartment;

    private String tipstaffPosition;

    private String tipstaffPersonalMailbox;

    private String phoneNumber;

    private String remark;

    public Integer getTipstaffId() {
        return tipstaffId;
    }

    public void setTipstaffId(Integer tipstaffId) {
        this.tipstaffId = tipstaffId;
    }

    public String getTipstaffName() {
        return tipstaffName;
    }

    public void setTipstaffName(String tipstaffName) {
        this.tipstaffName = tipstaffName;
    }

    public String getTipstaffSex() {
        return tipstaffSex;
    }

    public void setTipstaffSex(String tipstaffSex) {
        this.tipstaffSex = tipstaffSex;
    }

    public String getTipstaffOrg() {
        return tipstaffOrg;
    }

    public void setTipstaffOrg(String tipstaffOrg) {
        this.tipstaffOrg = tipstaffOrg;
    }

    public String getTipstaffDepartment() {
        return tipstaffDepartment;
    }

    public void setTipstaffDepartment(String tipstaffDepartment) {
        this.tipstaffDepartment = tipstaffDepartment;
    }

    public String getTipstaffPosition() {
        return tipstaffPosition;
    }

    public void setTipstaffPosition(String tipstaffPosition) {
        this.tipstaffPosition = tipstaffPosition;
    }

    public String getTipstaffPersonalMailbox() {
        return tipstaffPersonalMailbox;
    }

    public void setTipstaffPersonalMailbox(String tipstaffPersonalMailbox) {
        this.tipstaffPersonalMailbox = tipstaffPersonalMailbox;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}