package com.abilists.plugins.sample.bean;

import java.util.List;

import com.abilists.plugins.sample.bean.model.SampleModel;

import base.bean.model.CommonModel;

public class PluginsModel extends CommonModel {

	private SampleModel Sample;
	private List<SampleModel> SampleList;

	public SampleModel getSample() {
		return Sample;
	}
	public void setSample(SampleModel Sample) {
		this.Sample = Sample;
	}
	public List<SampleModel> getSampleList() {
		return SampleList;
	}
	public void setSampleList(List<SampleModel> SampleList) {
		this.SampleList = SampleList;
	}

}
