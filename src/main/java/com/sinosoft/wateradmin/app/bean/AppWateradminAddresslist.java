package com.sinosoft.wateradmin.app.bean;

import com.sinosoft.wateradmin.common.BaseBean;

/**
 * 水政单位通讯录
 * added by lvzhixue 2017-11-06 11:50
 */
public class AppWateradminAddresslist  extends BaseBean {
    private Integer unitId;

    private String unitName;

    private String unitAddress;

    private String unitContactName;

    private String unitContactTel;

    private String unitTel;

    private String unitFax;

    private String remark;

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getUnitContactName() {
        return unitContactName;
    }

    public void setUnitContactName(String unitContactName) {
        this.unitContactName = unitContactName;
    }

    public String getUnitContactTel() {
        return unitContactTel;
    }

    public void setUnitContactTel(String unitContactTel) {
        this.unitContactTel = unitContactTel;
    }

    public String getUnitTel() {
        return unitTel;
    }

    public void setUnitTel(String unitTel) {
        this.unitTel = unitTel;
    }

    public String getUnitFax() {
        return unitFax;
    }

    public void setUnitFax(String unitFax) {
        this.unitFax = unitFax;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}