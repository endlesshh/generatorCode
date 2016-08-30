package com.wifuns.entity.enhance;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbId;
import jodd.db.oom.meta.DbTable;

/**   
 * @Title: Entity
 * @Description: cgform_enhance_java
 * @author onlineGenerator
 * @date 2015-06-29 13:48:27
 * @version V1.0   
 *
 */
@DbTable(value = "cgform_enhance_java", schema = "")
@SuppressWarnings("serial")
public class CgformEnhanceJavaEntity implements java.io.Serializable {
	/**主键*/
	@DbId
	private java.lang.String id;
	
	/**按纽编码*/
	@DbColumn
	private java.lang.String buttonCode;
	
	/**类型*/
	@DbColumn
	private java.lang.String cgJavaType;
	/**数值*/
	@DbColumn
	private java.lang.String cgJavaValue;
	/**表单ID*/
	@DbColumn
	private java.lang.String formId;
	
	public java.lang.String getId(){
		return this.id;
	}

	public void setId(java.lang.String id){
		this.id = id;
	}
	public java.lang.String getCgJavaType(){
		return this.cgJavaType;
	}

	public void setCgJavaType(java.lang.String cgJavaType){
		this.cgJavaType = cgJavaType;
	}
	public java.lang.String getCgJavaValue(){
		return this.cgJavaValue;
	}
	public void setCgJavaValue(java.lang.String cgJavaValue){
		this.cgJavaValue = cgJavaValue;
	}
	public java.lang.String getFormId(){
		return this.formId;
	}
	public void setFormId(java.lang.String formId){
		this.formId = formId;
	}
	public java.lang.String getButtonCode() {
		return buttonCode;
	}

	public void setButtonCode(java.lang.String buttonCode) {
		this.buttonCode = buttonCode;
	}

	@Override
	public String toString() {
		return "CgformEnhanceJavaEntity [id=" + id + ", buttonCode="
				+ buttonCode + ", cgJavaType=" + cgJavaType + ", cgJavaValue="
				+ cgJavaValue + ", formId=" + formId + "]";
	}
	
}
