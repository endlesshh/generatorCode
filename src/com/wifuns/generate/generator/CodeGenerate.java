package com.wifuns.generate.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wifuns.constant.Globals;
import com.wifuns.database.WifunsReadTable;
import com.wifuns.entity.config.ExecuteModel;
import com.wifuns.generate.IGenerator;
import com.wifuns.generate.factory.CodeFactory;
import com.wifuns.pojo.Columnt;
import com.wifuns.pojo.CreateFileProperty;
import com.wifuns.util.CodeDateUtils;
import com.wifuns.util.CodeResourceUtil;
import com.wifuns.util.NonceUtils;

import freemarker.template.TemplateException;

public class CodeGenerate implements IGenerator {
	private static final Log log = LogFactory.getLog(CodeGenerate.class);
	private static String entityPackage = "test";
	private static String entityName = "Person";
	private static String tableName = "person";
	private static String ftlDescription = "公告";
	private static String primaryKeyPolicy = "uuid";
	private static String sequenceCode = "";
	private static String[] foreignKeys;
	private List<Columnt> originalColumns = new ArrayList<Columnt>();
	public static int FIELD_ROW_NUM = 1;
	private static CreateFileProperty createFileProperty = new CreateFileProperty();

	private List<Columnt> columns = new ArrayList<Columnt>();
	
