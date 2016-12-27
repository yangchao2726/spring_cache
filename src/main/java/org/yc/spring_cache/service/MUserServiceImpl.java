package org.yc.spring_cache.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yc.spring_cache.dao.MUserMapper;
import org.yc.spring_cache.model.MUser;

@Transactional
@Service("muserService")
public class MUserServiceImpl implements MUserServiceI{

	@Resource(name="muserMapper")
	private MUserMapper muserMapper;

	@Override
	public List<MUser> getAll() {
		
		return muserMapper.getAll();
	}

	@Override
	public int insert(MUser muser) throws Exception {
		muserMapper.insert(muser);
		if(1==1) throw new Exception("���������ȷ��");
		return 0;
	}

	@Override
	public int update(MUser muser) {
		
		return muserMapper.updateByPrimaryKey(muser);
	}

	@Override
	public int delete(Integer id) {
	
		return muserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public MUser selectByPrimaryKey(Integer id) {
		
		return muserMapper.selectByPrimaryKey(id);
	}

}