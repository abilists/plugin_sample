package com.abilists.plugins.sample.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.abilists.bean.AbilistsModel;
import com.abilists.core.common.bean.CommonBean;
import com.abilists.core.controller.CommonAbilistsController;
import com.abilists.plugins.sample.bean.PluginsModel;
import com.abilists.plugins.sample.bean.para.SltSamplePara;
import com.abilists.plugins.sample.bean.para.UdtSamplePara;
import com.abilists.plugins.sample.service.SampleService;

@Controller
@RequestMapping("/plugins/Sample")
public class SampleController extends CommonAbilistsController {
	final Logger logger = LoggerFactory.getLogger(SampleController.class);

	@Autowired
	private SampleService sampleService;
	@Autowired
	private CommonBean commonBean;

	@RequestMapping(value = {"/", "", "index"})
	public String index(@Validated SltSamplePara sltSamplePara, HttpServletRequest request, ModelMap model) throws Exception {
		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("plugins");
		abilistsModel.setMenu("Sample");

		PluginsModel pluginsModel = new PluginsModel();

		// Set user id
		this.handleSessionInfo(request.getSession(), sltSamplePara);

		model.addAttribute("model", abilistsModel);
		model.addAttribute("plugins", pluginsModel);

		return "apps/Sample/index";
	}

	@RequestMapping(value = { "udtSample" })
	public String udtSample(@Valid UdtSamplePara udtSamplePara, BindingResult bindingResult, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("plugins");
		abilistsModel.setMenu("Sample");

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

		// Execute the transaction
		if (!sampleService.udtSample(udtSamplePara)) {
			logger.error("udtSample - updating is error. userId={}, utrNo={}", udtSamplePara.getUserId(), udtSamplePara.getUtrNo());
			mapErrorMessage.put("errorMessage", message.getMessage("parameter.update.error.message", null, locale));
			model.addAttribute("mapErrorMessage", mapErrorMessage);
			return "apps/errors/systemErrors";
		}

		return "redirect:/plugins/sample";
	}

}