package code.pojo;

import org.apache.commons.lang.StringUtils;
/**
 * 读取数据库信息时根据数据库语法来设置相应的字段的属性值
 * @author Administrator
 *
 */
public class TableConvert {
	public static String getNullAble(String nullable) {
		if (("YES".equals(nullable)) || ("yes".equals(nullable)) || ("y".equals(nullable)) || ("Y".equals(nullable))
				|| ("f".equals(nullable))) {
			return "Y";
		}
		if (("NO".equals(nullable)) || ("N".equals(nullable)) || ("no".equals(nullable)) || ("n".equals(nullable))
				|| ("t".equals(nullable))) {
			return "N";
		}
		return null;
	}

	public static String getNullString(String nullable) {
		if (StringUtils.isBlank(nullable)) {
			return "";
		}
		return nullable;
	}

	public static String getV(String s) {
		return "'" + s + "'";
	}
}