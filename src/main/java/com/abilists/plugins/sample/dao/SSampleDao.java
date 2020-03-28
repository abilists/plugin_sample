package com.abilists.plugins.sample.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.abilists.plugins.sample.bean.model.SampleModel;

@Repository
public interface SSampleDao {

	public List<SampleModel> sltSampleList(Map<String, Object> map) throws SQLException;
	public SampleModel sltSample(Map<String, Object> map) throws SQLException;
	public int sltSampleSum(Map<String, Object> map) throws SQLException;

}