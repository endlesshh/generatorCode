package com.wifuns.generate.factory;

import com.wifuns.generate.BaseCodeFactory;
import com.wifuns.generate.ICodePathStyle;
import com.wifuns.generate.pathstrategy.CgFormProjectStyle;
import com.wifuns.generate.pathstrategy.CgFormServiceStyle;
import com.wifuns.util.CodeResourceUtil;

public class CgformCodeFactory extends BaseCodeFactory {
	 
	private String projectPath;  
	
	private ICodePathStyle style; 
	
	@Override
	public String getCodePath(String type, String entityPackage, String entityName) {
		String path = getProjectPath();
		String codePath = "";
		if ((this.packageStyle != null) && (CodeResourceUtil.PACKAGE_SERVICE_STYLE.equals(this.packageStyle))) {
			style = new CgFormServiceStyle();
		} else {
			style = new CgFormProjectStyle();
		}
		codePath = style.getPath(path, type, entityPackage, entityName);
		return codePath;
	} 

	@Override
	public String getClassPath() {
		String path = getClass().getResource("/").getPath();
		return path;
	}
	
	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}
	public String getProjectPath() {
		return this.projectPath;
	}

}