package com.wifuns.shop.manage.admin.action;

import com.easyjf.beans.BeanUtils;
import com.easyjf.beans.BeanWrapper;
import com.wifuns.shop.core.annotation.Log;
import com.wifuns.shop.core.annotation.SecurityMapping;
import com.wifuns.shop.core.mv.JModelAndView;
import com.wifuns.shop.core.query.support.IPageList;
import com.wifuns.shop.core.tools.CommUtil;
import com.wifuns.shop.core.tools.WebForm;
import com.wifuns.shop.core.domain.virtual.SysMap;

import org.apache.commons.lang.StringUtils;


import com.wifuns.shop.foundation.domain.${entityName};
import com.wifuns.shop.foundation.domain.LogType;
import com.wifuns.shop.foundation.domain.query.${entityName}QueryObject;
import com.wifuns.shop.foundation.service.I${entityName}Service;
import com.wifuns.shop.foundation.service.ISysConfigService;
import com.wifuns.shop.foundation.service.IUserConfigService;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName. 	  ${entityName}
 * @Description.  ${ftl_description}
 * @author 		  ${author}
 * @date. 		  ${ftl_create_time}
 * @version 	  V1.0
 */
@Controller
public class ${entityName}ManageAction {

	@Autowired
	private ISysConfigService configService;

	@Autowired
	private IUserConfigService userConfigService;

	@Autowired
	private I${entityName}Service ${entityName?uncap_first}Service;

