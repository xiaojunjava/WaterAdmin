package com.sinosoft.wateradmin.common;

import java.io.Serializable;

/**
 * VO基础类（序列化用）
 *
 * @author lkj.
 * @create 2017-11-06 11:43
 */
public class BaseBean implements Serializable {
	//排序属性
	protected String sort;
	//升降序
	protected String order;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
