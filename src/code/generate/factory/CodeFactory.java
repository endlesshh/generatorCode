package code.generate.factory;

import code.util.CodeResourceUtil;
import code.generate.BaseCodeFactory;
import code.generate.ICodePathStyle;
import code.generate.pathstrategy.NormalStyle;

public class CodeFactory extends BaseCodeFactory {
	
	private ICodePathStyle style  = new NormalStyle();
	
	/**
	 * 
	 * @Title. getProjectPath
	 * @Description. 获取项目的跟路径  E:/JKD6Eclipse1/shop-new/
	 * @return String
	 */
	public String getProjectPath() {
		String path = System.getProperty("user.dir").replace("\\", "/") + "/";
		//String path = CodeResourceUtil.getProject_path();
		return path;
	}
	// E:/JKD6Eclipse1/shop-new/target/test-classes/
	public String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("./").getPath();
		return path;
	}

	public String getCodePath(String type, String entityPackage, String entityName) { 
		return style.getPath(getProjectPath(), type, entityPackage, entityName);
	}  
	public static void main(String[] args) {
		NormalStyle style = new NormalStyle();
		CodeFactory f = new CodeFactory();
		System.out.println(style.getPath(f.getProjectPath(),"IDao","a/b","b"));
		System.out.println(style.getPath(f.getProjectPath(),"html_list","a/b","b"));
	}
	
}