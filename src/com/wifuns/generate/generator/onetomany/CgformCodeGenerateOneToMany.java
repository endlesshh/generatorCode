package com.wifuns.generate.generator.onetomany;

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
import com.wifuns.entity.button.CgformButtonEntity;
import com.wifuns.entity.config.CgFormFieldEntity;
import com.wifuns.entity.config.ExecuteModel;
import com.wifuns.entity.generate.GenerateEntity;
import com.wifuns.generate.IGenerator;
import com.wifuns.generate.factory.CgformCodeFactoryOneToMany;
import com.wifuns.generate.generator.CgformCodeGenerate;
import com.wifuns.pojo.CreateFileProperty;
import com.wifuns.pojo.onetomany.CodeParamEntity;
import com.wifuns.pojo.onetomany.SubTableEntity;
import com.wifuns.util.CodeDateUtils;
import com.wifuns.util.CodeResourceUtil;
import com.wifuns.util.NonceUtils;
import com.wifuns.util.SqlTypeUtil;

import freemarker.template.TemplateException;

public class CgformCodeGenerateOneToMany implements IGenerator {
	private static final Log log = LogFactory.getLog(CgformCodeGenerateOneToMany.class);

	private String entityPackage = "test";
	private String entityName = "Person";
	private String tableName = "person";
	private String ftlDescription = "用户";
	private String primaryKeyPolicy = "uuid";
	private String sequenceCode = "";
	private static String ftl_mode;
	public static String FTL_MODE_A = "A";
	public static String FTL_MODE_B = "B";

	private static List<SubTableEntity> subTabParam = new ArrayList<SubTableEntity>();
	private static CreateFileProperty createFileProperty = new CreateFileProperty();
	public static int FIELD_ROW_NUM = 4;

	private List<SubTableEntity> subTabFtl = new ArrayList<SubTableEntity>();
	private CodeParamEntity codeParamEntityIn;
	private GenerateEntity mainG;
	private Map<String, GenerateEntity> subsG;
	private List<SubTableEntity> subTabParamIn;

	
	public Map<String, Object> execute() {
		Map<String,Object> data = new HashMap<String,Object>();

		data.put(Globals.bussiPackage, CodeResourceUtil.bussiPackage);
		data.put(Globals.entityPackage, this.entityPackage);
		data.put(Globals.entityName, this.entityName);
		data.put(Globals.tableName, this.tableName);
		data.put(Globals.ftl_description, this.ftlDescription);
		data.put(Globals.jeecg_table_id, CodeResourceUtil.JEECG_GENERATE_TABLE_ID);
		data.put(Globals.jeecg_primary_key_policy, this.primaryKeyPolicy);
		data.put(Globals.jeecg_sequence_code, this.sequenceCode);
		data.put(Globals.ftl_create_time, CodeDateUtils.dateToString(new Date()));
		data.put(Globals.field_required_num,
				Integer.valueOf(StringUtils.isNotEmpty(CodeResourceUtil.JEECG_UI_FIELD_REQUIRED_NUM)
						? Integer.parseInt(CodeResourceUtil.JEECG_UI_FIELD_REQUIRED_NUM) : -1));
		data.put(Globals.search_field_num,
				Integer.valueOf(StringUtils.isNotEmpty(CodeResourceUtil.JEECG_UI_FIELD_SEARCH_NUM)
						? Integer.parseInt(CodeResourceUtil.JEECG_UI_FIELD_SEARCH_NUM) : -1));
		data.put(Globals.field_row_num, Integer.valueOf(FIELD_ROW_NUM));
		long serialVersionUID = NonceUtils.randomLong() + NonceUtils.currentMills();
		data.put(Globals.serialVersionUID, String.valueOf(serialVersionUID));
		
		try {
			Map<String,Object> fieldMeta = new HashMap<String,Object>();
			List<CgFormFieldEntity> columns = this.mainG.deepCopy().getCgFormHead().getColumns();

			for (CgFormFieldEntity cf : columns) {
				String type = cf.getType();
				cf.setType(SqlTypeUtil.getType(type));  
				String fieldName = cf.getFieldName();
				String fieldNameV = WifunsReadTable.formatField(fieldName);
				cf.setFieldName(fieldNameV);
				fieldMeta.put(fieldNameV, fieldName.toUpperCase());
			}
			List<CgFormFieldEntity> pageColumns = new ArrayList<CgFormFieldEntity>();
			for (CgFormFieldEntity cf : columns) {
				if ((StringUtils.isNotEmpty(cf.getIsShow())) && ("Y".equalsIgnoreCase(cf.getIsShow()))) {
					pageColumns.add(cf);
				}
			}
			String[] subtables = this.mainG.getCgFormHead().getSubTableStr().split(",");

			data.put(Globals.cgformConfig, this.mainG);
			data.put(Globals.fieldMeta, fieldMeta);
			data.put(Globals.columns, columns);
			data.put(Globals.pageColumns, pageColumns);
			data.put(Globals.buttons, this.mainG.getButtons() == null ? new ArrayList<CgformButtonEntity>() : this.mainG.getButtons());
			data.put(Globals.buttonSqlMap,
					this.mainG.getButtonSqlMap() == null ? new HashMap<String, String[]>() : this.mainG.getButtonSqlMap());
			data.put(Globals.subtables, subtables);
			data.put(Globals.subTab, this.subTabParamIn);

			Map<String,Object> subColumnsMap = new HashMap<String,Object>();
			Map<String,Object> subPageColumnsMap = new HashMap<String,Object>();
			Map<String,Object> subFieldMeta = new HashMap<String,Object>();
			Map<String,Object> subFieldMeta1 = new HashMap<String,Object>();
			for (String key : this.subsG.keySet()) {
				GenerateEntity subG = (GenerateEntity) this.subsG.get(key);
				List<CgFormFieldEntity> subColumns = subG.deepCopy().getCgFormHead().getColumns();
				List<CgFormFieldEntity> subPageColumns = new ArrayList<CgFormFieldEntity>();
				for (CgFormFieldEntity cf : subColumns) {
					String type = cf.getType();
					cf.setType(SqlTypeUtil.getType(type)); 
					String fieldName = cf.getFieldName();
					String fieldNameV = WifunsReadTable.formatField(fieldName);
					cf.setFieldName(fieldNameV);
					subFieldMeta.put(fieldNameV, fieldName.toUpperCase());
					subFieldMeta1.put(fieldName.toUpperCase(), fieldNameV);
					if ((StringUtils.isNotEmpty(cf.getIsShow())) && ("Y".equalsIgnoreCase(cf.getIsShow()))) {
						subPageColumns.add(cf);
					}
					String mtable = cf.getMainTable();
					String mfiled = cf.getMainField();
					if ((mtable != null) && (mtable.equalsIgnoreCase(this.mainG.getTableName()))) {
						data.put(key + "_fk", mfiled);
					}
					subColumnsMap.put(key, subColumns);
					subPageColumnsMap.put(key, subPageColumns);
				}
				data.put(Globals.subColumnsMap, subColumnsMap);
				data.put(Globals.subPageColumnsMap, subPageColumnsMap);
				data.put(Globals.subFieldMeta, subFieldMeta);
				data.put(Globals.subFieldMeta1, subFieldMeta1);
				data.put(Globals.packageStyle, this.mainG.getPackageStyle());
			}
		} catch (Exception e) {
			 
			e.printStackTrace();
		} 
		return data;
	}

