package code.generate;

import java.io.IOException;
import java.util.Map;

import freemarker.template.TemplateException;
/**
 * 
 * @author ShiQiang
 */
public abstract interface IGenerator {
	/**
	 * 准备要使用的数据
	 * @return
	 */
	public abstract Map<String, Object> execute();
	/**
	 * 生成相应的模板
	 * @throws TemplateException
	 * @throws IOException
	 */
	public void generateToFile() throws TemplateException,IOException;
}
