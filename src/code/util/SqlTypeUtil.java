package code.util;

public class SqlTypeUtil {
	public static String getType(String type){
		String javaType = "";
		if ("string".equalsIgnoreCase(type)){
			javaType = "java.lang.String";
		}else if ("Date".equalsIgnoreCase(type)){
			javaType = "java.util.Date";
		}else if ("double".equalsIgnoreCase(type)){
			javaType = "java.lang.Double";
		}else if ("int".equalsIgnoreCase(type)){
			javaType = "java.lang.Integer";
		}else if ("BigDecimal".equalsIgnoreCase(type)){
			javaType = "java.math.BigDecimal";
		}else if ("Text".equalsIgnoreCase(type)){
			javaType = "java.lang.String";
		}else if ("Blob".equalsIgnoreCase(type)) {
			javaType = "java.sql.Blob";
		}
		return javaType;
	}
}
