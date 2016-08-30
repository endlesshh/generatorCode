package com.wifuns.generate.pathstrategy;

import org.apache.commons.lang.StringUtils;

import com.wifuns.generate.ICodePathStyle;
import com.wifuns.generate.enums.CodeType;
import com.wifuns.util.CodeResourceUtil;
import com.wifuns.util.CodeStringUtils;

public class CgFormServiceStyle implements ICodePathStyle{

	@Override
	public String getPath(String path, String type, String entityPackage, String entityName) {
		StringBuilder str = new StringBuilder();
		if (StringUtils.isNotBlank(type)) {
			String codeType = ((CodeType) Enum.valueOf(CodeType.class, type)).getValue();
			str.append(path);
			if (("jsp".equals(type)) || ("jspList".equals(type)) || ("js".equals(type)) || ("jsList".equals(type))
					|| ("jsp_add".equals(type)) || ("jsp_update".equals(type))) {
				str.append(CodeResourceUtil.JSPPATH);
			} else{
				str.append(CodeResourceUtil.CODEPATH);
			}

			str.append(StringUtils.lowerCase(entityPackage));
			str.append("/");
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

			if (("jsp".equals(type)) || ("jspList".equals(type))) {
				String jspName = StringUtils.capitalize(entityName);

				str.append(CodeStringUtils.getInitialSmall(jspName));
				str.append(codeType);
				str.append(".jsp");
			} else if (("jsp_add".equals(type)) || ("jspList_add".equals(type))) {
				String jsName = StringUtils.capitalize(entityName);

				str.append(CodeStringUtils.getInitialSmall(jsName));
				str.append(codeType);
				str.append("-add.jsp");
			} else if (("jsp_update".equals(type)) || ("jspList_update".equals(type))) {
				String jsName = StringUtils.capitalize(entityName);

				str.append(CodeStringUtils.getInitialSmall(jsName));
				str.append(codeType);
				str.append("-update.jsp");
			} else if (("js".equals(type)) || ("jsList".equals(type))) {
				String jsName = StringUtils.capitalize(entityName);

				str.append(CodeStringUtils.getInitialSmall(jsName));
				str.append(codeType);
				str.append(".js");
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
