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
import com.wifuns.entity.config.CgFormFieldEntity;
import com.wifuns.entity.config.ExecuteModel;
import com.wifuns.entity.generate.GenerateEntity;
import com.wifuns.generate.IGenerator;
import com.wifuns.generate.factory.CgformCodeFactory;
import com.wifuns.pojo.CreateFileProperty;
import com.wifuns.pojo.onetomany.SubTableEntity;
import com.wifuns.util.CodeDateUtils;
import com.wifuns.util.CodeResourceUtil;
import com.wifuns.util.NonceUtils;
import com.wifuns.util.SqlTypeUtil;

import freemarker.template.TemplateException;

public class CgformCodeGenerate implements IGenerator {
	private static final Log log = LogFactory.getLog(CgformCodeGenerate.class);

	private String entityPackage = "test";
	private String entityName = "Person";
	private String tableName = "person";
	private String ftlDescription = "公告";
	private String primaryKeyPolicy = "uuid";
	private String sequenceCode = "";
	private String[] foreignKeys;
	public static int FIELD_ROW_NUM = 1;
	private String cgformJspHtml;
	private SubTableEntity sub;
	private GenerateEntity subG;
	private CreateFileProperty subFileProperty;
	private String policy;
	private String[] array;
	private GenerateEntity cgformConfig;
	private static CreateFileProperty createFileProperty = new CreateFileProperty(); 
	
	
	public Map<String, Object> execute() {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> fieldMeta = new HashMap<String, Object>();

		data.put(Globals.bussiPackage, CodeResourceUtil.bussiPackage);
		data.put(Globals.entityPackage, this.entityPackage);
		data.put(Globals.entityName, this.entityName);
		data.put(Globals.tableName, this.tableName);
		data.put(Globals.ftl_description, this.ftlDescription);
		data.put(Globals.jeecg_table_id, CodeResourceUtil.JEECG_GENERATE_TABLE_ID);
		data.put(Globals.jeecg_primary_key_policy, this.primaryKeyPolicy);
		data.put(Globals.jeecg_sequence_code, this.sequenceCode);
		data.put(Globals.ftl_create_time, CodeDateUtils.dateToString(new Date()));
		data.put(Globals.foreignKeys, this.foreignKeys);
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
			List<CgFormFieldEntity> columns = this.cgformConfig.deepCopy().getCgFormHead().getColumns();

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

			data.put(Globals.cgformConfig, this.cgformConfig);
			data.put(Globals.fieldMeta, fieldMeta);
			data.put(Globals.columns, columns);
			data.put(Globals.pageColumns, pageColumns);
			data.put(Globals.buttons, this.cgformConfig.getButtons() == null ? new ArrayList<CgFormFieldEntity>(0)
					: this.cgformConfig.getButtons());
			data.put(Globals.buttonSqlMap, this.cgformConfig.getButtonSqlMap() == null
					? new HashMap<String, String[]>(0) : this.cgformConfig.getButtonSqlMap());
			data.put(Globals.packageStyle, this.cgformConfig.getPackageStyle());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}

