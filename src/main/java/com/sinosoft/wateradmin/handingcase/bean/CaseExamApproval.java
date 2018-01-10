package com.sinosoft.wateradmin.handingcase.bean;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.wateradmin.common.CustomDateTimeDeserializer;
import com.sinosoft.wateradmin.common.CustomDateTimeSerializer;

import java.util.Date;

/**
 * 立案申请表_bean
 *
 * @author lvzhixue.
 * @create 2018/1/4 15:25
 */
public class CaseExamApproval {
    /**
     * 顺序号，主键
     */
    private int ceaId;

    /**
     * 处罚机关简称
     */
    private String ceaAbbreviation;

    /**
     *年份
     */
    private int ceaYear;

    /**
     * 序号
     */
    private int ceaSeqno;

    /**
     * 案件来源
     */
    private String ceaSource;

    /**
     * 案发地点
     */
    private String ceaPosition;

    /**
     * 案发时间
     */
    @JsonSerialize(using=CustomDateTimeSerializer.class)
    @JsonDeserialize(using= CustomDateTimeDeserializer.class)
    private Date ceaTimeofcase;

    /**
     * 姓名
     */
    private String ceaName;

    /**
     * 性别
     */
    private String ceaSex;

    /**
     * 电话
     */
    private String ceaTelephone;

    /**
     * 住所地
     */
    private String ceaAddress;

    /**
     * 邮编
     */
    private String ceaZipcode;

    /**
     * 单位名称
     */
    private String ceaCompanyName;

    /**
     * 单位法定代表人（负责人）
     */
    private String ceaCompanyCharge;

    /**
     * 单位职务
     */
    private String ceaCompanyJob;

    /**
     * 单位电话
     */
    private String ceaCompanyTelephone;

    /**
     * 单位住所地
     */
    private String ceaCompanyAddress;

    /**
     * 单位邮编
     */
    private String ceaCompanyZipcode;

    /**
     * 案情简介及立案依据
     */
    private String ceaCaseSummary;

    /**
     * 经办人
     */
    private String ceaCaseAgent;

    /**
     * 时间
     */
    @JsonSerialize(using=CustomDateTimeSerializer.class)
    @JsonDeserialize(using= CustomDateTimeDeserializer.class)
    private Date ceaCaseDatetime;

    /**
     * 执法机构负责人审核意见
     */
    private String ceaCompanyAuditopinion;

    /**
     * 签名
     */
    private String ceaCompanyAutograph;

    /**
     * 时间
     */
    @JsonSerialize(using=CustomDateTimeSerializer.class)
    @JsonDeserialize(using= CustomDateTimeDeserializer.class)
    private Date ceaCompanyDatetime;

    /**
     * 执法机关负责人审批意见
     */
    private String ceaOfficeAuditopinion;

    /**
     * 签名
     */
    private String ceaOfficeAutograph;

    /**
     * 时间
     */
    @JsonSerialize(using=CustomDateTimeSerializer.class)
    @JsonDeserialize(using= CustomDateTimeDeserializer.class)
    private Date ceaOfficeDatetime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 立案审批表状态
     */
    private String status;

    /**
     * 巡检上报记录，即：案件来源
     */
    private int prid;

    public int getCeaId() {
        return ceaId;
    }

    public void setCeaId(int ceaId) {
        this.ceaId = ceaId;
    }

    public String getCeaAbbreviation() {
        return ceaAbbreviation;
    }

    public void setCeaAbbreviation(String ceaAbbreviation) {
        this.ceaAbbreviation = ceaAbbreviation;
    }

    public int getCeaYear() {
        return ceaYear;
    }

    public void setCeaYear(int ceaYear) {
        this.ceaYear = ceaYear;
    }

    public int getCeaSeqno() {
        return ceaSeqno;
    }

    public void setCeaSeqno(int ceaSeqno) {
        this.ceaSeqno = ceaSeqno;
    }

    public String getCeaSource() {
        return ceaSource;
    }

    public void setCeaSource(String ceaSource) {
        this.ceaSource = ceaSource;
    }

    public String getCeaPosition() {
        return ceaPosition;
    }

    public void setCeaPosition(String ceaPosition) {
        this.ceaPosition = ceaPosition;
    }

    public Date getCeaTimeofcase() {
        return ceaTimeofcase;
    }

    public void setCeaTimeofcase(Date ceaTimeofcase) {
        this.ceaTimeofcase = ceaTimeofcase;
    }

    public String getCeaName() {
        return ceaName;
    }

