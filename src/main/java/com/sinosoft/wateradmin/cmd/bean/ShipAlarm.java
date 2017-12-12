package com.sinosoft.wateradmin.cmd.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.wateradmin.common.BaseBean;
import com.sinosoft.wateradmin.common.CustomDateTimeDeserializer;
import com.sinosoft.wateradmin.common.CustomDateTimeSerializer;

import java.util.Date;

/**
 * 车船报警表(cmd_ship_alarm)对应bean
 *
 * @author lkj.
 * @create 2017-11-29 14:22
 */
public class ShipAlarm extends BaseBean {
	//车船报警序号
	private Integer csaId;
	//车船档案序号
	private Integer saId;
	//报警开始时间
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private Date csaBegintime;
	//报警结束时间
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private Date csaEndtime;
	//报警原因
	private String csaAlarmReason;
	//报警类型
	private String csaType;
	//报警经度坐标
	private String csaLongitude;
	//报经纬度坐标
	private String csaLatitude;
	//状态
	private String csaStatus;
	//关联的车船对象
	private ShipArchives shipArchives;

	//****以下为“查询专用”****/
	private String startTime;//开始时间
	private String endTime;//结束时间

	public Integer getCsaId() {
		return csaId;
	}

	public void setCsaId(Integer csaId) {
		this.csaId = csaId;
	}

	public Integer getSaId() {
		return saId;
	}

	public void setSaId(Integer saId) {
		this.saId = saId;
	}

	public Date getCsaBegintime() {
		return csaBegintime;
	}

	public void setCsaBegintime(Date csaBegintime) {
		this.csaBegintime = csaBegintime;
	}

	public Date getCsaEndtime() {
		return csaEndtime;
	}

	public void setCsaEndtime(Date csaEndtime) {
		this.csaEndtime = csaEndtime;
	}

	public String getCsaAlarmReason() {
		return csaAlarmReason;
	}

	public void setCsaAlarmReason(String csaAlarmReason) {
		this.csaAlarmReason = csaAlarmReason;
	}

	public String getCsaType() {
		return csaType;
	}

	public void setCsaType(String csaType) {
		this.csaType = csaType;
	}

	public String getCsaLongitude() {
		return csaLongitude;
	}

	public void setCsaLongitude(String csaLongitude) {
		this.csaLongitude = csaLongitude;
	}

	public String getCsaLatitude() {
		return csaLatitude;
	}

	public void setCsaLatitude(String csaLatitude) {
		this.csaLatitude = csaLatitude;
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

	public ShipArchives getShipArchives() {
		return shipArchives;
	}

	public void setShipArchives(ShipArchives shipArchives) {
		this.shipArchives = shipArchives;
	}

	public String getCsaStatus() {
		return csaStatus;
	}

	public void setCsaStatus(String csaStatus) {
		this.csaStatus = csaStatus;
	}
}