	/**
	 * 
	 * @Title.  列表
	 * @Description. 查询${ftl_description}列表
	 * @param <code>HttpServletRequest</code> request
	 * @param <code>HttpServletResponse</code> response
	 * @param <code>String</code> currentPage  当前页
	 * @param <code>String</code> orderBy      排序字段
	 * @param <code>String</code> orderType    排序类型（升、降） 
	 * @return ModelAndView 
	 */
	@SecurityMapping(title = "${ftl_description}列表", value = "/wifuns/${entityName?uncap_first}_list.htm*", rtype = "admin", rname = "${ftl_description}管理",  rcode = "${entityName?uncap_first}_admin", rgroup = "网站", display = false, rsequence = 0, sequence = 0, level=0, menuState=1, subAccountState=false)
	@RequestMapping({ "/wifuns/${entityName?uncap_first}_list.htm" })
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response, String currentPage, String orderBy,
			String orderType,String condition,String value) {
		ModelAndView mv = new JModelAndView("wifuns/blue/${entityName?uncap_first}_list.html",
				this.configService.getSysConfig(),
				this.userConfigService.getUserConfig(), 0, request, response);

		String url = this.configService.getSysConfig().getAddress();
		if (StringUtils.isBlank(url)) {
			url = CommUtil.getURL(request);
		}
		
		String params = "";
		${entityName}QueryObject qo = new ${entityName}QueryObject(currentPage, mv,orderBy, orderType);
		WebForm wf = new WebForm();
		wf.toQueryPo(request, qo, ${entityName}.class, mv);
		//此处只使用字符串，其他的需要自行添加
		if (condition != null && StringUtils.isNotEmpty(value)) {
			qo.addQuery("obj."+condition, new SysMap(condition, "%" + value+ "%"), "like");
		}
		
		mv.addObject("condition", condition);
		IPageList pList = this. ${entityName?uncap_first}Service.list(qo);
		
		CommUtil.saveIPageList2ModelAndView(url + "/wifuns/${entityName?uncap_first}_list.htm","", params, pList, mv);
		return mv;
	}

	/**
	 * 
	 * @Title. 新增
	 * @Description. 新增${ftl_description}
	 * @param <code>HttpServletRequest</code> request
	 * @param <code>HttpServletResponse</code> response
	 * @param <code>String</code> currentPage  当前页
	 * @return ModelAndView 
	 */
	@SecurityMapping(title = "${ftl_description}添加", value = "/wifuns/${entityName?uncap_first}_add.htm*", rtype = "admin", rname = "${ftl_description}管理", rcode = "${entityName?uncap_first}_admin ", rgroup = "", display = false, rsequence = 0, sequence = 0, level=0, menuState=1, subAccountState=false)
	@RequestMapping({ "/wifuns/${entityName?uncap_first}_add.htm" })
	public ModelAndView coupon_add(HttpServletRequest request,
			HttpServletResponse response, String currentPage) {
		ModelAndView mv = new JModelAndView("wifuns/blue/${entityName?uncap_first}_add.html",
				this.configService.getSysConfig(),
				this.userConfigService.getUserConfig(), 0, request, response);
		mv.addObject("currentPage", currentPage);
		return mv;
	}

	/***
	 * 
	 * @Title. edit
	 * @Description. ${ftl_description}编辑页面
	 * @param <code>HttpServletRequest</code> request
	 * @param <code>HttpServletResponse</code> response
	 * @param <code>String</code> currentPage    当前页
	 * @param <code>String</code> 	id              id 
	 * @return ModelAndView 
	 */
	@SecurityMapping(title = "${ftl_description}编辑", value = "/wifuns/${entityName?uncap_first}_edit.htm*", rtype = "admin", rname = "文章管理", rcode = "${entityName?uncap_first}", rgroup = "${entityName?uncap_first}", display = false, rsequence = 0, sequence = 0, level=0, menuState=1, subAccountState=false)
	@RequestMapping({ "/wifuns/${entityName?uncap_first}_edit.htm" })
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response, String id, String currentPage) {
		ModelAndView mv = new JModelAndView("wifuns/blue/${entityName?uncap_first}_add.html",
				this.configService.getSysConfig(),
				this.userConfigService.getUserConfig(), 0, request, response);
		if (StringUtils.isNotBlank(id)) {
			${entityName} ${entityName?uncap_first} = this. ${entityName?uncap_first}Service.getObjById(Long.valueOf(Long.parseLong(id)));
			mv.addObject("obj", ${entityName?uncap_first});
			mv.addObject("currentPage", currentPage);
			mv.addObject("edit", Boolean.valueOf(true));
		}
		return mv;
	}

	/**
	 * 
	 * @Title. save
	 * @Description. 保存${ftl_description}（添加、修改）
	 * @param <code>HttpServletRequest</code> request
	 * @param <code>HttpServletResponse</code> response
	 * @param <code>String</code> id 文章id
	 * @param <code>String</code> currentPage 当前页
	 * @return ModelAndView 
	 */
	@Log(title = "${ftl_description}保存", type = LogType.SAVE, description = "", entityName = "${entityName}", ip = "")
	@SecurityMapping(title = "${ftl_description}保存", value = "/wifuns/${entityName?uncap_first}_save.htm*", rtype = "admin", rname = "${ftl_description}", rcode = "${entityName?uncap_first}", rgroup = "", display = false, rsequence = 0, sequence = 0, level=0, menuState=1, subAccountState=false)
	@RequestMapping({ "/wifuns/${entityName?uncap_first}_save.htm" })
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, String id, String currentPage) {
		WebForm wf = new WebForm();
		${entityName} ${entityName?uncap_first}= null;

		/** Id为空时为添加、不为空时为修改 */
		if (StringUtils.isBlank(id)) {
			${entityName?uncap_first}= (${entityName}) wf.toPo(request, ${entityName}.class);
			${entityName?uncap_first}.setAddTime(new Date());
		} else {
			${entityName} obj = this. ${entityName?uncap_first}Service.getObjById(Long.valueOf(Long.parseLong(id)));
			${entityName?uncap_first} = (${entityName}) wf.toPo(request, obj);
		} 
		
		if (StringUtils.isBlank(id)){
			this.${entityName?uncap_first}Service.save(${entityName?uncap_first});
		}else{
			this.${entityName?uncap_first}Service.update(${entityName?uncap_first});
  		}
		ModelAndView mv = new JModelAndView("wifuns/blue/success.html",this.configService.getSysConfig(),
				this.userConfigService.getUserConfig(), 0, request, response);
		mv.addObject("list_url", CommUtil.getURL(request)+ "/wifuns/${entityName?uncap_first}_list.htm?currentPage=" + currentPage);
		mv.addObject("op_title", "保存${ftl_description}成功");
		mv.addObject("add_url", CommUtil.getURL(request)+ "/wifuns/${entityName?uncap_first}_add.htm?currentPage=" + currentPage);	
		return mv;
	}

	/**
	 * 
	 * @Title. 删除
	 * @Description. 根据id删除${ftl_description}
	 * @param <code>HttpServletRequest</code> request
	 * @param <code>String</code> mulitId   Id组成的字符串（以逗号隔开）
	 * @return String
	 */
	@Log(title = "${ftl_description}删除", type = LogType.REMOVE, description = "", entityName = "${entityName}", ip = "")
	@SecurityMapping(title = "${ftl_description}删除", value = "/wifuns/${entityName?uncap_first}_del.htm*", rtype = "admin", rname = "${ftl_description}管理", rcode = "${entityName?uncap_first}", rgroup = "", display = false, rsequence = 0, sequence = 0, level=0, menuState=1, subAccountState=false)
	@RequestMapping({ "/wifuns/${entityName?uncap_first}_del.htm" })
	public String delete(HttpServletRequest request, String mulitId) {
		String[] ids = mulitId.split(",");
		for (String id : ids) {
			if(StringUtils.isNotBlank(id)) {
				${entityName } ${entityName?uncap_first}= this.${entityName?uncap_first}Service.getObjById(Long.valueOf(Long.parseLong(id)));
			    if(${entityName?uncap_first} != null){
					this.${entityName?uncap_first}Service.delete(${entityName?uncap_first}.getId());
				}
			}
		}
		return "redirect: ${entityName?uncap_first}_list.htm";
	}

	/**
	 * 
	 * @Title. 异步更新
	 * @Description. ajax更新${ftl_description}字段信息
	 * @param <code>HttpServletRequest</code> 	request
	 * @param <code>HttpServletResponse</code> 	response
	 * @param <code>String</code> id        	Id
	 * @param <code>String</code> fieldName 	字段名称
	 * @param <code>String</code> value     	字段值
	 * @throws ClassNotFoundException
	 */
	@Log(title = "${ftl_description}删除", type = LogType.SAVE, description = "", entityName = "${entityName}", ip = "")
	@SecurityMapping(title = "${ftl_description}更新", value = "/wifuns/${entityName?uncap_first}_ajax.htm*", rtype = "admin", rname = "${ftl_description}管理", rcode = "${entityName?uncap_first}", rgroup = "", display = false, rsequence = 0, sequence = 0, level=0, menuState=1, subAccountState=false)
	@RequestMapping({ "/wifuns/${entityName?uncap_first}_ajax.htm" })
	public void ajax(HttpServletRequest request, HttpServletResponse response,
			String id, String fieldName, String value) throws ClassNotFoundException {
		${entityName} obj = this.${entityName?uncap_first}Service.getObjById(Long.valueOf(Long.parseLong(id)));

		Field[] fields = ${entityName}.class.getDeclaredFields();
		BeanWrapper wrapper = new BeanWrapper(obj);
		Object val = null;
		for (Field field : fields) {
			if (field.getName().equals(fieldName)) {
				Class<?> clz = Class.forName("java.lang.String");
				if (field.getType().getName().equals("int")) {
					clz = Class.forName("java.lang.Integer");
				}
				if (field.getType().getName().equals("boolean")) {
					clz = Class.forName("java.lang.Boolean");
				}
				if (!value.equals("")){
					val = BeanUtils.convertType(value, clz);
				} else {
					val = Boolean.valueOf(!CommUtil.null2Boolean(wrapper.getPropertyValue(fieldName)));
				}
				wrapper.setPropertyValue(fieldName, val);
			}
		}
		this.${entityName?uncap_first}Service.update(obj);
		
		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.print(val.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

