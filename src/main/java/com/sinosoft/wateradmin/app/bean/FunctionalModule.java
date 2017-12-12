package com.sinosoft.wateradmin.app.bean;

import java.util.List;

/**
 * 功能模块_bean
 * added by lvzhixue 2017-11-08
 */
public class FunctionalModule {
    private Integer fmId;

    private String fmName;

    private String fmCode;

    private Integer parFmId;

    private String fmAccessAddress;

    private String remark;

    private Integer seqNo;

    private Integer fmDepth;

    private Integer fmType;

    private List<Role> roleList;//--角色列表

    private List<FunctionalModule> childNodeList;


    public Integer getFmId() {
        return fmId;
    }

    public void setFmId(Integer fmId) {
        this.fmId = fmId;
    }

    public String getFmName() {
        return fmName;
    }

    public void setFmName(String fmName) {
        this.fmName = fmName;
    }

    public String getFmCode() {
        return fmCode;
    }

    public void setFmCode(String fmCode) {
        this.fmCode = fmCode;
    }

    public Integer getParFmId() {
        return parFmId;
    }

    public void setParFmId(Integer parFmId) {
        this.parFmId = parFmId;
    }

    public String getFmAccessAddress() {
        return fmAccessAddress;
    }

    public void setFmAccessAddress(String fmAccessAddress) {
        this.fmAccessAddress = fmAccessAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Integer getFmDepth() {
        return fmDepth;
    }

    public void setFmDepth(Integer fmDepth) {
        this.fmDepth = fmDepth;
    }

    public Integer getFmType() {
        return fmType;
    }

    public void setFmType(Integer fmType) {
        this.fmType = fmType;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<FunctionalModule> getChildNodeList() {
        return childNodeList;
    }

    public void setChildNodeList(List<FunctionalModule> childNodeList) {
        this.childNodeList = childNodeList;
    }
}