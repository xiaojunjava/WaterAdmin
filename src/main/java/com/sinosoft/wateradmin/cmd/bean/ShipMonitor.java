package com.sinosoft.wateradmin.cmd.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.wateradmin.common.BaseBean;
import com.sinosoft.wateradmin.common.CustomDateTimeDeserializer;
import com.sinosoft.wateradmin.common.CustomDateTimeSerializer;

import java.util.Date;

/**
 * 车船实时位置监控记录表(cmd_ship_monitor)对应bean
 *
 * @author lkj.
 * @create 2017-11-15 14:22
 */
public class ShipMonitor extends BaseBean {
	//监控记录序号
	private Integer csmId;
	//车船档案序号
	private Integer saId;
	//轨迹开始时间
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private Date smBeginTime;
	//轨迹结束时间
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private Date smEndTime;
	//轨迹（点集合）
	private String smTraJectory;//clob字段
	//备注
	private String remark;

	private  ShipArchives shipArchives;
	//****以下为“查询专用”****/
	private String startTime;//开始时间
	private String endTime;//结束时间

	public ShipArchives getShipArchives() {
		return shipArchives;
	}

	public void setShipArchives(ShipArchives shipArchives) {
		this.shipArchives = shipArchives;
	}

	public Integer getCsmId() {
		return csmId;
	}

	public void setCsmId(Integer csmId) {
		this.csmId = csmId;
	}

	public Integer getSaId() {
		return saId;
	}

	public void setSaId(Integer saId) {
		this.saId = saId;
	}

	public Date getSmBeginTime() {
		return smBeginTime;
	}

	public void setSmBeginTime(Date smBeginTime) {
		this.smBeginTime = smBeginTime;
	}

	public Date getSmEndTime() {
		return smEndTime;
	}

	public void setSmEndTime(Date smEndTime) {
		this.smEndTime = smEndTime;
	}

	public String getSmTraJectory() {
		return smTraJectory;
	}

	public void setSmTraJectory(String smTraJectory) {
		this.smTraJectory = smTraJectory;
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
