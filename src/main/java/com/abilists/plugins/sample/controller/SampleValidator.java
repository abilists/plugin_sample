package com.abilists.plugins.sample.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;

import com.abilists.core.controller.BaseValidator;
import com.abilists.plugins.sample.bean.para.IstSamplePara;
import com.abilists.plugins.sample.bean.para.UdtSamplePara;

public class SampleValidator implements BaseValidator {

	final Logger logger = LoggerFactory.getLogger(SampleValidator.class);

	@Override
	public <T> Map<String, String> validateBusiness(T para, Errors errors, Locale local) throws IOException {
		Map<String, String> mapErrorMessage = new HashMap<>();

		if (para instanceof UdtSamplePara) {
			UdtSamplePara udtSamplePara = (UdtSamplePara)para;

		} else if(para instanceof IstSamplePara) {
			IstSamplePara istSamplePara = (IstSamplePara)para;

		}

		return mapErrorMessage;
	}

	@Override
	public <T> Map<String, String> validateBusiness(T para, HttpSession session) throws IOException {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
