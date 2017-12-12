package com.sinosoft.wateradmin.app.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.wateradmin.common.BaseBean;
import com.sinosoft.wateradmin.common.CustomDateDeserializer;
import com.sinosoft.wateradmin.common.CustomDateSerializer;

import java.util.Date;

/**
 * 执法记录仪_bean
 *
 * @author lvzhixue.
 * @create 2017/11/24 16:32
 */

public class AppLawenforceRecorderDevice  extends BaseBean {
    private Integer alrId;

    private String alrCode;

    private String alrModel;
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date purchaseDate;

    private String status;

    public Integer getAlrId() {
        return alrId;
    }

    public void setAlrId(Integer alrId) {
        this.alrId = alrId;
    }

    public String getAlrCode() {
        return alrCode;
    }

    public void setAlrCode(String alrCode) {
        this.alrCode = alrCode;
    }

    public String getAlrModel() {
        return alrModel;
    }

    public void setAlrModel(String alrModel) {
        this.alrModel = alrModel;
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