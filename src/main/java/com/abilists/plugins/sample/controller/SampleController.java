package com.abilists.plugins.sample.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.abilists.bean.AbilistsModel;
import com.abilists.core.common.bean.CommonBean;
import com.abilists.core.controller.AbstractBaseController;
import com.abilists.core.controller.CommonAbilistsController;
import com.abilists.plugins.sample.bean.PluginsModel;
import com.abilists.plugins.sample.bean.model.SampleModel;
import com.abilists.plugins.sample.bean.para.DltSamplePara;
import com.abilists.plugins.sample.bean.para.IstSamplePara;
import com.abilists.plugins.sample.bean.para.SltSamplePara;
import com.abilists.plugins.sample.bean.para.UdtSamplePara;
import com.abilists.plugins.sample.service.SampleService;

import io.utility.security.TokenUtility;

@Controller
@RequestMapping("/plugins/sample")
public class SampleController extends CommonAbilistsController {
	final Logger logger = LoggerFactory.getLogger(SampleController.class);

	@Autowired
	private SampleService sampleService;
	@Autowired
	private CommonBean commonBean;

	@RequestMapping(value = {"/", "", "index"}, method = RequestMethod.GET)
	public String index(@Validated SltSamplePara sltSamplePara, HttpServletRequest request, ModelMap model) throws Exception {
		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("plugins");
		abilistsModel.setMenu("sample");

		PluginsModel pluginsModel = new PluginsModel();

		// Set user id
		this.handleSessionInfo(request.getSession(), sltSamplePara);

		// Set Paging list
		int intSum = sampleService.sltSampleSum(sltSamplePara);
		abilistsModel.setPaging(sampleService.makePaging(sltSamplePara, intSum));
		// Get sample list
		pluginsModel.setSampleList(sampleService.sltSampleList(sltSamplePara));
		
		// Get key and token
		String token = TokenUtility.generateToken(TokenUtility.SHA_256);
		String key = this.makeKey(sltSamplePara.getUserId(), AbstractBaseController.PREFIX_IST_KEY);
		commonBean.addTokenExpireMap(key, token);
		abilistsModel.setToken(token);

		model.addAttribute("model", abilistsModel);
		model.addAttribute("plugins", pluginsModel);

		return "apps/sample/index";
	}

	@RequestMapping(value = "/sltSampleAjax")
	public @ResponseBody SampleModel sltSampleAjax(@RequestBody SltSamplePara sltSamplePara,
			HttpSession session) throws Exception {

		// Set user id
		this.handleSessionInfo(session, sltSamplePara);

		// Get user Reports.
		SampleModel sample = sampleService.sltSample(sltSamplePara);

		// Get key and token
		String token = TokenUtility.generateToken(TokenUtility.SHA_256);
		String key = this.makeKey(sltSamplePara.getUserId(), AbstractBaseController.PREFIX_UDT_KEY);
		commonBean.addTokenExpireMap(key, token);

		// Set a token
		sample.setToken(token);

		return sample;
	}

	@RequestMapping(value = { "istSample" })
	public String istSample(@Valid IstSamplePara istSamplePara, BindingResult bindingResult, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("plugins");
		abilistsModel.setMenu("sample");

		// Set language in Locale.
		Locale locale = RequestContextUtils.getLocale(request);

		Map<String, String> mapErrorMessage = new HashMap<String, String>();
		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("istSample - There are parameter errors.");
			response.setStatus(400);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "apps/errors/parameterErrors";
		}

		// Set user id
		this.handleSessionInfo(request.getSession(), istSamplePara);

		logger.info("2 userId=" + istSamplePara.getUserId());

		// Validate token
		String key = this.makeKey(istSamplePara.getUserId(), AbstractBaseController.PREFIX_IST_KEY);
		if (!istSamplePara.getToken().equals(commonBean.getTokenExpireMap(key))) {
			logger.error("istSample - token is wrong. parameter token=" + istSamplePara.getToken() + ", server token=" + commonBean.getTokenExpireMap(key));
			mapErrorMessage.put("errorMessage", message.getMessage("parameter.error.token.message", null, locale));
			model.addAttribute("errorMessage", mapErrorMessage);
			return "apps/errors/parameterErrors";
		}

