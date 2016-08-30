package com.wifuns.entity.button;

import java.beans.Transient;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbId;
import jodd.db.oom.meta.DbTable;

/**   
 * @Title: Entity
 * @Description: 按钮sql增强
 * @author ShiQiang
 * @date 2013-08-07 23:09:23
 * @version V1.0   
 *
 */
@DbTable(value = "cgform_button_sql", schema = "")
@SuppressWarnings("serial")
public class CgformButtonSqlEntity implements java.io.Serializable {
	/**id*/
	@DbId
	private java.lang.String id;
	/**外键关联cgform_head*/
	@DbColumn
	private java.lang.String formId;
	/**按钮编码*/
	@DbColumn
	private java.lang.String buttonCode;
	/**称名*/
	@DbColumn
	private java.lang.String cgbSqlName;
	/**强增sql*/
	@DbColumn
	private byte[] cgbSql;
	/**强增sql Str*/
	@DbColumn
	private String cgbSqlStr;
	/**描述*/
	@DbColumn
	private java.lang.String content;
	
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
	public java.lang.String getCgbSqlName(){
		return this.cgbSqlName;
	}
	public void setCgbSqlName(java.lang.String cgbSqlName){
		this.cgbSqlName = cgbSqlName;
	}
	public byte[] getCgbSql(){
		return this.cgbSql;
	}

	public void setCgbSql(byte[] cgbSql){
		this.cgbSql = cgbSql;
	}
	
	@Transient
	public String getCgbSqlStr() {
		if(cgbSql!=null){
			cgbSqlStr = new String(cgbSql);
		}
		return cgbSqlStr;
	}

	public void setCgbSqlStr(String cgbSqlStr) {
		this.cgbSqlStr = cgbSqlStr;
		if(cgbSqlStr!=null){
			this.cgbSql = cgbSqlStr.getBytes();
		}
	}

	public java.lang.String getContent(){
		return this.content;
	}
	public void setContent(java.lang.String content){
		this.content = content;
	}
}
