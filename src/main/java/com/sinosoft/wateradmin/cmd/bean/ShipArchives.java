package com.sinosoft.wateradmin.cmd.bean;

import com.sinosoft.wateradmin.common.BaseBean;

/**
 * 车船档案表(cmd_ship_archives)对应bean
 *
 * @author lkj.
 * @create 2017-10-31 17:05
 */
public class ShipArchives extends BaseBean{
	//车船档案序号
	private Integer saId;
	//车船号码
	private String saNum;
	//车台号码
	private String saCode;
	//类型
	private String saType;
	//颜色
	private String saColor;
	//发动机号
	private String saMotorNum;
	//底盘号码
	private String saChassisNum;
	//用途
	private String saUse;
	//车船名称
	private String saName;
	//车船主人
	private String saOwner;
	@Override
	public void setSort(String sort) {
		if(sort!=null&&sort.length()>0){
			switch (sort){
				case "saNum":
					super.sort="sa_num"; break;
				case "saCode":
					super.sort="sa_code"; break;
				default : break;
			}
		}
	}

	public String getSaName() {
		return saName;
	}

	public void setSaName(String saName) {
		this.saName = saName;
	}

	public String getSaOwner() {
		return saOwner;
	}

	public void setSaOwner(String saOwner) {
		this.saOwner = saOwner;
	}

	public Integer getSaId() {
		return saId;
	}
	public void setSaId(Integer saId) {
		this.saId = saId;
	}

	public String getSaNum() {
		return saNum;
	}

	public void setSaNum(String saNum) {
		this.saNum = saNum;
	}

	public String getSaCode() {
		return saCode;
	}

	public void setSaCode(String saCode) {
		this.saCode = saCode;
	}

	public String getSaType() {
		return saType;
	}

	public void setSaType(String saType) {
		this.saType = saType;
	}

	public String getSaColor() {
		return saColor;
	}

	public void setSaColor(String saColor) {
		this.saColor = saColor;
	}

	public String getSaMotorNum() {
		return saMotorNum;
	}

	public void setSaMotorNum(String saMotorNum) {
		this.saMotorNum = saMotorNum;
	}

	public String getSaChassisNum() {
		return saChassisNum;
	}

	public void setSaChassisNum(String saChassisNum) {
		this.saChassisNum = saChassisNum;
	}

	public String getSaUse() {
		return saUse;
	}

	public void setSaUse(String saUse) {
		this.saUse = saUse;
	}
}
