package com.abilists.plugins.sample.service;

import java.util.List;

import com.abilists.core.service.PagingService;
import com.abilists.plugins.sample.bean.model.SampleModel;
import com.abilists.plugins.sample.bean.para.DltSamplePara;
import com.abilists.plugins.sample.bean.para.IstSamplePara;
import com.abilists.plugins.sample.bean.para.SltSamplePara;
import com.abilists.plugins.sample.bean.para.UdtSamplePara;

import base.bean.para.CommonPara;

public interface SampleService extends PagingService {

	public SampleModel sltSample(SltSamplePara sltSamplePara) throws Exception;
	public List<SampleModel> sltSampleList(SltSamplePara sltSamplePara) throws Exception;
	public int sltSampleSum(CommonPara commonPara) throws Exception;

	public boolean istSample(IstSamplePara istSamplePara) throws Exception;
	public boolean udtSample(UdtSamplePara udtSamplePara) throws Exception;
	public boolean dltSample(DltSamplePara dltSamplePara) throws Exception;

}
