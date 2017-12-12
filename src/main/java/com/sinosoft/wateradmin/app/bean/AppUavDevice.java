package com.sinosoft.wateradmin.app.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.wateradmin.common.BaseBean;
import com.sinosoft.wateradmin.common.CustomDateDeserializer;
import com.sinosoft.wateradmin.common.CustomDateSerializer;

import java.util.Date;

/**
 * 无人机设备_bean
 *
 * @author lvzhixue.
 * @create 2017/11/24 16:32
 */
public class AppUavDevice extends BaseBean {
    private Integer uavId;

    private String uavCode;

    private String uavModel;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date purchaseDate;

    private String status;

    public Integer getUavId() {
        return uavId;
    }

    public void setUavId(Integer uavId) {
        this.uavId = uavId;
    }

    public String getUavCode() {
        return uavCode;
    }

    public void setUavCode(String uavCode) {
        this.uavCode = uavCode;
    }

    public String getUavModel() {
        return uavModel;
    }

    public void setUavModel(String uavModel) {
        this.uavModel = uavModel;
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
}