package com.sinosoft.wateradmin.common;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分页参数类
 *
 * @author lkj.
 * @create 2017-11-13 14:43
 */
public class BasePage<T> extends PageInfo {
	//eaayui分页修改化参数
	private List<T> rows;
	//total已有
	/****构造函数处理 start ***/
	public BasePage(List<T> list){
		super(list);
	}
	public BasePage(List<T> list, int navigatePages){
		super(list,navigatePages);
	}
	/****构造函数处理 end ***/
	//给集合赋父类的值
	public List<T> getRows() {
		return this.getList();
	}

}
