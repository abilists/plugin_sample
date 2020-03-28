package com.abilists.plugins.sample.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MSampleDao {

	public int istSample(Map<String, Object> map) throws SQLException;
	public int udtSample(Map<String, Object> map) throws SQLException;
	public int dltSample(Map<String, Object> map) throws SQLException;

}