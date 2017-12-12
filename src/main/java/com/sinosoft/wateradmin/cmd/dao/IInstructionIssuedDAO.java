package com.sinosoft.wateradmin.cmd.dao;

import com.sinosoft.wateradmin.cmd.bean.InstructionIssued;
import com.sinosoft.wateradmin.cmd.bean.ShipMonitor;
import com.sinosoft.wateradmin.common.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 下发指令  DAO层
 * @author lkj
 */
@MyBatisDao
public interface IInstructionIssuedDAO {

	/**
	 * 根据主键查数据
	 * @param iiId
	 * @return
	 */
	InstructionIssued selectByPrimaryKey(@Param(value = "iiId") int iiId);

	/**
	 * 根据条件查询数据
	 * @param ii
	 * @return
	 */
	List<InstructionIssued> selectDatas(InstructionIssued ii);

	/**
	 * 根据主键删除1条数据
	 * @param iiId
	 * @return
	 */
	int deleteByPrimaryKey(@Param(value = "iiId") int iiId);

	/**
	 *新增记录
	 * @param ii
	 * @return
	 */
	int insert(InstructionIssued ii);

	/**
	 * 更新记录
	 * @param ii
	 * @return
	 */
	int update(InstructionIssued ii);

}