	public void generateToFile() throws TemplateException, IOException {
		CgformCodeFactoryOneToMany codeFactoryOneToMany = new CgformCodeFactoryOneToMany();
		codeFactoryOneToMany.setProjectPath(this.mainG.getProjectPath());
		codeFactoryOneToMany.setPackageStyle(this.mainG.getPackageStyle());
		codeFactoryOneToMany.setIGenerator(
				new CgformCodeGenerateOneToMany(this.subTabParamIn, this.codeParamEntityIn, this.mainG, this.subsG));
		List<ExecuteModel> generateFiles = new ArrayList<ExecuteModel>();
		if (createFileProperty.isJspFlag()) {
			ExecuteModel exe = new ExecuteModel("onetomany/cgform_jspListTemplate.ftl", "jspList");
			generateFiles.add(exe);  
			if ("06".equals(createFileProperty.getJspMode())) {
				ExecuteModel exec = new ExecuteModel("onetomany/cgform_jspBootstrapTemplate_add.ftl", "jsp_add");
				generateFiles.add(exec); 
				ExecuteModel exec1 = new ExecuteModel("onetomany/cgform_jspBootstrapTemplate_update.ftl", "jsp_update");
				generateFiles.add(exec1); 
			} else {
				ExecuteModel exec = new ExecuteModel("onetomany/cgform_jspTemplate_add.ftl", "jsp_add");
				generateFiles.add(exec); 
				ExecuteModel exec1 = new ExecuteModel("onetomany/cgform_jspTemplate_update.ftl", "jsp_update");
				generateFiles.add(exec1); 
				
			}
			ExecuteModel exec = new ExecuteModel("onetomany/cgform_jsEnhanceTemplate.ftl", "js");
			generateFiles.add(exec); 
			ExecuteModel exec1 = new ExecuteModel("onetomany/cgform_jsListEnhanceTemplate.ftl", "jsList");
			generateFiles.add(exec1); 
		}
		if (createFileProperty.isServiceImplFlag()) {
			ExecuteModel exe = new ExecuteModel("onetomany/cgform_serviceImplTemplate.ftl", "serviceImpl");
			generateFiles.add(exe);  
		}
		if (createFileProperty.isServiceIFlag()) {
			ExecuteModel exe = new ExecuteModel("onetomany/cgform_serviceITemplate.ftl", "service");
			generateFiles.add(exe);  
		}
		if (createFileProperty.isActionFlag()) {
			ExecuteModel exe = new ExecuteModel("onetomany/cgform_controllerTemplate.ftl", "controller");
			generateFiles.add(exe);  
		}
		if (createFileProperty.isEntityFlag()) {
			ExecuteModel exe = new ExecuteModel("onetomany/cgform_entityTemplate.ftl", "entity");
			generateFiles.add(exe);  
		}
		if (createFileProperty.isPageFlag()) {
			ExecuteModel exe = new ExecuteModel("onetomany/cgform_pageTemplate.ftl", "page");
			generateFiles.add(exe);  
		}
		
		codeFactoryOneToMany.invoke(generateFiles);
	}

