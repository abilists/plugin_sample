package com.abilists.plugins.sample.bean.model;

import java.io.Serializable;
import java.util.Date;

import base.bean.model.BasicModel;

public class SampleModel extends BasicModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private long usmNo;
	private String usmSample;
  	private String usmStatus;
	private String userId;
	private Date insertTime;
	private Date updateTime;

	public long getUsmNo() {
		return usmNo;
	}
	public void setUsmNo(long usmNo) {
		this.usmNo = usmNo;
	}
	public String getUsmSample() {
		return usmSample;
	}
	public void setUsmSample(String usmSample) {
		this.usmSample = usmSample;
	}
	public String getUsmStatus() {
		return usmStatus;
	}
	public void setUsmStatus(String usmStatus) {
		this.usmStatus = usmStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}