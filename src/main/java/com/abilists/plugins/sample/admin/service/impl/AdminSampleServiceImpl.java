package com.abilists.plugins.sample.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abilists.core.service.AbilistsAbstractService;
import com.abilists.plugins.sample.admin.service.AdminSampleService;
import com.abilists.plugins.sample.bean.model.SampleModel;
import com.abilists.plugins.sample.dao.SSampleDao;

import base.bean.para.CommonPara;

@Service
public class AdminSampleServiceImpl extends AbilistsAbstractService implements AdminSampleService {

	final Logger logger = LoggerFactory.getLogger(AdminSampleServiceImpl.class);

	@Autowired
	private SqlSession sAbilistsDao;
	@Autowired
    private Configuration configuration;

	@Override
	public List<SampleModel> sltSampleList(CommonPara commonPara) throws Exception {
		List<SampleModel> sampleList = null;

		// Get now page
		int nowPage = commonPara.getNowPage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nowPage", (nowPage - 1) * configuration.getInt("paging.row.ten"));
		map.put("row", configuration.getInt("paging.row.ten"));

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			sampleList = sAbilistsDao.getMapper(SSampleDao.class).sltSampleList(map);

		} catch (Exception e) {
			logger.error("sltOptions Exception error", e);
		}

		return sampleList;
	}

	@Override
	public int sltSampleSum(CommonPara commonPara) throws Exception {
		int sum = 0;

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			sum = sAbilistsDao.getMapper(SSampleDao.class).sltSampleSum(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return sum;
	}

}
