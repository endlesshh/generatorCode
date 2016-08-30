package com.wifuns.generate.factory;

import com.wifuns.generate.BaseCodeFactory;
import com.wifuns.generate.ICodePathStyle;
import com.wifuns.generate.pathstrategy.NormalStyle;

public class CodeFactory extends BaseCodeFactory {
	
	private ICodePathStyle style  = new NormalStyle();
	
	public static String getProjectPath() {
		String path = System.getProperty("user.dir").replace("\\", "/") + "/";
		return path;
	}

	public String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("./").getPath();
		return path;
	}

	public String getCodePath(String type, String entityPackage, String entityName) { 
		return style.getPath(getProjectPath(), type, entityPackage, entityName);
	}  

	public static void main(String[] args) {
		CodeFactory f = new CodeFactory();
		System.out.println(f.getCodePath("jsp","www","ss"));
	}  
}