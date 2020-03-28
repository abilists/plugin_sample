package com.abilists.plugins.sample.admin.service;

import java.util.List;

import com.abilists.core.service.PagingService;
import com.abilists.plugins.sample.bean.model.SampleModel;

import base.bean.para.CommonPara;

public interface AdminSampleService extends PagingService {

	public List<SampleModel> sltSampleList(CommonPara commonPara) throws Exception;
	public int sltSampleSum(CommonPara commonPara) throws Exception;

}