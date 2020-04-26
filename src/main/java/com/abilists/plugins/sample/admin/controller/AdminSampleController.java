 package com.abilists.plugins.sample.admin.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abilists.bean.AdminAbilistsModel;
import com.abilists.core.controller.AbstractBaseController;
import com.abilists.plugins.sample.admin.service.AdminSampleService;
import com.abilists.plugins.sample.bean.PluginsModel;

import base.bean.para.CommonPara;

@Controller
@RequestMapping("/admin/plugins/sample")
public class AdminSampleController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(AdminSampleController.class);

	@Autowired
	private AdminSampleService adminSampleService;
    @Autowired
    ServletContext servletContext;

	@RequestMapping(value = {"/", "", "index"})
	public String pluginList(@Valid CommonPara commonPara, HttpSession session, 
			ModelMap model, HttpServletRequest request) throws Exception {

		AdminAbilistsModel adminAbilistsModel = new AdminAbilistsModel();
		adminAbilistsModel.setNavi("plugins");
		adminAbilistsModel.setMenu("sample");
		
		PluginsModel pluginsModel = new PluginsModel();

		// Set user id
		this.handleSessionInfo(request.getSession(), commonPara);

		// Set Paging list
		commonPara.setUserId(null);
		int intSum = adminSampleService.sltSampleSum(commonPara);
		adminAbilistsModel.setPaging(adminSampleService.makePaging(commonPara, intSum));

		// Get time recorded list
		pluginsModel.setSampleList(adminSampleService.sltSampleList(commonPara));

		model.addAttribute("model", adminAbilistsModel);
		model.addAttribute("plugins", pluginsModel);

		return "apps/sample/admin/index";
	}

}