    public void setCeaName(String ceaName) {
        this.ceaName = ceaName;
    }

    public String getCeaSex() {
        return ceaSex;
    }

    public void setCeaSex(String ceaSex) {
        this.ceaSex = ceaSex;
    }

    public String getCeaTelephone() {
        return ceaTelephone;
    }

    public void setCeaTelephone(String ceaTelephone) {
        this.ceaTelephone = ceaTelephone;
    }

    public String getCeaAddress() {
        return ceaAddress;
    }

    public void setCeaAddress(String ceaAddress) {
        this.ceaAddress = ceaAddress;
    }

    public String getCeaZipcode() {
        return ceaZipcode;
    }

    public void setCeaZipcode(String ceaZipcode) {
        this.ceaZipcode = ceaZipcode;
    }

    public String getCeaCompanyName() {
        return ceaCompanyName;
    }

    public void setCeaCompanyName(String ceaCompanyName) {
        this.ceaCompanyName = ceaCompanyName;
    }

    public String getCeaCompanyCharge() {
        return ceaCompanyCharge;
    }

    public void setCeaCompanyCharge(String ceaCompanyCharge) {
        this.ceaCompanyCharge = ceaCompanyCharge;
    }

    public String getCeaCompanyJob() {
        return ceaCompanyJob;
    }

    public void setCeaCompanyJob(String ceaCompanyJob) {
        this.ceaCompanyJob = ceaCompanyJob;
    }

    public String getCeaCompanyTelephone() {
        return ceaCompanyTelephone;
    }

    public void setCeaCompanyTelephone(String ceaCompanyTelephone) {
        this.ceaCompanyTelephone = ceaCompanyTelephone;
    }

    public String getCeaCompanyAddress() {
        return ceaCompanyAddress;
    }

    public void setCeaCompanyAddress(String ceaCompanyAddress) {
        this.ceaCompanyAddress = ceaCompanyAddress;
    }

    public String getCeaCompanyZipcode() {
        return ceaCompanyZipcode;
    }

    public void setCeaCompanyZipcode(String ceaCompanyZipcode) {
        this.ceaCompanyZipcode = ceaCompanyZipcode;
    }

    public String getCeaCaseSummary() {
        return ceaCaseSummary;
    }

    public void setCeaCaseSummary(String ceaCaseSummary) {
        this.ceaCaseSummary = ceaCaseSummary;
    }

    public String getCeaCaseAgent() {
        return ceaCaseAgent;
    }

    public void setCeaCaseAgent(String ceaCaseAgent) {
        this.ceaCaseAgent = ceaCaseAgent;
    }

    public Date getCeaCaseDatetime() {
        return ceaCaseDatetime;
    }

    public void setCeaCaseDatetime(Date ceaCaseDatetime) {
        this.ceaCaseDatetime = ceaCaseDatetime;
    }

    public String getCeaCompanyAuditopinion() {
        return ceaCompanyAuditopinion;
    }

    public void setCeaCompanyAuditopinion(String ceaCompanyAuditopinion) {
        this.ceaCompanyAuditopinion = ceaCompanyAuditopinion;
    }

    public String getCeaCompanyAutograph() {
        return ceaCompanyAutograph;
    }

    public void setCeaCompanyAutograph(String ceaCompanyAutograph) {
        this.ceaCompanyAutograph = ceaCompanyAutograph;
    }

    public Date getCeaCompanyDatetime() {
        return ceaCompanyDatetime;
    }

    public void setCeaCompanyDatetime(Date ceaCompanyDatetime) {
        this.ceaCompanyDatetime = ceaCompanyDatetime;
    }

    public String getCeaOfficeAuditopinion() {
        return ceaOfficeAuditopinion;
    }

    public void setCeaOfficeAuditopinion(String ceaOfficeAuditopinion) {
        this.ceaOfficeAuditopinion = ceaOfficeAuditopinion;
    }

    public String getCeaOfficeAutograph() {
        return ceaOfficeAutograph;
    }

    public void setCeaOfficeAutograph(String ceaOfficeAutograph) {
        this.ceaOfficeAutograph = ceaOfficeAutograph;
    }

    public Date getCeaOfficeDatetime() {
        return ceaOfficeDatetime;
    }

    public void setCeaOfficeDatetime(Date ceaOfficeDatetime) {
        this.ceaOfficeDatetime = ceaOfficeDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrid() {
        return prid;
    }

    public void setPrid(int prid) {
        this.prid = prid;
    }
}