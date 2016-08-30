package code.generate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import code.constant.Globals;
import code.entity.config.ExecuteModel;
import code.util.CodeResourceUtil;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class BaseCodeFactory {
	/**生成文件的结构样式*/
	protected String packageStyle;
	/**对应的各自的代码生成器*/
	protected IGenerator generator;
	
	
	/**
	 * 根据模板生成文件
	 * @param templateFileName
	 * @param type
	 * @param data
	 * @throws TemplateException
	 * @throws IOException
	 */
	public void generateFile(String templateFileName, String type,String entityPackage, Map<String,Object> data) throws TemplateException, IOException {
		try {
			
			String entityName = data.get(Globals.entityName).toString();
			String fileNamePath = getCodePath(type, entityPackage, entityName);
			String fileDir = StringUtils.substringBeforeLast(fileNamePath, "/");
			Template template = getConfiguration().getTemplate(templateFileName);

			FileUtils.forceMkdir(new File(fileDir + "/"));
			Writer out = new OutputStreamWriter(new FileOutputStream(fileNamePath), CodeResourceUtil.SYSTEM_ENCODING);

			template.process(data, out);
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * 根据html生成页面
	 * @param html
	 * @param type
	 * @throws TemplateException
	 * @throws IOException
	 */
	public void generateHtml2File(String html, String type,String entityPackage,Map<String,Object> data) throws TemplateException, IOException {
		try {
			String entityName = data.get(Globals.entityName).toString();
			String fileNamePath = getCodePath(type, entityPackage, entityName);
			String fileDir = StringUtils.substringBeforeLast(fileNamePath, "/");
			FileUtils.forceMkdir(new File(fileDir + "/"));
			Configuration cfg = new Configuration();
			StringTemplateLoader loader = new StringTemplateLoader();
			loader.putTemplate(entityName, html);
			cfg.setTemplateLoader(loader);
			cfg.setDefaultEncoding(Globals.DefaultEncoding);
			Template template = cfg.getTemplate(entityName);
			FileUtils.forceMkdir(new File(fileDir + "/"));
			Writer out = new OutputStreamWriter(new FileOutputStream(fileNamePath), CodeResourceUtil.SYSTEM_ENCODING);
			template.process(data, out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	} 
	
	/**
	 * 确定生成代码的风格
	 * @param type
	 * @param entityPackage
	 * @param entityName
	 * @return
	 */
	public abstract String getCodePath(String type, String entityPackage, String entityName);
	
	
	/**
	 * 生成文件
	 * @param templateFileName
	 * @param type
	 * @throws TemplateException
	 * @throws IOException
	 */
	public void invoke(List<ExecuteModel> executes) throws TemplateException, IOException {
		if(executes != null && executes.size()>0){
			Map<String,Object> data = new HashMap<String,Object>();
			data = this.generator.execute();
			for(ExecuteModel exe : executes){ 
				generateFile(exe.getTemplateName(),exe.getStyle(),exe.getEntityPackage(), data);
			} 	
		} 
	}
	
	public void invokeNotFlt(List<ExecuteModel> executes) throws TemplateException, IOException {
		if(executes != null && executes.size()>0){
			Map<String,Object> data = new HashMap<String,Object>();
			data = this.generator.execute();
			for(ExecuteModel exe : executes){ 
				generateHtml2File(exe.getTemplateName(),exe.getStyle(),exe.getEntityPackage(), data);
			} 	
		} 
	}

	/**
	 * 获取模板路径 生成代码时使用的模板
	 * @return
	 */
	public String getTemplatePath() {
		String path = getClassPath() + CodeResourceUtil.TEMPLATEPATH;
		return path;
	}
	
	/**
	 * 不同工厂方法获取的方式不一样
	 * @return
	 */
	public abstract String getClassPath();
	
	
	
	public Configuration getConfiguration() throws IOException {
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(getClass(), CodeResourceUtil.FREEMARKER_CLASSPATH);
		cfg.setLocale(Locale.CHINA);
		cfg.setDefaultEncoding(Globals.DefaultEncoding);
		return cfg;
	}

	
	//-------------------------get set 方法
	
	public String getPackageStyle() {
		return this.packageStyle;
	}

	public void setPackageStyle(String packageStyle) {
		this.packageStyle = packageStyle;
	}

	public IGenerator getIGenerator() {
		return this.generator;
	}

	public void setIGenerator(IGenerator generator) {
		this.generator = generator;
	}
	
}