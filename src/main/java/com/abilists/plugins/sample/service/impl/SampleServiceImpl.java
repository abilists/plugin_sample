package com.abilists.plugins.sample.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abilists.core.service.AbilistsAbstractService;
import com.abilists.plugins.sample.bean.model.SampleModel;
import com.abilists.plugins.sample.bean.para.DltSamplePara;
import com.abilists.plugins.sample.bean.para.IstSamplePara;
import com.abilists.plugins.sample.bean.para.SltSamplePara;
import com.abilists.plugins.sample.bean.para.UdtSamplePara;
import com.abilists.plugins.sample.dao.MSampleDao;
import com.abilists.plugins.sample.dao.SSampleDao;
import com.abilists.plugins.sample.service.SampleService;

import base.bean.para.CommonPara;

@Service
public class SampleServiceImpl extends AbilistsAbstractService implements SampleService {

	final Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);

	@Autowired
	private SqlSession sAbilistsDao;
	@Autowired
    private Configuration configuration;

	@Override
	public List<SampleModel> sltSampleList(SltSamplePara sltSamplePara) throws Exception {
		List<SampleModel> SampleList = null;
	
		// Get now page
		int nowPage = sltSamplePara.getNowPage();
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", sltSamplePara.getUserId());
		map.put("nowPage", (nowPage - 1) * configuration.getInt("paging.row.ten"));
		map.put("row", configuration.getInt("paging.row.ten"));

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			SampleList = sAbilistsDao.getMapper(SSampleDao.class).sltSampleList(map);	
		} catch (Exception e) {
			logger.error("sltSampleList Exception error", e);
		}

		return SampleList;
	}

	public SampleModel sltSample(SltSamplePara sltSamplePara) throws Exception {
		SampleModel Sample = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usmNo", sltSamplePara.getUsmNo());
		map.put("userId", sltSamplePara.getUserId());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			Sample = sAbilistsDao.getMapper(SSampleDao.class).sltSample(map);
		} catch (Exception e) {
			logger.error("sltOptions Exception error", e);
		}
		return Sample;
	}

	@Override
	public int sltSampleSum(CommonPara commonPara) throws Exception {
		int sum = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", commonPara.getUserId());

		try {
			sum = sAbilistsDao.getMapper(SSampleDao.class).sltSampleSum(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return sum;
	}

	@Override
	public boolean istSample(IstSamplePara istSamplePara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usmSample", istSamplePara.getUsmSample());
		map.put("userId", istSamplePara.getUserId());

		try {
			intResult = mAbilistsDao.getMapper(MSampleDao.class).istSample(map);

		} catch (IndexOutOfBoundsException ie) {
			logger.error("IndexOutOfBoundsException error", ie);
			return false;
		} catch (Exception e) {
			logger.error("Exception error", e);
			return false;
		}

		if(intResult < 1) {
			logger.error("istSample error, userId={}", map.get("userId"));
			return false;
		}

		return true;	
	}

	@Transactional(rollbackFor = {IllegalArgumentException.class, Exception.class}, propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean udtSample(UdtSamplePara udtSamplePara) throws Exception {

		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usmNo", udtSamplePara.getUsmNo());
		map.put("usmSample", udtSamplePara.getUsmSample());
		map.put("userId", udtSamplePara.getUserId());

		try {
			intResult = mAbilistsDao.getMapper(MSampleDao.class).udtSample(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		if(intResult < 1) {
			logger.error("udtSample error, userId={}", udtSamplePara.getUserId());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean dltSample(DltSamplePara dltSamplePara) throws Exception {

		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usmNo", dltSamplePara.getUsmNo());
		map.put("userId", dltSamplePara.getUserId());

		try {
			intResult = mAbilistsDao.getMapper(MSampleDao.class).dltSample(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		if(intResult < 1) {
			logger.error("dltSample error, usmNo={}, userId={}", dltSamplePara.getUsmNo(), dltSamplePara.getUserId());
			return false;
		}

		return true;
	}

}