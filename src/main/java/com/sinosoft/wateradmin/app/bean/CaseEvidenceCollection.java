package com.sinosoft.wateradmin.app.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.wateradmin.common.BaseBean;
import com.sinosoft.wateradmin.common.CustomDateTimeDeserializer;
import com.sinosoft.wateradmin.common.CustomDateTimeSerializer;

import java.util.Date;

/**
 * 案件调查取证记录_pojo
 *
 * @author lvzhixue
 * @create 2017-11-02 15:06
 */
public class CaseEvidenceCollection  extends BaseBean {
    private Integer ecId;

    private Integer ciId;

    private String ecName;
    @JsonSerialize(using=CustomDateTimeSerializer.class)
    @JsonDeserialize(using= CustomDateTimeDeserializer.class)
    private Date ecDatetime;

    private String ecRecPerson;

    private String ecType;

    private String remark;

    private String ecPlace;

    private String ecContent;

    /****下面为：查询专用****/
    private String startTime;
    private String endTime;

    public Integer getEcId() {
        return ecId;
    }

    public void setEcId(Integer ecId) {
        this.ecId = ecId;
    }

    public Integer getCiId() {
        return ciId;
    }

    public void setCiId(Integer ciId) {
        this.ciId = ciId;
    }

    public String getEcName() {
        return ecName;
    }

    public void setEcName(String ecName) {
        this.ecName = ecName;
    }

    public Date getEcDatetime() {
        return ecDatetime;
    }

    public void setEcDatetime(Date ecDatetime) {
        this.ecDatetime = ecDatetime;
    }

    public String getEcRecPerson() {
        return ecRecPerson;
    }

    public void setEcRecPerson(String ecRecPerson) {
        this.ecRecPerson = ecRecPerson;
    }

    public String getEcType() {
        return ecType;
    }

    public void setEcType(String ecType) {
        this.ecType = ecType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEcPlace() {
        return ecPlace;
    }

    public void setEcPlace(String ecPlace) {
        this.ecPlace = ecPlace;
    }

    public String getEcContent() {
        return ecContent;
    }

    public void setEcContent(String ecContent) {
        this.ecContent = ecContent;
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
}