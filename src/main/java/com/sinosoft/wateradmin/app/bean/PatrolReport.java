package com.sinosoft.wateradmin.app.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.wateradmin.common.BaseBean;
import com.sinosoft.wateradmin.common.CustomDateTimeDeserializer;
import com.sinosoft.wateradmin.common.CustomDateTimeSerializer;

import java.util.Date;

/**
 * 巡查上报记录表(app_patrol_report)对应bean
 *
 * @author lkj.
 * @create 2017-10-31 17:05
 */
public class PatrolReport extends BaseBean{
	//上报记录序号
	private int prId;
	//巡查台账序号
	private int plId;
	//现场描述
	private String prSiteDescription;
	//发生位置
	private String prPosition;
	//上报时间
	@JsonSerialize(using=CustomDateTimeSerializer.class)
	@JsonDeserialize(using= CustomDateTimeDeserializer.class)
	private Date prReportTime;
	//经度坐标
	private String longitude;
	//纬度坐标
	private String latitude;
	//备注
	private String remark;
	/****下面为：查询专用****/
	private String startTime;
	private String endTime;


	public int getPrId() {
		return prId;
	}

	public void setPrId(int prId) {
		this.prId = prId;
	}

	public int getPlId() {
		return plId;
	}

	public void setPlId(int plId) {
		this.plId = plId;
	}

	public String getPrSiteDescription() {
		return prSiteDescription;
	}

	public void setPrSiteDescription(String prSiteDescription) {
		this.prSiteDescription = prSiteDescription;
	}

	public String getPrPosition() {
		return prPosition;
	}

	public void setPrPosition(String prPosition) {
		this.prPosition = prPosition;
	}

	public Date getPrReportTime() {
		return prReportTime;
	}

	public void setPrReportTime(Date prReportTime) {
		this.prReportTime = prReportTime;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
