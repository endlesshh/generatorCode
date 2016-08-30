package code.generate.pathstrategy;

import org.apache.commons.lang.StringUtils;

import code.generate.ICodePathStyle;
import code.generate.enums.CodeType;
import code.util.CodeResourceUtil;
import code.util.CodeStringUtils;

public class NormalStyle implements ICodePathStyle{

	@Override
	public String getPath(String path, String type, String entityPackage, String entityName) {
		StringBuilder str = new StringBuilder();
		if (StringUtils.isNotBlank(type)) {
			CodeType codeType = ((CodeType) Enum.valueOf(CodeType.class, type));
			
			//项目的跟路径
			str.append(path); 
			
			//判断是 html还是java
			if(".java".equals(codeType.getType())){
				entityName = StringUtils.capitalize(entityName);
				str.append("/");
				str.append(CodeResourceUtil.CODEPATH);
				str.append(StringUtils.lowerCase(codeType.getPath()));

				//添加文件夹的路径
				str.append("/");
				str.append(StringUtils.lowerCase(entityPackage));
				str.append("/");
				
				if(codeType.equals(CodeType.IDao) || codeType.equals(CodeType.IService)){
					str.append("I");
				}
				str.append(StringUtils.capitalize(entityName));
			}else{
				String jspName = StringUtils.capitalize(entityName);
				entityName = CodeStringUtils.getInitialSmall(jspName);
				str.append("/");
				str.append(CodeResourceUtil.HTMLPATH);
				str.append(StringUtils.lowerCase(codeType.getPath()));
				str.append(StringUtils.lowerCase(entityPackage));
				str.append("/");
				str.append(StringUtils.lowerCase(entityName));
			} 
			
			
			str.append(codeType.getSuffixName());
			str.append(codeType.getType());
			 
		} else {
			throw new IllegalArgumentException("type is null");
		}
		return str.toString();
	}
}
