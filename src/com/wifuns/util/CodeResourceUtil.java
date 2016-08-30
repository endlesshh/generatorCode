package com.wifuns.util;

import java.util.ResourceBundle;

/**
 * 工具类，获取配置
 * @author Administrator
 *
 */
public class CodeResourceUtil {
	
	private CodeResourceUtil(){ 
	}
	
	private static final ResourceBundle bundle = ResourceBundle.getBundle("wifuns/wifuns_database");
	private static final ResourceBundle bundlePath = ResourceBundle.getBundle("wifuns/wifuns_config");

	
	
	/**
	 * ----------数据库默认配置--------------
	 * */
	public static String DIVER_NAME = "com.mysql.jdbc.Driver";
	public static String URL = "jdbc:mysql://localhost:3306/sys?useUnicode=true&characterEncoding=UTF-8";
	public static String USERNAME = "root";
	public static String PASSWORD = "root";
	public static String DATABASE_NAME = "sys";
	public static String DATABASE_TYPE = "mysql";
	public static String JEECG_UI_FIELD_REQUIRED_NUM = "4";
	public static String JEECG_UI_FIELD_SEARCH_NUM = "3"; 
	/**
	 * ----------项目相关默认配置--------------
	 * */ 
	/**项目的路径*/
	public static String project_path = "c:/workspace/jeecg";
	/**web项目的请求路径**/
	public static String web_root_package = "WebRoot";
	/**核心资源路径*/
	public static String source_root_package = "src"; 
	public static String bussiPackage = "sun";
	/**实体类文件夹名称*/
	public static String entity_package = "entity"; 
	public static String page_package = "page"; 
	/**是否进行属性转化*/
	public static boolean JEECG_FILED_CONVERT = true;
	/**模板地址*/
	public static String FREEMARKER_CLASSPATH = "/wifuns/template";

	public static String PACKAGE_SERVICE_STYLE = "service";
	public static String PACKAGE_PROJECT_STYLE = "project";
	
	public static String bussiPackageUrl = "";
	/**实体类地址*/
	public static String ENTITY_URL = source_root_package + "/" + bussiPackageUrl + "/" + entity_package + "/";
	/**page不知道是什么*/
	public static String PAGE_URL = source_root_package + "/" + bussiPackageUrl + "/" + page_package + "/";

	public static String ENTITY_URL_INX = bussiPackage + "." + entity_package + ".";
	public static String PAGE_URL_INX = bussiPackage + "." + page_package + ".";
	public static String TEMPLATEPATH;
	/**代码路径*/
	public static String CODEPATH = source_root_package + "/" + bussiPackageUrl + "/";
	/**页面路径*/
	public static String JSPPATH = web_root_package + "/" + "webpage" + "/" + bussiPackageUrl + "/";
	//实体类中id字段的名称
	public static String JEECG_GENERATE_TABLE_ID;
	public static String JEECG_GENERATE_UI_FILTER_FIELDS;
	/**编码格式*/
	public static String SYSTEM_ENCODING;

	

	public static final String getDIVER_NAME() {
		return bundle.getString("diver_name");
	}

	public static final String getURL() {
		return bundle.getString("url");
	}

	public static final String getUSERNAME() {
		return bundle.getString("username");
	}

	public static final String getPASSWORD() {
		return bundle.getString("password");
	}

	public static final String getDATABASE_NAME() {
		return bundle.getString("database_name");
	}

	public static final boolean getJEECG_FILED_CONVERT() {
		String s = bundlePath.getString("jeecg_filed_convert");
		if (s.toString().equals("false")) {
			return false;
		}
		return true;
	}

	private static String getBussiPackage() {
		return bundlePath.getString("bussi_package");
	}

	public static final String getEntityPackage() {
		return bundlePath.getString("entity_package");
	}

	public static final String getPagePackage() {
		return bundlePath.getString("page_package");
	}

	public static final String getTEMPLATEPATH() {
		return bundlePath.getString("templatepath");
	}

	public static final String getSourceRootPackage() {
		return bundlePath.getString("source_root_package");
	}

	public static final String getWebRootPackage() {
		return bundlePath.getString("webroot_package");
	}

	public static final String getSYSTEM_ENCODING() {
		return bundlePath.getString("system_encoding");
	}

	public static final String getJeecg_generate_table_id() {
		return bundlePath.getString("jeecg_generate_table_id");
	}

	public static final String getJeecg_generate_ui_filter_fields() {
		return bundlePath.getString("ui_filter_fields");
	}

	public static final String getJeecg_ui_search_filed_num() {
		return bundlePath.getString("jeecg_ui_search_filed_num");
	}

	public static final String getJeecg_ui_field_required_num() {
		return bundlePath.getString("jeecg_ui_field_required_num");
	}

	public static String getProject_path() {
		String projp = bundlePath.getString("project_path");
		if ((projp != null) && (!"".equals(projp))) {
			project_path = projp;
		}
		return project_path;
	}

	public static void setProject_path(String project_path) {
		CodeResourceUtil.project_path = project_path;
	}

	static {
		DIVER_NAME = getDIVER_NAME();
		URL = getURL();
		USERNAME = getUSERNAME();
		PASSWORD = getPASSWORD();
		DATABASE_NAME = getDATABASE_NAME();
		JEECG_FILED_CONVERT = getJEECG_FILED_CONVERT();

		SYSTEM_ENCODING = getSYSTEM_ENCODING();
		TEMPLATEPATH = getTEMPLATEPATH();
		source_root_package = getSourceRootPackage();
		web_root_package = getWebRootPackage();
		bussiPackage = getBussiPackage();

		JEECG_GENERATE_TABLE_ID = getJeecg_generate_table_id();

		JEECG_GENERATE_UI_FILTER_FIELDS = getJeecg_generate_ui_filter_fields();

		JEECG_UI_FIELD_SEARCH_NUM = getJeecg_ui_search_filed_num();

		if ((URL.indexOf("mysql") >= 0) || (URL.indexOf("MYSQL") >= 0)){
			DATABASE_TYPE = "mysql";
		}else if ((URL.indexOf("oracle") >= 0) || (URL.indexOf("ORACLE") >= 0)){
			DATABASE_TYPE = "oracle";
		}else if ((URL.indexOf("postgresql") >= 0) || (URL.indexOf("POSTGRESQL") >= 0)) {
			DATABASE_TYPE = "postgresql";
		} else if ((URL.indexOf("sqlserver") >= 0) || (URL.indexOf("sqlserver") >= 0)) {
			DATABASE_TYPE = "sqlserver";
		}

		source_root_package = source_root_package.replace(".", "/");
		web_root_package = web_root_package.replace(".", "/");
		CodeResourceUtil.bussiPackageUrl = bussiPackage.replace(".", "/");
	}
}