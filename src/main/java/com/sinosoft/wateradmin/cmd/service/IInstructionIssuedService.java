package com.sinosoft.wateradmin.cmd.service;

import com.sinosoft.wateradmin.cmd.bean.InstructionIssued;

import java.util.List;

/**
 * 下发指令  Service接口
 * @author lkj
 */
public interface IInstructionIssuedService {

	/**
	 * 根据主键查数据
	 * @param iiId
	 * @return
	 */
	public InstructionIssued selectByPrimaryKey(int iiId) throws Exception;

	/**
	 * 根据条件查数据
	 * @param ii
	 * @return
	 * @throws Exception
	 */
	public List<InstructionIssued> selectDatas(InstructionIssued ii) throws Exception;

	/**
	 * 根据主键删除1条数据
	 * @param iiId
	 * @return
	 */
	public int deleteByPrimaryKey(int iiId) throws Exception;

	/**
	 *新增记录
	 * @param ii
	 * @return
	 */
	public int insert(InstructionIssued ii) throws Exception;

	/**
	 * 更新记录
	 * @param ii
	 * @return
	 */
	public int update(InstructionIssued ii) throws Exception;

}
