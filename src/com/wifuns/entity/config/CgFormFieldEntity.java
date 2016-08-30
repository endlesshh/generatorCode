package com.wifuns.entity.config;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbId;
import jodd.db.oom.meta.DbTable;

/**   
 * @Title: Entity
 * @Description: 自动生成表的列属性
 * @author jueyue
 * @date 2013-06-30 11:37:32
 * @version V1.0   
 *
 */
@DbTable(value = "cgform_field", schema = "")
@SuppressWarnings("serial")
public class CgFormFieldEntity implements java.io.Serializable {
	/**id*/
	@DbId
	private java.lang.String id;
	/**字段名称*/
	@DbColumn
	private java.lang.String fieldName;
	/**关联的表*/
	@DbColumn
	private CgFormHeadEntity table;
	/**字段长度*/
	@DbColumn
	private java.lang.Integer length;
	/**小数点长度*/
	@DbColumn
	private java.lang.Integer pointLength;
	/**字段类型*/
	@DbColumn
	private java.lang.String type;
	/**是否允许空值*/
	@DbColumn
	private java.lang.String isNull;
	/**在表中的顺序*/
	@DbColumn
	private java.lang.Integer orderNum;
	/**是否主键*/
	@DbColumn
	private java.lang.String isKey;
	/**是否显示*/
	@DbColumn
	private java.lang.String isShow;
	/**是否在列表上显示*/
	@DbColumn
	private java.lang.String isShowList;
	/**显示类型*/
	@DbColumn
	private java.lang.String showType;
	/**是否生产查询字段*/
	@DbColumn
	private java.lang.String isQuery;
	/**控件长度*/
	@DbColumn
	private java.lang.Integer fieldLength;
	/**字段Href*/
	@DbColumn
	private java.lang.String fieldHref;
	/**控件校验*/
	@DbColumn
	private java.lang.String fieldValidType;
	/**查询类型single(默认：单字段查询),group(范围查询)*/
	@DbColumn
	private java.lang.String queryMode;
	/**功能注释*/
	@DbColumn
	private java.lang.String content;
	/**创建时间*/
	@DbColumn
	private java.util.Date createDate;
	/**创建人ID*/
	@DbColumn
	private java.lang.String createBy;
	/**创建人名称*/
	@DbColumn
	private java.lang.String createName;
	/**修改时间*/
	@DbColumn
	private java.util.Date updateDate;
	/**修改人ID*/
	@DbColumn
	private java.lang.String updateBy;
	/**修改人名称*/
	@DbColumn
	private java.lang.String updateName;
	/**字典Code*/
	@DbColumn
	private java.lang.String dictField;
	/**字典Table*/
	@DbColumn
	private java.lang.String dictTable;
	/**字典Text*/
	@DbColumn
	private java.lang.String dictText;
	/**主表名*/
	@DbColumn
	private java.lang.String mainTable;
	/**主表字段*/
	@DbColumn
	private java.lang.String mainField;
	/**旧的字段名称*/
	@DbColumn
	private java.lang.String oldFieldName;
	/**字段默认值*/
	@DbColumn
	private java.lang.String fieldDefault;
	//add-start--Author:luobaoli  Date:20150610 for：添加扩展参数字段
	/**扩展参数**/
	@DbColumn
	private java.lang.String extendJson;
	
	public java.lang.String getId(){
		return this.id;
	}
	 
	public void setId(java.lang.String id){
		this.id = id;
	}
	
	public java.lang.String getFieldName(){
		return this.fieldName;
	}

	public void setFieldName(java.lang.String fieldName){
		this.fieldName = fieldName;
	}
	
	public CgFormHeadEntity getTable(){
		return this.table;
	} 
	public void setTable(CgFormHeadEntity table){
		this.table = table;
	}
	 
	public java.lang.Integer getLength(){
		return this.length;
	}

	 
	public void setLength(java.lang.Integer length){
		this.length = length;
	}
	 
	public java.lang.Integer getPointLength(){
		return this.pointLength;
	}

	 
	public void setPointLength(java.lang.Integer pointLength){
		this.pointLength = pointLength;
	}
	 