	public static void oneToManyCreate(List<SubTableEntity> subTabParamIn, CodeParamEntity codeParamEntityIn,
			GenerateEntity mainG, Map<String, GenerateEntity> subsG) throws TemplateException, IOException {
		log.info("----jeecg----Code-----Generation-----[一对多数据模型：" + codeParamEntityIn.getTableName()
				+ "]------- 生成中。。。");

		CreateFileProperty subFileProperty = new CreateFileProperty();
		subFileProperty.setActionFlag(false);
		subFileProperty.setServiceIFlag(false);
		subFileProperty.setJspFlag(true);
		subFileProperty.setServiceImplFlag(false);
		subFileProperty.setPageFlag(false);
		subFileProperty.setEntityFlag(true);
		subFileProperty.setJspMode("03");

		for (SubTableEntity sub : subTabParamIn) {
			String[] foreignKeys = sub.getForeignKeys();
			GenerateEntity subG = (GenerateEntity) subsG.get(sub.getTableName());
			new CgformCodeGenerate(sub, subG, subFileProperty, "uuid", foreignKeys).generateToFile();
		}

		new CgformCodeGenerateOneToMany(subTabParamIn, codeParamEntityIn, mainG, subsG).generateToFile();
		log.info("----jeecg----Code----Generation------[一对多数据模型：" + codeParamEntityIn.getTableName()
				+ "]------ 生成完成。。。");
	}

	public static void oneToManyCreateBootstap(List<SubTableEntity> subTabParamIn, CodeParamEntity codeParamEntityIn,
			GenerateEntity mainG, Map<String, GenerateEntity> subsG) throws TemplateException, IOException {
		log.info("----jeecg----Code-----Generation-----[一对多数据模型：" + codeParamEntityIn.getTableName()
				+ "]------- 生成中。。。");

		CreateFileProperty subFileProperty = new CreateFileProperty();
		subFileProperty.setActionFlag(false);
		subFileProperty.setServiceIFlag(false);
		subFileProperty.setJspFlag(true);
		subFileProperty.setServiceImplFlag(false);
		subFileProperty.setPageFlag(false);
		subFileProperty.setEntityFlag(true);
		subFileProperty.setJspMode("06");

		for (SubTableEntity sub : subTabParamIn) {
			String[] foreignKeys = sub.getForeignKeys();
			GenerateEntity subG = (GenerateEntity) subsG.get(sub.getTableName());
			new CgformCodeGenerate(sub, subG, subFileProperty, "uuid", foreignKeys).generateToFile();
		}

		createFileProperty.setJspMode("06");
		new CgformCodeGenerateOneToMany(subTabParamIn, codeParamEntityIn, mainG, subsG).generateToFile();
		log.info("----jeecg----Code----Generation------[一对多数据模型：" + codeParamEntityIn.getTableName()
				+ "]------ 生成完成。。。");
	}
	public CgformCodeGenerateOneToMany() {
	}

	public CgformCodeGenerateOneToMany(List<SubTableEntity> subTabParamIn, CodeParamEntity codeParamEntityIn,
			GenerateEntity mainG, Map<String, GenerateEntity> subsG) {
		this.entityName = codeParamEntityIn.getEntityName();
		this.entityPackage = codeParamEntityIn.getEntityPackage();
		this.tableName = codeParamEntityIn.getTableName();
		this.ftlDescription = codeParamEntityIn.getFtlDescription();
		subTabParam = codeParamEntityIn.getSubTabParam();
		ftl_mode = codeParamEntityIn.getFtl_mode();
		this.primaryKeyPolicy = "uuid";
		this.sequenceCode = codeParamEntityIn.getSequenceCode();
		this.subTabParamIn = subTabParamIn;
		this.mainG = mainG;
		this.subsG = subsG;
		this.codeParamEntityIn = codeParamEntityIn;
	}

	static {
		createFileProperty.setActionFlag(true);
		createFileProperty.setServiceIFlag(true);
		createFileProperty.setJspFlag(true);
		createFileProperty.setServiceImplFlag(true);
		createFileProperty.setPageFlag(true);
		createFileProperty.setEntityFlag(true);
	}
}
