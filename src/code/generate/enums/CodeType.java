package code.generate.enums;
/**
 * 
 * @ClassName. CodeType
 * @Description. 数据结构： enum的名称	 文件类型    生成文件要拼接的名称   	文件在基本根目录下的目录
 * 					比如：   IService  	Java	  a  aIService     (com/wifuns/shop/) /foundation
 * @author ShiQiang
 * @date. 2016年8月27日 上午10:17:38
 * @version V1.0
 */
public enum CodeType {
	//服务层
	IService("IService",".java","Service","/foundation"),
	serviceImpl("serviceImpl",".java","ServiceImpl","/foundation"), 
	// dao
	IDao("IDao",".java","DAO","/foundation"), 
	daoImpl("daoImpl",".java","DAO","/foundation"),
	//控制类
	adminAction("adminAction",".java","ManageAction","/manage"), 
	
	
	//实体类
	domain("domain",".java","","/foundation"),
	query("query",".java","QueryObject","/foundation"),
	
	//页面
	htmlView("htmlView",".html","_view","/"), 
	htmlAdd("htmlAdd",".html","_add","/"), 
	htmlEdit("htmlEdit",".html","_edit","/"), 
	htmlList("htmlList",".html","_list","/");

	//类型
	private String type;
	//后缀名称
	private String suffixName;
	//生成文件的路径
	private String path;
	//当前数据名称
	private String enumName;
	
	private CodeType(String enumName,String type,String suffixName,String path) {
		this.enumName = enumName;
		this.type = type;
		this.suffixName = suffixName;
		this.path = path;
	}

	public String getType() {
		return this.type;
	}
	public String getEnumName() {
		return this.enumName;
	}
	public String getSuffixName() {
		return this.suffixName;
	}
	public String getPath() {
		return this.path;
	}
	
	
	public static void main(String[] args) {
		String type = ((CodeType) Enum.valueOf(CodeType.class,"IService")).getType();
		String name = ((CodeType) Enum.valueOf(CodeType.class,"IService")).getSuffixName();
		CodeType path = ((CodeType) Enum.valueOf(CodeType.class,"IService"));
		System.out.println(name);
		System.out.println(type);
		System.out.println(path.enumName);
	}
}