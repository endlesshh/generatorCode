package com.wifuns.generate.pathstrategy;

import org.apache.commons.lang.StringUtils;

import com.wifuns.generate.ICodePathStyle;
import com.wifuns.generate.enums.CodeType;
import com.wifuns.util.CodeResourceUtil;
import com.wifuns.util.CodeStringUtils;

public class NormalStyle implements ICodePathStyle{

	@Override
	public String getPath(String path, String type, String entityPackage, String entityName) {
		StringBuilder str = new StringBuilder();
		if (StringUtils.isNotBlank(type)) {
			String codeType = ((CodeType) Enum.valueOf(CodeType.class, type)).getValue();
			str.append(path);
			if (("jsp".equals(type)) || ("jspList".equals(type)))
				str.append(CodeResourceUtil.JSPPATH);
			else {
				str.append(CodeResourceUtil.CODEPATH);
			}
			if ("Action".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase("action"));
			}else if ("ServiceImpl".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase("service/impl"));
			}else if ("ServiceI".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase("service"));
			}else if (!"List".equalsIgnoreCase(codeType)) {
				str.append(StringUtils.lowerCase(codeType));
			}
			str.append("/");
			str.append(StringUtils.lowerCase(entityPackage));
			str.append("/");

			if (("jsp".equals(type)) || ("jspList".equals(type))) {
				String jspName = StringUtils.capitalize(entityName);

				str.append(CodeStringUtils.getInitialSmall(jspName));
				str.append(codeType);
				str.append(".jsp");
			} else {
				str.append(StringUtils.capitalize(entityName));
				str.append(codeType);
				str.append(".java");
			}
		} else {
			throw new IllegalArgumentException("type is null");
		}
		return str.toString();
	}

}
