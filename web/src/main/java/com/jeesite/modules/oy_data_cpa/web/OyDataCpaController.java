/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_data_cpa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.oy_data_cpa.entity.OyDataCpa;
import com.jeesite.modules.oy_data_cpa.service.OyDataCpaService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * oy_data_cpaController
 * @author lhy
 * @version 2018-07-16
 */
@Controller
@RequestMapping(value = "${adminPath}/oy_data_cpa/oyDataCpa")
public class OyDataCpaController extends BaseController {

	@Autowired
	private OyDataCpaService oyDataCpaService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public OyDataCpa get(Long id, boolean isNewRecord) {
		return oyDataCpaService.get(String.valueOf(id), isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("oy_data_cpa:oyDataCpa:view")
	@RequestMapping(value = {"list", ""})
	public String list(OyDataCpa oyDataCpa, Model model) {
		model.addAttribute("oyDataCpa", oyDataCpa);
		return "modules/oy_data_cpa/oyDataCpaList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("oy_data_cpa:oyDataCpa:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<OyDataCpa> listData(OyDataCpa oyDataCpa, HttpServletRequest request, HttpServletResponse response) {

		Map<String,Object> map2 = new HashMap<>();
		List list = new ArrayList();
		map2.put("crop","cmpp");
		map2.put("client_name",",sudai");
		map2.put("phone",list);
		int a = oyDataCpaService.batchUpdate(map2);
		System.out.println("更新——————————"+a);

		Map<String,Object> map3 = new HashMap<>();
		map3.put("crop","cmpp");
		map3.put("client_name","sudai");
		map3.put("update_date",7);
		int b = oyDataCpaService.countTotal(map3);
		System.out.println("统计——————————"+b);
		Page<OyDataCpa> page = oyDataCpaService.findPage(new Page<OyDataCpa>(request, response), oyDataCpa);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("oy_data_cpa:oyDataCpa:view")
	@RequestMapping(value = "form")
	public String form(OyDataCpa oyDataCpa, Model model) {
		model.addAttribute("oyDataCpa", oyDataCpa);
		return "modules/oy_data_cpa/oyDataCpaForm";
	}

	/**
	 * 保存oy_data_cpa
	 */
	@RequiresPermissions("oy_data_cpa:oyDataCpa:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated OyDataCpa oyDataCpa) {
		oyDataCpaService.save(oyDataCpa);
		return renderResult(Global.TRUE, "保存oy_data_cpa成功！");
	}
	
	/**
	 * 删除oy_data_cpa
	 */
	@RequiresPermissions("oy_data_cpa:oyDataCpa:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(OyDataCpa oyDataCpa) {
		oyDataCpaService.delete(oyDataCpa);
		return renderResult(Global.TRUE, "删除oy_data_cpa成功！");
	}
	
}