	private WifunsReadTable dbFiledUtil = new WifunsReadTable();
	
	
	public Map<String, Object> execute() {
		Map<String,Object> data = new HashMap<String,Object>();
		//传入数据 
		data.put(Globals.entityPackage, entityPackage);
		data.put(Globals.entityName, entityName);
		data.put(Globals.tableName, tableName);
		data.put(Globals.ftl_description, ftlDescription); 
		data.put(Globals.jeecg_primary_key_policy, primaryKeyPolicy);
		data.put(Globals.jeecg_sequence_code, sequenceCode);
		data.put(Globals.ftl_create_time, CodeDateUtils.dateToString(new Date()));
		data.put(Globals.foreignKeys, foreignKeys);
		
		//配置数据
		data.put(Globals.bussiPackage, CodeResourceUtil.bussiPackage);
		data.put(Globals.jeecg_table_id, CodeResourceUtil.JEECG_GENERATE_TABLE_ID);
		data.put(Globals.field_required_num,Integer.valueOf(StringUtils.isNotEmpty(CodeResourceUtil.JEECG_UI_FIELD_REQUIRED_NUM)
						? Integer.parseInt(CodeResourceUtil.JEECG_UI_FIELD_REQUIRED_NUM) : -1));
		data.put(Globals.search_field_num,Integer.valueOf(StringUtils.isNotEmpty(CodeResourceUtil.JEECG_UI_FIELD_SEARCH_NUM)
						? Integer.parseInt(CodeResourceUtil.JEECG_UI_FIELD_SEARCH_NUM) : -1));
		data.put(Globals.field_row_num, Integer.valueOf(FIELD_ROW_NUM));
		long serialVersionUID = NonceUtils.randomLong() + NonceUtils.currentMills();
		data.put(Globals.serialVersionUID, String.valueOf(serialVersionUID));
		
		//数据库数据
		try {
			this.columns = this.dbFiledUtil.readTableColumn(tableName);
			data.put(Globals.columns, this.columns);
			this.originalColumns = this.dbFiledUtil.readOriginalTableColumn(tableName);
			data.put(Globals.originalColumns, this.originalColumns);
			for (Columnt c : this.originalColumns) {
				if (c.getFieldName().toLowerCase().equals(CodeResourceUtil.JEECG_GENERATE_TABLE_ID.toLowerCase())){
					data.put(Globals.primary_key_type, c.getFieldType());
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		
		return data;
	}

	/**
	 * 生成文件
	 */
	public void generateToFile() throws TemplateException, IOException {
		log.info("----wifuns---Code----Generation----[单表模型:" + tableName + "]------- 生成中。。。");

		CodeFactory codeFactory = new CodeFactory();
		codeFactory.setIGenerator(new CodeGenerate());
	
		List<ExecuteModel> generateFiles = new ArrayList<ExecuteModel>();
		//Jsp页面
		if (createFileProperty.isJspFlag()) {
			if ("03".equals(createFileProperty.getJspMode())) {
				ExecuteModel exe = new ExecuteModel("onetomany/jspSubTemplate.ftl", "jspList");
				generateFiles.add(exe);
			} else {
				if ("01".equals(createFileProperty.getJspMode())) {
					ExecuteModel exe = new ExecuteModel("jspTableTemplate.ftl", "jsp");
					generateFiles.add(exe);
				}
				if ("02".equals(createFileProperty.getJspMode())) {
					ExecuteModel exe = new ExecuteModel("jspDivTemplate.ftl", "jsp");
					generateFiles.add(exe); 
				}
				ExecuteModel exe = new ExecuteModel("jspListTemplate.ftl", "jspList");
				generateFiles.add(exe);  
			}
		}
		//服务实现类
		if (createFileProperty.isServiceImplFlag()) {
			ExecuteModel exe = new ExecuteModel("serviceImplTemplate.ftl", "serviceImpl");
			generateFiles.add(exe);  
		}
		//服务接口
		if (createFileProperty.isServiceIFlag()) {
			ExecuteModel exe = new ExecuteModel("serviceITemplate.ftl", "service");
			generateFiles.add(exe);  
		}
		//controller
		if (createFileProperty.isActionFlag()) {
			ExecuteModel exe = new ExecuteModel("controllerTemplate.ftl", "controller");
			generateFiles.add(exe);  
		}
		//实体类
		if (createFileProperty.isEntityFlag()) {
			ExecuteModel exe = new ExecuteModel("entityTemplate.ftl", "entity");
			generateFiles.add(exe);  
		}
 
		codeFactory.invoke(generateFiles);
		
		log.info("----wifuns----Code----Generation-----[单表模型：" + tableName + "]------ 生成完成。。。");
	}

	public static void main(String[] args) throws TemplateException, IOException {
		System.out.println("----wifuns--------- Code------------- Generation -----[单表模型]------- 生成中。。。");
		new CodeGenerate().generateToFile();
		System.out.println("----wifuns--------- Code------------- Generation -----[单表模型]------- 生成完成。。。");
	}
	
	
	//---------------------------------------------构造函数等，不重要的
	public CodeGenerate() {
	}

	public CodeGenerate(String entityPackage, String entityName, String tableName, String ftlDescription,
			CreateFileProperty createFileProperty, int fieldRowNum, String primaryKeyPolicy, String sequenceCode) {
		CodeGenerate.entityName = entityName;
		CodeGenerate.entityPackage = entityPackage;
		CodeGenerate.tableName = tableName;
		CodeGenerate.ftlDescription = ftlDescription;
		CodeGenerate.createFileProperty = createFileProperty;
		FIELD_ROW_NUM = fieldRowNum;
		CodeGenerate.primaryKeyPolicy = primaryKeyPolicy;
		CodeGenerate.sequenceCode = sequenceCode;
	}

	public CodeGenerate(String entityPackage, String entityName, String tableName, String ftlDescription,
			CreateFileProperty createFileProperty, String primaryKeyPolicy, String sequenceCode) {
		CodeGenerate.entityName = entityName;
		CodeGenerate.entityPackage = entityPackage;
		CodeGenerate.tableName = tableName;
		CodeGenerate.ftlDescription = ftlDescription;
		CodeGenerate.createFileProperty = createFileProperty;
		CodeGenerate.primaryKeyPolicy = primaryKeyPolicy;
		CodeGenerate.sequenceCode = sequenceCode;
	}

	public CodeGenerate(String entityPackage, String entityName, String tableName, String ftlDescription,
			CreateFileProperty createFileProperty, String primaryKeyPolicy, String sequenceCode, String[] foreignKeys) {
		CodeGenerate.entityName = entityName;
		CodeGenerate.entityPackage = entityPackage;
		CodeGenerate.tableName = tableName;
		CodeGenerate.ftlDescription = ftlDescription;
		CodeGenerate.createFileProperty = createFileProperty;
		CodeGenerate.primaryKeyPolicy = primaryKeyPolicy;
		CodeGenerate.sequenceCode = sequenceCode;
		CodeGenerate.foreignKeys = foreignKeys;
	}

	static {
		createFileProperty.setActionFlag(true);
		createFileProperty.setServiceIFlag(true);
		createFileProperty.setJspFlag(true);
		createFileProperty.setServiceImplFlag(true);
		createFileProperty.setJspMode("01");
		createFileProperty.setPageFlag(true);
		createFileProperty.setEntityFlag(true);
	}
}
