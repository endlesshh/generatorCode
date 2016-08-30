package com.wifuns.entity.config;

import java.util.List;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbId;
import jodd.db.oom.meta.DbTable;

/**   
 * @Title: Entity
 * @Description: 自动生成表属性
 * @author jueyue
 * @date 2013-06-30 11:36:53
 * @version V1.0   
 *
 */
@DbTable(value = "cgform_head", schema = "")
@SuppressWarnings("serial")
public class CgFormHeadEntity implements java.io.Serializable {
	/**id*/
	@DbId
	private java.lang.String id;
	/**表格名称*/
	@DbColumn
	private java.lang.String tableName;
	/**dategrid是否为树形*/
	@DbColumn
	private java.lang.String isTree;
	/**datagrid是否分页*/
	@DbColumn
	private java.lang.String isPagination;
	/**是否同步了数据库*/
	@DbColumn
	private java.lang.String isDbSynch;
	/**datagrid是否显示复选框*/
	@DbColumn
	private java.lang.String isCheckbox;
	/**查询模式：single(单条件查询：默认),group(组合查询)*/
	@DbColumn
	private java.lang.String querymode;
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
	/**修改人*/
	@DbColumn
	private java.lang.String updateBy;
	/**修改人名称*/
	@DbColumn
	private java.lang.String updateName;
	/**表单版本*/
	@DbColumn
	private java.lang.String jformVersion;
	/**表单类型*/
	@DbColumn
	private Integer jformType;
	/**表单主键策略*/
	@DbColumn
	private java.lang.String jformPkType;
	/**表单主键策略-序列(针对oracle等数据库)*/
	@DbColumn
	private java.lang.String jformPkSequence;
	/**附表关联类型*/
	@DbColumn
	private Integer relationType;
	/**附表清单*/
	@DbColumn
	private String subTableStr;
	/**一对多Tab顺序*/
	private Integer tabOrder;
	/**
	 * 表格列属性
	 */
	@DbColumn
	private List<CgFormFieldEntity> columns;
	
	/**树形列表 父id列名*/
	@DbColumn
	private java.lang.String treeParentIdFieldName;
	/**树形列表 id列名*/
	@DbColumn
	private java.lang.String treeIdFieldname;
	/**树形列表 菜单列名*/
	@DbColumn
	private java.lang.String treeFieldname;
	
	//add-start--Author:luobaoli  Date:20150607 for：增加表单分类列
	/**表单分类*/
	@DbColumn
	private java.lang.String jformCategory;
	//add-end--Author:luobaoli  Date:20150607 for：增加表单分类列
	//add-start--Author:张忠亮  Date:20150618 for：增加表单模板选择
	/**表单模板*/
	@DbColumn
	private String formTemplate;
	//add-end--Author:张忠亮  Date:20150618 for：增加表单模板选择
	
	//add-start--Author:scott Date:20160301 for：online表单移动样式单独配置
	/**表单模板样式(移动端)*/
	@DbColumn
	private String formTemplateMobile;
	//add-end--Author:scott Date:20160301 for：online表单移动样式单独配置
	
	public java.lang.String getId(){
		return this.id;
	}

	public void setId(java.lang.String id){
		this.id = id;
	}
	public java.lang.String getTableName(){
		return this.tableName;
	}
	public void setTableName(java.lang.String tableName){
		this.tableName = tableName;
	}
	public java.lang.String getIsTree(){
		return this.isTree;
	}
	public void setIsTree(java.lang.String isTree){
		this.isTree = isTree;
	}
	public java.lang.String getIsPagination(){
		return this.isPagination;
	}
	public void setIsPagination(java.lang.String isPagination){
		this.isPagination = isPagination;
	}
	public java.lang.String getIsDbSynch(){
		return this.isDbSynch;
	}
	public void setIsDbSynch(java.lang.String isDbSynch){
		this.isDbSynch = isDbSynch;
	}
	public java.lang.String getIsCheckbox(){
		return this.isCheckbox;
	}
	public void setIsCheckbox(java.lang.String isCheckbox){
		this.isCheckbox = isCheckbox;
	}
	public java.lang.String getQuerymode(){
		return this.querymode;
	}
	public void setQuerymode(java.lang.String querymode){
		this.querymode = querymode;
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
	public List<CgFormFieldEntity> getColumns() {
		return columns;
	}

	public void setColumns(List<CgFormFieldEntity> columns) {
		this.columns = columns;
	}
	public java.lang.String getJformVersion() {
		return jformVersion;
	}

	public void setJformVersion(java.lang.String jformVersion) {
		this.jformVersion = jformVersion;
	}
	/**
	 *方法: 取得Integer
	 *1-单表,2-主表,3-从表
	 *@return: INteger  表单类型
	 */
	public Integer getJformType() {
		return jformType;
	}

	public void setJformType(Integer jformType) {
		this.jformType = jformType;
	}
	public java.lang.String getJformPkType() {
		return jformPkType;
	}

	public void setJformPkType(java.lang.String jformPkType) {
		this.jformPkType = jformPkType;
	}
	public java.lang.String getJformPkSequence() {
		return jformPkSequence;
	}

	public void setJformPkSequence(java.lang.String jformPkSequence) {
		this.jformPkSequence = jformPkSequence;
	}

	public String getSubTableStr() {
		return subTableStr;
	}

	public void setSubTableStr(String subTableStr) {
		this.subTableStr = subTableStr;
	}
	
	/**
	 *方法: 取得Integer
	 *0：一对多 1：一对一
	 *@return: INteger  附表关联类型
	 */
	public Integer getRelationType() {
		return relationType;
	}

	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}
	
	public Integer getTabOrder() {
		return tabOrder;
	}

	public void setTabOrder(Integer tabOrder) {
		this.tabOrder = tabOrder;
	}
	
	public java.lang.String getTreeParentIdFieldName() {
		return treeParentIdFieldName;
	}

	public void setTreeParentIdFieldName(java.lang.String treeParentIdFieldName) {
		this.treeParentIdFieldName = treeParentIdFieldName;
	}

	public java.lang.String getTreeIdFieldname() {
		return treeIdFieldname;
	}

	public void setTreeIdFieldname(java.lang.String treeIdFieldname) {
		this.treeIdFieldname = treeIdFieldname;
	}

	public java.lang.String getTreeFieldname() {
		return treeFieldname;
	}

	public void setTreeFieldname(java.lang.String treeFieldname) {
		this.treeFieldname = treeFieldname;
	}

	/**
	 *@return: INteger  表单分类
	 */
	public java.lang.String getJformCategory() {
		return jformCategory;
	}

	public void setJformCategory(java.lang.String jformCategory) {
		this.jformCategory = jformCategory;
	}
	//add-start--Author:张忠亮  Date:20150618 for：增加表单模板选择
	public String getFormTemplate() {
		return formTemplate;
	}

	public void setFormTemplate(String formTemplate) {
		this.formTemplate = formTemplate;
	}
	//add-end--Author:张忠亮  Date:20150618 for：增加表单模板选择
	public String getFormTemplateMobile() {
		return formTemplateMobile;
	}

	public void setFormTemplateMobile(String formTemplateMobile) {
		this.formTemplateMobile = formTemplateMobile;
	}
	
}
