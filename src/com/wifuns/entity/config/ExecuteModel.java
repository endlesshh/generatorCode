package com.wifuns.entity.config;
/***
 * 工厂方法根据该类的 模板名称和风格进行生成
 * @author ShiQiang
 *
 */
public class ExecuteModel {
	/**
	 * 模板名称
	 */
	private String templateName;
	/**
	 * 类型名称如：jsp,service等
	 */
	private String style;
	
	
	public ExecuteModel(String templateName,String style){
		this.templateName = templateName;
		this.style = style;
	}
	
	
	public void setTemplateName(String templateName){
		this.templateName = templateName;
	}
	public String getTemplateName(){
		return this.templateName;
	}
	
	public void setStyle(String style){
		this.style = style;
	}
	public String getStyle(){
		return this.style;
	}
}
