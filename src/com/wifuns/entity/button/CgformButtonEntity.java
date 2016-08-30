package com.wifuns.entity.button;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbId;
import jodd.db.oom.meta.DbTable;

/**   
 * @Title: Entity
 * @Description: 表单自定义按钮
 * @author ShiQiang
 * @date 2013-08-07 20:16:26
 * @version V1.0   
 *
 */
@DbTable(value = "cgform_button", schema = "")
@SuppressWarnings("serial")
public class CgformButtonEntity implements java.io.Serializable {
	/**id*/
	@DbId
	private java.lang.String id;
	/**外键关联cgform_head*/
	@DbColumn
	private java.lang.String formId;
	/**按钮编码*/
	@DbColumn
	private java.lang.String buttonCode;
	/**按钮名称*/
	@DbColumn
	private java.lang.String buttonName;
	/**按钮样式link/button*/
	@DbColumn
	private java.lang.String buttonStyle;
	/**动作类型:js/bus*/
	@DbColumn
	private java.lang.String optType;
	/**显示表达式:exp="status#eq#0"*/
	@DbColumn
	private java.lang.String exp;
	/**0:禁用/1:使用*/
	@DbColumn
	private java.lang.String buttonStatus;
	/**顺序*/
	@DbColumn
	private java.lang.Integer orderNum;
	/**按钮图标样式*/
	@DbColumn
	private java.lang.String buttonIcon;
	
	public java.lang.String getId(){
		return this.id;
	}

	public void setId(java.lang.String id){
		this.id = id;
	}
	public java.lang.String getFormId(){
		return this.formId;
	}

	public void setFormId(java.lang.String formId){
		this.formId = formId;
	}
	public java.lang.String getButtonCode(){
		return this.buttonCode;
	}
	public void setButtonCode(java.lang.String buttonCode){
		this.buttonCode = buttonCode;
	}
	public java.lang.String getButtonName(){
		return this.buttonName;
	}
	public void setButtonName(java.lang.String buttonName){
		this.buttonName = buttonName;
	}
	public java.lang.String getButtonStyle(){
		return this.buttonStyle;
	}
	public void setButtonStyle(java.lang.String buttonStyle){
		this.buttonStyle = buttonStyle;
	}
	public java.lang.String getOptType(){
		return this.optType;
	}

	public void setOptType(java.lang.String optType){
		this.optType = optType;
	}
	public java.lang.String getExp(){
		return this.exp;
	}
	public void setExp(java.lang.String exp){
		this.exp = exp;
	}
	public java.lang.String getButtonStatus(){
		return this.buttonStatus;
	}
	public void setButtonStatus(java.lang.String buttonStatus){
		this.buttonStatus = buttonStatus;
	}
	public java.lang.Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(java.lang.Integer orderNum) {
		this.orderNum = orderNum;
	}
	public java.lang.String getButtonIcon() {
		return buttonIcon;
	}

	public void setButtonIcon(java.lang.String buttonIcon) {
		this.buttonIcon = buttonIcon;
	}
	
	
}
