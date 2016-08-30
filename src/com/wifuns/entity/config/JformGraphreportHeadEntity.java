package com.wifuns.entity.config;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;


@DbTable(value = "jform_graphreport_head", schema = "")
@SuppressWarnings("serial")
public class JformGraphreportHeadEntity implements java.io.Serializable {
	/**id*/
	@DbColumn
	private String id;
	/**名称*/
	@DbColumn
	private String name;
	/**编码*/
	@DbColumn
	private String code;
	/**查询数据SQL*/
	@DbColumn
	private String cgrSql;
	/**描述*/
	@DbColumn
	private String content;
	/**y轴文字*/
	@DbColumn
	private String ytext;
	/**x轴数据*/
	@DbColumn
	private String categories;
	/**是否显示明细*/
	@DbColumn
	private String isShowList;
	/**扩展JS*/
	@DbColumn
	private String xpageJs;
	
	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}
	public String getCode(){
		return this.code;
	}

	public void setCode(String code){
		this.code = code;
	}
	public String getCgrSql(){
		return this.cgrSql;
	}

	public void setCgrSql(String cgrSql){
		this.cgrSql = cgrSql;
	}
	
	public String getContent(){
		return this.content;
	}

	public void setContent(String content){
		this.content = content;
	}
	
	
	public String getYtext(){
		return this.ytext;
	}

	public void setYtext(String ytext){
		this.ytext = ytext;
	}
	
	public String getCategories(){
		return this.categories;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  x轴数据
	 */
	public void setCategories(String categories){
		this.categories = categories;
	}
	
	public String getIsShowList(){
		return this.isShowList;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否显示明细
	 */
	public void setIsShowList(String isShowList){
		this.isShowList = isShowList;
	}

	/**
	 *方法: 取得javax.xml.soap.Text
	 *@return: javax.xml.soap.Text  扩展JS
	 */
	public String getXpageJs() {
		return xpageJs;
	}

	public void setXpageJs(String xpageJs) {
		this.xpageJs = xpageJs;
	}
	
	

}
