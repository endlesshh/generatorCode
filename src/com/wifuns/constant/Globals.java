package com.wifuns.constant;

public class Globals {
	/**package ${bussiPackage}.entity.${entityPackage};*/
	public static final String bussiPackage = "bussiPackage";
	public static final String entityPackage = "entityPackage";
	/**public class ${entityName}Entity */
	public static final String entityName = "entityName";
	/**@Table(name = "${tableName}", schema = "")*/
	public static final String tableName = "tableName";
	/** * @Description: ${ftl_description}*/
	public static final String ftl_description = "ftl_description";
	/** 主键的名称     在 配置文件中给定 po.fieldName == jeecg_table_id*/
	public static final String jeecg_table_id = "jeecg_table_id";
	/** 主键策略 uuid  sequence identity*/
	public static final String jeecg_primary_key_policy = "jeecg_primary_key_policy";
	public static final String primary_key_type = "primary_key_type";
	/** 自增序列名称  由前台传入*/
	public static final String jeecg_sequence_code = "jeecg_sequence_code";
	/** * @date ${ftl_create_time}*/
	public static final String ftl_create_time = "ftl_create_time";
	/** 双表外键*/
	public static final String foreignKeys = "foreignKeys";
	/**应该 属性需要的数目*/
	public static final String field_required_num = "field_required_num";
	/**查找需要的 属性数目*/
	public static final String search_field_num = "search_field_num";
	
	public static final String field_row_num = "field_row_num";
	/**控制生成版本序列*/
	public static final String serialVersionUID = "serialVersionUID";
	
	/**表单配置 是在页面选择后标出到数据库，在生成代码的时候从数据库读取配置数据*/
	public static final String cgformConfig = "cgformConfig";
	/**数据库中 字段的名称  a_b*/
	public static final String fieldMeta = "fieldMeta";
	/**应该是取出来的数据表的 字段名称*/
	public static final String columns = "columns";
	public static final String mainColums = "mainColums";
	public static final String originalColumns = "originalColumns";
	/**分页要显示的字段*/
	public static final String pageColumns = "pageColumns";
	/** 要展示哪些按钮*/
	public static final String buttons = "buttons";
	public static final String buttonSqlMap = "buttonSqlMap";
	/** 子表*/
	public static final String subtables = "subtables";
	public static final String subTab = "subTab";
	/** 就是文件的层级结构   <#if packageStyle == "service"> */
	public static final String packageStyle = "packageStyle";
	
	public static final String subFieldMeta = "subFieldMeta";
	public static final String subFieldMeta1 = "subFieldMeta1";
	
	public static final String subColumnsMap = "subColumnsMap";
	public static final String subPageColumnsMap = "subPageColumnsMap";
	public static final String DefaultEncoding = "UTF-8";
}
