package com.wifuns.entity.config;

import jodd.db.oom.meta.DbTable;

/**   
 * @Title: Entity
 * @Description: 子表
 * @author onlineGenerator
 * @date 2015-06-10 17:19:06
 * @version V1.0   
 *
 */

@DbTable(value = "jform_graphreport_item", schema = "")
@SuppressWarnings("serial")
public class JformGraphreportItemEntity implements java.io.Serializable {
	/**id*/
	private String id;
	/**字段名*/
	private String fieldName;
	/**字段文本*/
	private String fieldTxt;
	/**排序*/
	private Integer orderNum;
	/**字段类型*/
	private String fieldType;
	/**是否显示*/
	private String isShow;
	/**是否查询*/
	private String searchFlag;
	/**查询模式*/
	private String searchMode;
	/**字典Code*/
	private String dictCode;
	/**显示图表*/
	private String isGraph;
	/**图表类型*/
	private String graphType;
	/**图表名称*/
	private String graphName;
	/**标签名称*/
	private String tabName;
	/**字段href*/
	private String fieldHref;
	/**取值表达式*/
	private String replaceVa;
	/**cgreportHeadId*/
	private String cgreportHeadId;
	
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getFieldName(){
		return this.fieldName;
	}

	public void setFieldName(String fieldName){
		this.fieldName = fieldName;
	}
	public String getFieldTxt(){
		return this.fieldTxt;
	}

	public void setFieldTxt(String fieldTxt){
		this.fieldTxt = fieldTxt;
	}
	public Integer getOrderNum(){
		return this.orderNum;
	}

	public void setOrderNum(Integer orderNum){
		this.orderNum = orderNum;
	}
	public String getFieldType(){
		return this.fieldType;
	}
	public void setFieldType(String fieldType){
		this.fieldType = fieldType;
	}
	public String getIsShow(){
		return this.isShow;
	}

	public void setIsShow(String isShow){
		this.isShow = isShow;
	}
	public String getSearchFlag(){
		return this.searchFlag;
	}
	public void setSearchFlag(String searchFlag){
		this.searchFlag = searchFlag;
	}
	public String getSearchMode(){
		return this.searchMode;
	}

	public void setSearchMode(String searchMode){
		this.searchMode = searchMode;
	}
	public String getDictCode(){
		return this.dictCode;
	}

	public void setDictCode(String dictCode){
		this.dictCode = dictCode;
	}
	public String getIsGraph(){
		return this.isGraph;
	}

	public void setIsGraph(String isGraph){
		this.isGraph = isGraph;
	}
	public String getGraphType(){
		return this.graphType;
	}
	public void setGraphType(String graphType){
		this.graphType = graphType;
	}
	public String getGraphName(){
		return this.graphName;
	}

	public void setGraphName(String graphName){
		this.graphName = graphName;
	}
	public String getTabName(){
		return this.tabName;
	}
	public void setTabName(String tabName){
		this.tabName = tabName;
	}
	public String getFieldHref(){
		return this.fieldHref;
	}

	public void setFieldHref(String fieldHref){
		this.fieldHref = fieldHref;
	}
	public String getReplaceVa(){
		return this.replaceVa;
	}

	public void setReplaceVa(String replaceVa){
		this.replaceVa = replaceVa;
	}
	public String getCgreportHeadId(){
		return this.cgreportHeadId;
	}
	public void setCgreportHeadId(String cgreportHeadId){
		this.cgreportHeadId = cgreportHeadId;
	}
}