	public java.lang.String getType(){
		return this.type;
	}

	 
	public void setType(java.lang.String type){
		this.type = type;
	}
	 
	public java.lang.String getIsNull(){
		return this.isNull;
	}
 
	public void setIsNull(java.lang.String isNull){
		this.isNull = isNull;
	}
	 
	public java.lang.String getIsShow(){
		return this.isShow;
	}

	 
	public void setIsShow(java.lang.String isShow){
		this.isShow = isShow;
	}
	 
	public java.lang.String getShowType(){
		return this.showType;
	}

	 
	public void setShowType(java.lang.String showType){
		this.showType = showType;
	}
	 
	public java.lang.String getIsQuery(){
		return this.isQuery;
	}
 
	public void setIsQuery(java.lang.String isQuery){
		this.isQuery = isQuery;
	}
	 
	public java.lang.String getQueryMode(){
		return this.queryMode;
	}

	 
	public void setQueryMode(java.lang.String queryMode){
		this.queryMode = queryMode;
	}
	 
	public java.lang.String getContent(){
		return this.content;
	}

	 
	public void setContent(java.lang.String content){
		this.content = content;
	}
	 
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
 
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	 
	public java.lang.String getCreateBy(){
		return this.createBy;
	}
 
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	 
	public java.lang.String getCreateName(){
		return this.createName;
	}

	  
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	 
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	 
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	 
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}
 
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	 
	public java.lang.String getUpdateName(){
		return this.updateName;
	}
 
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	 
	public java.lang.Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(java.lang.Integer orderNum) {
		this.orderNum = orderNum;
	}
	 
	public java.lang.String getIsKey() {
		return isKey;
	}

	public void setIsKey(java.lang.String isKey) {
		this.isKey = isKey;
	}
	 
	public java.lang.Integer getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(java.lang.Integer field_length) {
		this.fieldLength = field_length;
	}
	 
	public java.lang.String getFieldHref() {
		return fieldHref;
	}

	public void setFieldHref(java.lang.String field_href) {
		this.fieldHref = field_href;
	}
	 
	public java.lang.String getFieldValidType() {
		return fieldValidType;
	}

	public void setFieldValidType(java.lang.String field_valid_type) {
		this.fieldValidType = field_valid_type;
	}
	 
	public java.lang.String getDictField() {
		return dictField;
	}

	public void setDictField(java.lang.String dictField) {
		this.dictField = dictField;
	}
	 
	public java.lang.String getDictTable() {
		return dictTable;
	}

	public void setDictTable(java.lang.String dictTable) {
		this.dictTable = dictTable;
	}
	 
	public java.lang.String getMainTable() {
		return mainTable;
	}

	public void setMainTable(java.lang.String mainTable) {
		this.mainTable = mainTable;
	}
	 
	public java.lang.String getMainField() {
		return mainField;
	}

	public void setMainField(java.lang.String mainField) {
		this.mainField = mainField;
	}
 
	public java.lang.String getOldFieldName() {
		return oldFieldName;
	}

	public void setOldFieldName(java.lang.String oldFieldName) {
		this.oldFieldName = oldFieldName;
	}
	 
	public java.lang.String getIsShowList() {
		return isShowList;
	}

	public void setIsShowList(java.lang.String isShowList) {
		this.isShowList = isShowList;
	}
	 
	public java.lang.String getDictText() {
		return dictText;
	}

	public void setDictText(java.lang.String dictText) {
		this.dictText = dictText;
	}
 
	public java.lang.String getFieldDefault() {
		if (fieldDefault != null) {
			fieldDefault = fieldDefault.trim();
		}
		return fieldDefault;
	}

	public void setFieldDefault(java.lang.String fieldDefault) {
		this.fieldDefault = fieldDefault;
	}
 
	public java.lang.String getExtendJson() {
		return extendJson;
	}

	public void setExtendJson(java.lang.String extendJson) {
		this.extendJson = extendJson;
	}
	 
}
