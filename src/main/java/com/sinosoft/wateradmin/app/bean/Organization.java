package com.sinosoft.wateradmin.app.bean;

/**
 * 组织机构_bean
 * added by lvzhixue 2017-11-08
 */
public class Organization {

    private Integer orgId;

    private String orgCode;

    private String orgName;

    private Integer parOrgId;

    private String orgDescription;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getParOrgId() {
        return parOrgId;
    }

    public void setParOrgId(Integer parOrgId) {
        this.parOrgId = parOrgId;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }
}