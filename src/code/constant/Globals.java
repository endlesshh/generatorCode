package code.constant;

public class Globals {
	/**package ${bussiPackage}.entity.${entityPackage};*/
	public static final String bussiPackage = "bussiPackage";
	public static final String author = "author";
	/**public class ${entityName}Entity */
	public static final String entityName = "entityName";
	/**@Table(name = "${tableName}", schema = "")*/
	public static final String tableName = "tableName";
	/** * @Description: ${ftl_description}*/
	public static final String ftl_description = "ftl_description";
	/** 主键的名称     在 配置文件中给定 po.fieldName == jeecg_table_id*/
	public static final String wifuns_table_id = "wifuns_table_id";

	public static final String primary_key_type = "primary_key_type";
	
	/** * @date ${ftl_create_time}*/
	public static final String ftl_create_time = "ftl_create_time";

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
	
	public static final String DefaultEncoding = "UTF-8";
	
	public static final String DEFAULT_TABLE_SUFFIX = "wifunsshop_";
}
