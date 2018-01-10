package com.sinosoft.wateradmin.app.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.wateradmin.common.BaseBean;
import com.sinosoft.wateradmin.common.CustomDateTimeDeserializer;
import com.sinosoft.wateradmin.common.CustomDateTimeSerializer;

import java.util.Date;

/**
 * 案件信息_pojo
 *
 * @author lvzhixue
 * @create 2017-11-02 15:06
 */
public class CaseInfo extends BaseBean {
    private Integer ciId;

    private Integer prId;//--巡检上报记录编号

    private String ciName;

    @JsonSerialize(using=CustomDateTimeSerializer.class)
    @JsonDeserialize(using= CustomDateTimeDeserializer.class)
    private Date ciDatetime;

    private String ciType;

    private String ciStatus;

    private String ciContent;

    private String ciPlace;

    private String ciAcceptPerson;//--受理人

    private String remark;

    private Integer userId;

    private String userName;

    private Integer ceaId;//--立案审批表编号


    //--申请理由
    private String applyReason;

    /****下面为：查询专用****/
    private String startTime;
    private String endTime;


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

    public Integer getPrId() {
        return prId;
    }

    public void setPrId(Integer prId) {
        this.prId = prId;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getCeaId() {
        return ceaId;
    }

    public void setCeaId(Integer ceaId) {
        this.ceaId = ceaId;
    }
}