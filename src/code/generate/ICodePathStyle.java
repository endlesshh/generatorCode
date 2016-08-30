package code.generate;

public interface ICodePathStyle {
	/**
	 * 
	 * @Title. getPath  文件路径分割都是以当前系统为准
	 * @Description. 拼装生成文件的路径
	 * @param path
	 * @param type
	 * @param entityPackage
	 * @param entityName
	 * @return String
	 */
	public String getPath(String path, String type, String entityPackage, String entityName);
}
