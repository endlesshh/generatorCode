package code.entity.config;


/***
 * 工厂方法根据该类的 模板名称和风格进行生成
 * @author ShiQiang
 *
 */
public class ExecuteModel {
	/**
	 * 模板名称
	 */
	private String templateName;
	/**
	 * 类型名称如：jsp,service等
	 */
	private String style;
	/**
	 * 生成文件的 最终层目录
	 * 如： dao  dao/impl
	 */
	private String entityPackage;
	
	
	public ExecuteModel(String templateName,String style,String entityPackage){
		this.templateName = templateName;
		this.style = style;
		this.entityPackage = entityPackage;
	}
	
	public String getEntityPackage() {
		return entityPackage;
	}

	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}




	public void setTemplateName(String templateName){
		this.templateName = templateName;
	}
	public String getTemplateName(){
		return this.templateName;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	} 
	
}