		// Execute the transaction
		if (!sampleService.istSample(istSamplePara)) {
			logger.error("istReports - inserting is error. userId={}", istSamplePara.getUserId());
			mapErrorMessage.put("errorMessage", message.getMessage("parameter.insert.error.message", null, locale));
			model.addAttribute("mapErrorMessage", mapErrorMessage);
			return "apps/errors/systemErrors";
		}

		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");
		return "redirect:/plugins/sample";
	}

	@RequestMapping(value = { "udtSample" })
	public String udtSample(@Valid UdtSamplePara udtSamplePara, BindingResult bindingResult, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("plugins");
		abilistsModel.setMenu("sample");

		// Set language in Locale.
		Locale locale = RequestContextUtils.getLocale(request);

		Map<String, String> mapErrorMessage = new HashMap<String, String>();
		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("udtSample - There are parameter errors.");
			response.setStatus(400);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "apps/errors/parameterErrors";
		}

		// Set user id
		this.handleSessionInfo(request.getSession(), udtSamplePara);

		// Validate token
		String key = this.makeKey(udtSamplePara.getUserId(), AbstractBaseController.PREFIX_UDT_KEY);
		if (!udtSamplePara.getToken().equals(commonBean.getTokenExpireMap(key))) {
			logger.error("udtSample - token is wrong. parameter token=" + udtSamplePara.getToken() + ", server token=" + commonBean.getTokenExpireMap(key));
			mapErrorMessage.put("errorMessage", message.getMessage("parameter.error.token.message", null, locale));
			model.addAttribute("errorMessage", mapErrorMessage);
			return "apps/errors/parameterErrors";
		}

		// Execute the transaction
		if (!sampleService.udtSample(udtSamplePara)) {
			logger.error("udtSample - updating is error. userId={}, usmNo={}", udtSamplePara.getUserId(), udtSamplePara.getUsmNo());
			mapErrorMessage.put("errorMessage", message.getMessage("parameter.update.error.message", null, locale));
			model.addAttribute("mapErrorMessage", mapErrorMessage);
			return "apps/errors/systemErrors";
		}

		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");
		return "redirect:/plugins/sample";
	}


	@RequestMapping(value = { "dltSample" })
	public String dltSample(@Valid DltSamplePara dltSamplePara, BindingResult bindingResult, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("works");
		abilistsModel.setMenu("sample");

		// Set language in Locale.
		Locale locale = RequestContextUtils.getLocale(request);

		Map<String, String> mapErrorMessage = new HashMap<String, String>();
		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("dltSample - There are parameter errors.");
			response.setStatus(400);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "apps/errors/parameterErrors";
		}

		// Set user id
		this.handleSessionInfo(request.getSession(), dltSamplePara);

		// Validate token
		String key = this.makeKey(dltSamplePara.getUserId(), AbstractBaseController.PREFIX_UDT_KEY);
		if (!dltSamplePara.getToken().equals(commonBean.getTokenExpireMap(key))) {
			logger.error("dltSample - token is wrong. parameter token=" + dltSamplePara.getToken() + ", server token=" + commonBean.getTokenExpireMap(key));
			mapErrorMessage.put("errorMessage", message.getMessage("parameter.error.token.message", null, locale));
			model.addAttribute("errorMessage", mapErrorMessage);
			return "apps/errors/parameterErrors";
		}

		// Execute the transaction
		if (!sampleService.dltSample(dltSamplePara)) {
			logger.error("dltSample - deleting is error. userId={}, usmNo={}", dltSamplePara.getUserId(), dltSamplePara.getUsmNo());
			mapErrorMessage.put("errorMessage", message.getMessage("parameter.delete.error.message", null, locale));
			model.addAttribute("mapErrorMessage", mapErrorMessage);
			return "apps/errors/systemErrors";
		}

		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");
		return "redirect:/plugins/sample";
	}
}