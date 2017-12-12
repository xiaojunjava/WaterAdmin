package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.AppPatrolLedger;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 巡查台账  Service
 *
 * @author hanjie.
 * @create 2017/11/3
 */
public interface IAppPatrolLedgerSerivice {

    /**
     * 根据主键删除一条记录
     * @param plId
     * @return
     * @throws Exception
     */
    int deleteByPrimaryKey(int plId) throws Exception;

    /**
     * 新增一条记录
     * @param appPatrolLedger
     * @return
     * @throws Exception
     */
    int insert(AppPatrolLedger appPatrolLedger) throws Exception;

    /**
     * 根据主键查询一条记录
     * @param plId
     * @return
     * @throws Exception
     */
    AppPatrolLedger selectByPrimaryKey(@Param(value = "plId") int plId) throws Exception;

	/**
	 * 根据条件查询
	 * @param appPatrolLedger
	 * @return
	 * @throws Exception
	 */
	List<AppPatrolLedger> selectDatas(AppPatrolLedger appPatrolLedger) throws Exception;
    /**
     * 更新一条记录
     * @param appPatrolLedger
     * @return
     * @throws Exception
     */
    int updateByPrimaryKey(AppPatrolLedger appPatrolLedger) throws Exception;

    /**
     * 根据userId查询记录
     * @param userId
     * @return
     * @throws Exception
     */
    List<AppPatrolLedger> selectByUserId(@Param(value = "userId") int userId) throws Exception;

    /**
     * 新增一条记录
     * @param appPatrolLedger
     * @return
     */
    int insertSelective(AppPatrolLedger appPatrolLedger);

    /**
     * 获取正在巡检的台账记录  added by lvzhixue 2017-11-13
     * @return
     */
    List<AppPatrolLedger> getPatrolNow();
}
