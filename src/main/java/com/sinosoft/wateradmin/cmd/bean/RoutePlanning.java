package com.sinosoft.wateradmin.cmd.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.wateradmin.common.BaseBean;
import com.sinosoft.wateradmin.common.CustomDateTimeDeserializer;
import com.sinosoft.wateradmin.common.CustomDateTimeSerializer;

import java.util.Date;

/**
 * 路线规划记录表(cmd_route_planning)对应bean
 *
 * @author lkj.
 * @create 2017-10-31 17:05
 */
public class RoutePlanning extends BaseBean{
	//路线序号
	private int rpId;
	//类型 1-航道、2-区域边界
	private int rpType;
	//归属  1-采沙航道、2-采沙区域、3-执法航道、4-执法区域
	private int rpBelong;
	//路线名称
	private String rpLineName;
	//路线点集合
	private String rpPoints;//clob字段
	//线路有效状态
	private String rpStatus;
	//制定人
	private String rpMakePerson;
	//线路制定时间
	@JsonSerialize(using=CustomDateTimeSerializer.class)
	@JsonDeserialize(using= CustomDateTimeDeserializer.class)
	private Date rpMakeTime;
	//备注
	private String remark;

	//****以下为“查询专用”****/
	private String startTime;//开始时间
	private String endTime;//结束时间

	public int getRpId() {
		return rpId;
	}

	public void setRpId(int rpId) {
		this.rpId = rpId;
	}

	public int getRpType() {
		return rpType;
	}

	public void setRpType(int rpType) {
		this.rpType = rpType;
	}

	public int getRpBelong() {
		return rpBelong;
	}

	public void setRpBelong(int rpBelong) {
		this.rpBelong = rpBelong;
	}

	public String getRpLineName() {
		return rpLineName;
	}

	public void setRpLineName(String rpLineName) {
		this.rpLineName = rpLineName;
	}

	public String getRpPoints() {
		return rpPoints;
	}

	public void setRpPoints(String rpPoints) {
		this.rpPoints = rpPoints;
	}

	public String getRpStatus() {
		return rpStatus;
	}

	public void setRpStatus(String rpStatus) {
		this.rpStatus = rpStatus;
	}

	public String getRpMakePerson() {
		return rpMakePerson;
	}

	public void setRpMakePerson(String rpMakePerson) {
		this.rpMakePerson = rpMakePerson;
	}

	public Date getRpMakeTime() {
		return rpMakeTime;
	}

	public void setRpMakeTime(Date rpMakeTime) {
		this.rpMakeTime = rpMakeTime;
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
