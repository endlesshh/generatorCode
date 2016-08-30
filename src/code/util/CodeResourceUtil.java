package code.util;

import java.util.ResourceBundle;

/**
 * 工具类，获取配置
 * 1、代码的都是  .和 /
 * 2、页面的都是 /
 * @author Administrator
 *
 */
public class CodeResourceUtil {
	
	private CodeResourceUtil(){ 
	}
	
	
	
	private static final ResourceBundle bundle = ResourceBundle.getBundle("code/wifuns_database");
	private static final ResourceBundle bundlePath = ResourceBundle.getBundle("code/wifuns_config");

	
	
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
	
	/**是否首字母大写*/
	public static boolean WIFUNS_FILED_CONVERT = getWIFUNS_FILED_CONVERT();
	/**模板地址*/
	public static String FREEMARKER_CLASSPATH = "/code/template";

	
	/**
	 * 上面的是 .
	 * 下面的是 /
	 * */
	public static String bussiPackage = getBussiPackage();
	public static String bussiPackageUrl = getbussiPackageUrl();
	
	
	public static String TEMPLATEPATH;
	
	/** 生成页面的跟路径*/
	public static String LAYOUT_PACKAGE = getLayoutPackage();
	
	
	/**代码全跟路径    "/"        			src/main/java/        /    com/wifuns/shop                */
	public static String CODEPATH = getSourceRootPackage() + "/" + bussiPackageUrl;
	/**页面全跟路径    "/"   					src/main/webapp        /     WEB-INF/templates/zh_cn/     */
	public static String HTMLPATH = getWebRootPackage() + "/"  + LAYOUT_PACKAGE;
	
	
	
	
	//实体类中id字段的名称
	public static String WIFUNS_GENERATE_TABLE_ID = getWifuns_generate_table_id();
	//
	public static String WIFUNS_GENERATE_UI_FILTER_FIELDS = getWifuns_generate_ui_filter_fields();
	/**编码格式*/
	public static String SYSTEM_ENCODING;

	
	static {
		DIVER_NAME = getDIVER_NAME();
		URL = getURL();
		USERNAME = getUSERNAME();
		PASSWORD = getPASSWORD();
		DATABASE_NAME = getDATABASE_NAME();
		SYSTEM_ENCODING = getSYSTEM_ENCODING();
		TEMPLATEPATH = getTEMPLATEPATH();
	}
	public static final String getLayoutPackage(){
		String layoutPackage = bundlePath.getString("layout_package");
		if ((layoutPackage != null) && (!"".equals(layoutPackage))) {
			LAYOUT_PACKAGE = layoutPackage;
		}
		return LAYOUT_PACKAGE;
		
	}
	public static final String getWifuns_generate_ui_filter_fields() {
		return bundlePath.getString("ui_filter_fields");
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
	
	public static final String getDIVER_NAME() {
		return bundle.getString("diver_name");
	}

	public static final boolean getWIFUNS_FILED_CONVERT() {
		String s = bundlePath.getString("wifuns_filed_convert");
		if (s.toString().equals("false")) {
			return false;
		}
		return true;
	}

	private static String getBussiPackage() {
		String bussi_package = bundlePath.getString("bussi_package");
		if ((bussi_package != null) && (!"".equals(bussi_package))) {
			bussiPackage = bussi_package;
		} else {
			bussiPackage = "com.wifun.shop";
		}
		return bussiPackage;
		
	}
	private static String getbussiPackageUrl() {
		String bussi_package = bundlePath.getString("bussi_package");
		if ((bussi_package != null) && (!"".equals(bussi_package))) {
			bussiPackage = bussi_package;
		}
		bussiPackage = bussiPackage.replace(".", "/");
		return bussiPackage; 
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
		String sourceRootPackage = bundlePath.getString("source_root_package");
		if ((sourceRootPackage != null) && (!"".equals(sourceRootPackage))) {
			source_root_package = sourceRootPackage;
		}
		source_root_package = source_root_package.replace(".", "/");
		return source_root_package;
	}

	public static final String getWebRootPackage() {
		String webrootPackage = bundlePath.getString("webroot_package");
		if ((webrootPackage != null) && (!"".equals(webrootPackage))) {
			web_root_package = webrootPackage;
		}
		web_root_package = web_root_package.replace(".", "/");
		return web_root_package;
	}

	public static final String getSYSTEM_ENCODING() {
		return bundlePath.getString("system_encoding");
	}

	public static final String getWifuns_generate_table_id() {
		return bundlePath.getString("wifuns_generate_table_id");
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

	
}