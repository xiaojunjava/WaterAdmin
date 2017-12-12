package com.sinosoft.wateradmin.app.bean;

import java.util.Date;

/**
 * 案件调查取证记录_附件_pojo
 *
 * @author lvzhixue
 * @create 2017-11-02 15:06
 */

public class CaseAttachments {
    private Integer ecaId;

    private Integer ecId;

    private String name;

    private String ecaPosition;

    private Date ecaDatetime;

    private String remark;

    public Integer getEcaId() {
        return ecaId;
    }

    public void setEcaId(Integer ecaId) {
        this.ecaId = ecaId;
    }

    public Integer getEcId() {
        return ecId;
    }

    public void setEcId(Integer ecId) {
        this.ecId = ecId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEcaPosition() {
        return ecaPosition;
    }

    public void setEcaPosition(String ecaPosition) {
        this.ecaPosition = ecaPosition;
    }

    public Date getEcaDatetime() {
        return ecaDatetime;
    }

    public void setEcaDatetime(Date ecaDatetime) {
        this.ecaDatetime = ecaDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}