	public void generateToFile() throws TemplateException, IOException {
		log.info("----jeecg---Code----Generation----[单表模型:" + this.tableName + "]------- 生成中。。。");

		CgformCodeFactory codeFactory = new CgformCodeFactory();
		codeFactory.setProjectPath(this.cgformConfig.getProjectPath());
		codeFactory.setPackageStyle(this.cgformConfig.getPackageStyle());
		if (this.cgformConfig.getCgFormHead().getJformType().intValue() == 1){
			codeFactory.setIGenerator(new CgformCodeGenerate(createFileProperty, this.cgformConfig));
		} else {
			codeFactory.setIGenerator(
					new CgformCodeGenerate(this.sub, this.subG, this.subFileProperty, "uuid", this.foreignKeys));
		}
		List<ExecuteModel> generateFiles = new ArrayList<ExecuteModel>();
		List<ExecuteModel> generateHtmlFiles = new ArrayList<ExecuteModel>();
		if (createFileProperty.isJspFlag()) {
			if ("03".equals(createFileProperty.getJspMode())) {
				ExecuteModel exe = new ExecuteModel("onetomany/cgform_jspSubTemplate.ftl", "jspList");
				generateFiles.add(exe);
			} else if ("06".equals(createFileProperty.getJspMode())) {
				ExecuteModel exe = new ExecuteModel("onetomany/cgform_jspBootstrapSubTemplate.ftl", "jspList");
				generateFiles.add(exe);
			} else {
				if (StringUtils.isNotEmpty(this.cgformJspHtml)) { 
					ExecuteModel exe = new ExecuteModel(this.cgformJspHtml, "jsp_add");
					ExecuteModel exe1 = new ExecuteModel(this.cgformJspHtml.replace("doAdd", "doUpdate"), "jsp_update");
					generateHtmlFiles.add(exe);
					generateHtmlFiles.add(exe1);
				} else if ("01".equals(createFileProperty.getJspMode())) {
					ExecuteModel exe = new ExecuteModel("cgform_jspTableTemplate_add.ftl", "jsp_add");
					ExecuteModel exe1 = new ExecuteModel("cgform_jspTableTemplate_update.ftl", "jsp_update");
					generateFiles.add(exe);
					generateFiles.add(exe1);
				} else if ("02".equals(createFileProperty.getJspMode())) {
					ExecuteModel exe = new ExecuteModel("cgform_jspDivTemplate_add.ftl", "jsp_add");
					ExecuteModel exe1 = new ExecuteModel("cgform_jspDivTemplate_update.ftl", "jsp_update");
					generateFiles.add(exe);
					generateFiles.add(exe1);
				} else if ("05".equals(createFileProperty.getJspMode())) {
					ExecuteModel exe = new ExecuteModel("cgform_jspBootstrapTemplate_add.ftl", "jsp_add");
					ExecuteModel exe1 = new ExecuteModel("cgform_jspBootstrapTemplate_update.ftl", "jsp_update");
					generateFiles.add(exe);
					generateFiles.add(exe1);
				}
				ExecuteModel exe = new ExecuteModel("cgform_jspListTemplate.ftl", "jspList");
				ExecuteModel exe1 = new ExecuteModel("cgform_jsListEnhanceTemplate.ftl", "jsList");
				ExecuteModel exe2 = new ExecuteModel("cgform_jsEnhanceTemplate.ftl", "js");
				generateFiles.add(exe);
				generateFiles.add(exe1);
				generateFiles.add(exe2);
			}
		}
		if (createFileProperty.isServiceImplFlag()) {
			ExecuteModel exe = new ExecuteModel("cgform_serviceImplTemplate.ftl", "serviceImpl");
			generateFiles.add(exe);
		}
		if (createFileProperty.isServiceIFlag()) {
			ExecuteModel exe = new ExecuteModel("cgform_serviceITemplate.ftl", "service");
			generateFiles.add(exe);
		}
		if (createFileProperty.isActionFlag()) {
			ExecuteModel exe = new ExecuteModel("cgform_controllerTemplate.ftl", "controller");
			generateFiles.add(exe);
		}
		if (createFileProperty.isEntityFlag()) {
			ExecuteModel exe = new ExecuteModel("cgform_entityTemplate.ftl", "entity");
			generateFiles.add(exe);
		}
		
		codeFactory.invoke(generateFiles);
		codeFactory.invokeNotFlt(generateHtmlFiles);
		
		
		
		log.info("----jeecg----Code----Generation-----[单表模型：" + this.tableName + "]------ 生成完成。。。");
	}

	public GenerateEntity getCgformConfig() {
		return this.cgformConfig;
	}

	public void setCgformConfig(GenerateEntity cgformConfig) {
		this.cgformConfig = cgformConfig;
	}

	public String getCgformJspHtml() {
		return this.cgformJspHtml;
	}

	public void setCgformJspHtml(String cgformJspHtml) {
		this.cgformJspHtml = cgformJspHtml;
	}
	public CgformCodeGenerate() {
	}

	public CgformCodeGenerate(CreateFileProperty createFileProperty2, GenerateEntity generateEntity) {
		this.entityName = generateEntity.getEntityName();
		this.entityPackage = generateEntity.getEntityPackage();
		this.tableName = generateEntity.getTableName();
		this.ftlDescription = generateEntity.getFtlDescription();
		FIELD_ROW_NUM = 1;
		createFileProperty = createFileProperty2;
		createFileProperty.setJspMode(createFileProperty2.getJspMode());
		this.primaryKeyPolicy = generateEntity.getPrimaryKeyPolicy();
		this.sequenceCode = "";
		this.cgformConfig = generateEntity;
	}

	public CgformCodeGenerate(SubTableEntity sub, GenerateEntity subG, CreateFileProperty subFileProperty,
			String policy, String[] array) {
		this.entityName = subG.getEntityName();
		this.entityPackage = subG.getEntityPackage();
		this.tableName = subG.getTableName();
		this.ftlDescription = subG.getFtlDescription();
		createFileProperty = subFileProperty;
		FIELD_ROW_NUM = 1;
		this.primaryKeyPolicy = policy;
		this.sequenceCode = "";
		this.cgformConfig = subG;
		this.foreignKeys = array;
		this.sub = sub;
		this.subG = subG;
		this.subFileProperty = subFileProperty;
		this.policy = policy;
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
