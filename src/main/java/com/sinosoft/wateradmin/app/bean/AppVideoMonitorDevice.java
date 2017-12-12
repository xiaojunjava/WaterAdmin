package com.sinosoft.wateradmin.app.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.wateradmin.common.BaseBean;
import com.sinosoft.wateradmin.common.CustomDateDeserializer;
import com.sinosoft.wateradmin.common.CustomDateSerializer;

import java.util.Date;

/**
 * 视频监控设备_bean
 *
 * @author lvzhixue.
 * @create 2017/11/24 16:32
 */
public class AppVideoMonitorDevice  extends BaseBean {
    private Integer vmId;

    private String vmCode;

    private String vmModel;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date purchaseDate;

    private String status;

    private String vmPosition;

    private String longitude;

    private String latitude;

    private String vmType;

    public Integer getVmId() {
        return vmId;
    }

    public void setVmId(Integer vmId) {
        this.vmId = vmId;
    }

    public String getVmCode() {
        return vmCode;
    }

    public void setVmCode(String vmCode) {
        this.vmCode = vmCode;
    }

    public String getVmModel() {
        return vmModel;
    }

    public void setVmModel(String vmModel) {
        this.vmModel = vmModel;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVmPosition() {
        return vmPosition;
    }

    public void setVmPosition(String vmPosition) {
        this.vmPosition = vmPosition;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getVmType() {
        return vmType;
    }

    public void setVmType(String vmType) {
        this.vmType = vmType;
    }
}