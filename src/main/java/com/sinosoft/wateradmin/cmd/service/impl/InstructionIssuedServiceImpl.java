package com.sinosoft.wateradmin.cmd.service.impl;

import com.sinosoft.wateradmin.cmd.bean.InstructionIssued;
import com.sinosoft.wateradmin.cmd.dao.IInstructionIssuedDAO;
import com.sinosoft.wateradmin.cmd.service.IInstructionIssuedService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *  下发指令  Service实现类
 * @author lkj
 *
 */
@Service
@Transactional
public class InstructionIssuedServiceImpl implements IInstructionIssuedService {
	@Resource
	private IInstructionIssuedDAO instructionIssuedDAO;
	@Resource
	private RedisTemplate<String, Map<String, String>> redisTemplate;
	@Override
	public InstructionIssued selectByPrimaryKey(int iiId) throws Exception{
		return instructionIssuedDAO.selectByPrimaryKey(iiId);
	}
	@Override
	public List<InstructionIssued> selectDatas(InstructionIssued ii) throws Exception{
		return instructionIssuedDAO.selectDatas(ii);
	}
	@Override
	public int deleteByPrimaryKey(int iiId) throws Exception{
		return instructionIssuedDAO.deleteByPrimaryKey(iiId);
	}
	@Override
	public int insert(InstructionIssued ii) throws Exception{
		return instructionIssuedDAO.insert(ii);
	}
	@Override
	public int update(InstructionIssued ii) throws Exception{
		return instructionIssuedDAO.update(ii);
	}
}
