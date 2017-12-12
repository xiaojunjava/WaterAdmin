package com.sinosoft.wateradmin.app.bean;

import java.util.Date;

/**
 * 案件信息_pojo
 *
 * @author lvzhixue
 * @create 2017-11-02 15:06
 */
public class CaseInfo {
    private Integer ciId;

    private String ciName;

    private Date ciDatetime;

    private String ciType;

    private String ciStatus;

    private String ciContent;

    private String ciPlace;

    private String ciAcceptPerson;//--受理人

    private String remark;

    public Integer getCiId() {
        return ciId;
    }

    public void setCiId(Integer ciId) {
        this.ciId = ciId;
    }

    public String getCiName() {
        return ciName;
    }

    public void setCiName(String ciName) {
        this.ciName = ciName;
    }

    public Date getCiDatetime() {
        return ciDatetime;
    }

    public void setCiDatetime(Date ciDatetime) {
        this.ciDatetime = ciDatetime;
    }

    public String getCiType() {
        return ciType;
    }

    public void setCiType(String ciType) {
        this.ciType = ciType;
    }

    public String getCiStatus() {
        return ciStatus;
    }

    public void setCiStatus(String ciStatus) {
        this.ciStatus = ciStatus;
    }

    public String getCiContent() {
        return ciContent;
    }

    public void setCiContent(String ciContent) {
        this.ciContent = ciContent;
    }

    public String getCiPlace() {
        return ciPlace;
    }

    public void setCiPlace(String ciPlace) {
        this.ciPlace = ciPlace;
    }

    public String getCiAcceptPerson() {
        return ciAcceptPerson;
    }

    public void setCiAcceptPerson(String ciAcceptPerson) {
        this.ciAcceptPerson = ciAcceptPerson;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}