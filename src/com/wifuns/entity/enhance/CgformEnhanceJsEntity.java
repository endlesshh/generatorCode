package com.wifuns.entity.enhance;

import java.beans.Transient;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbId;
import jodd.db.oom.meta.DbTable;

/**   
 * @Title: Entity
 * @Description: JS增强
 * @author ShiQiang
 * @date 2013-08-11 09:47:30
 * @version V1.0   
 *
 */
@DbTable(value = "cgform_enhance_js", schema = "")
@SuppressWarnings("serial")
public class CgformEnhanceJsEntity implements java.io.Serializable {
	/**id*/
	@DbId
	private java.lang.String id;
	/**formId*/
	@DbColumn
	private java.lang.String formId;
	/**js增强类型（form/list）*/
	@DbColumn
	private java.lang.String cgJsType;
	/**增强js*/
	@DbColumn
	private byte[] cgJs;
	
	/**增强js Str*/
	@DbColumn
	private String cgJsStr;
	
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
	public java.lang.String getCgJsType(){
		return this.cgJsType;
	}
	public void setCgJsType(java.lang.String cgJsType){
		this.cgJsType = cgJsType;
	}
	public byte[] getCgJs(){
		return this.cgJs;
	}

	public void setCgJs(byte[] cgJs){
		this.cgJs = cgJs;
	}
	public java.lang.String getContent(){
		return this.content;
	}
	public void setContent(java.lang.String content){
		this.content = content;
	}

	@Transient
	public String getCgJsStr() {
		if(cgJs!=null){
			try{
				cgJsStr = new String(cgJs,"utf-8");
			}catch (Exception e){
			}
		}
		return cgJsStr;
	}

	public void setCgJsStr(String cgJsStr) {
		this.cgJsStr = cgJsStr;
		if(cgJsStr!=null){
			try {
				this.cgJs = cgJsStr.getBytes("utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 深度复制
	 * @return
	 * @throws Exception
	 */
	public CgformEnhanceJsEntity deepCopy() throws Exception{  
        //将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        ObjectOutputStream oos = new ObjectOutputStream(bos);  
        oos.writeObject(this);  
  
        //将流序列化成对象  
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());  
        ObjectInputStream ois = new ObjectInputStream(bis);  
        return (CgformEnhanceJsEntity) ois.readObject();  
    } 
}
