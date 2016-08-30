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
import com.wifuns.entity.config.ExecuteModel;
import com.wifuns.generate.IGenerator;
import com.wifuns.generate.factory.CodeFactoryOneToMany;
import com.wifuns.generate.generator.CodeGenerate;
import com.wifuns.pojo.Columnt;
import com.wifuns.pojo.CreateFileProperty;
import com.wifuns.pojo.onetomany.CodeParamEntity;
import com.wifuns.pojo.onetomany.SubTableEntity;
import com.wifuns.util.CodeDateUtils;
import com.wifuns.util.CodeResourceUtil;
import com.wifuns.util.NonceUtils;
import com.wifuns.util.def.JeecgKey;

import freemarker.template.TemplateException;

public class CodeGenerateOneToMany implements IGenerator {
	private static final Log log = LogFactory.getLog(CodeGenerateOneToMany.class);

	private static String entityPackage = "test";
	private static String entityName = "Person";
	private static String tableName = "person";
	private static String ftlDescription = "用户";
	private static String primaryKeyPolicy = "uuid";
	private static String sequenceCode = "";
	private static String ftl_mode;
	public static String FTL_MODE_A = "A";
	public static String FTL_MODE_B = "B";

	private static List<SubTableEntity> subTabParam = new ArrayList<SubTableEntity>();
	private static CreateFileProperty createFileProperty = new CreateFileProperty();
	public static int FIELD_ROW_NUM = 4;

	private List<Columnt> mainColums = new ArrayList<Columnt>();

	private List<Columnt> originalColumns = new ArrayList<Columnt>();

	private List<SubTableEntity> subTabFtl = new ArrayList<SubTableEntity>();

	private static WifunsReadTable dbFiledUtil = new WifunsReadTable();

	public CodeGenerateOneToMany() {
	}

	public CodeGenerateOneToMany(String entityPackage, String entityName, String tableName,
			List<SubTableEntity> subTabParam, String ftlDescription, CreateFileProperty createFileProperty,
			String primaryKeyPolicy, String sequenceCode) {
		CodeGenerateOneToMany.entityName = entityName;
		CodeGenerateOneToMany.entityPackage = entityPackage;
		CodeGenerateOneToMany.tableName = tableName;
		CodeGenerateOneToMany.ftlDescription = ftlDescription;
		CodeGenerateOneToMany.createFileProperty = createFileProperty;
		CodeGenerateOneToMany.subTabParam = subTabParam;
		CodeGenerateOneToMany.primaryKeyPolicy = StringUtils.isNotBlank(primaryKeyPolicy) ? primaryKeyPolicy : "uuid";
		CodeGenerateOneToMany.sequenceCode = sequenceCode;
	}

	public CodeGenerateOneToMany(CodeParamEntity codeParamEntity) {
		entityName = codeParamEntity.getEntityName();
		entityPackage = codeParamEntity.getEntityPackage();
		tableName = codeParamEntity.getTableName();
		ftlDescription = codeParamEntity.getFtlDescription();
		subTabParam = codeParamEntity.getSubTabParam();
		ftl_mode = codeParamEntity.getFtl_mode();
		primaryKeyPolicy = StringUtils.isNotBlank(codeParamEntity.getPrimaryKeyPolicy())
				? codeParamEntity.getPrimaryKeyPolicy() : "uuid";
		sequenceCode = codeParamEntity.getSequenceCode();
	}

	public Map<String, Object> execute() {
		Map<String,Object> data = new HashMap<String,Object>();

		data.put(Globals.bussiPackage, CodeResourceUtil.bussiPackage);

		data.put(Globals.entityPackage, entityPackage);

		data.put(Globals.entityName, entityName);

		data.put(Globals.tableName, tableName);

		data.put(Globals.ftl_description, ftlDescription);

		data.put(Globals.jeecg_table_id, CodeResourceUtil.JEECG_GENERATE_TABLE_ID);

		data.put(Globals.jeecg_primary_key_policy, primaryKeyPolicy);
		data.put(Globals.jeecg_sequence_code, sequenceCode);
		data.put(Globals.ftl_create_time, CodeDateUtils.dateToString(new Date()));

		data.put(Globals.field_required_num,
				Integer.valueOf(StringUtils.isNotEmpty(CodeResourceUtil.JEECG_UI_FIELD_REQUIRED_NUM)
						? Integer.parseInt(CodeResourceUtil.JEECG_UI_FIELD_REQUIRED_NUM) : -1));

		data.put(Globals.search_field_num,
				Integer.valueOf(StringUtils.isNotEmpty(CodeResourceUtil.JEECG_UI_FIELD_SEARCH_NUM)
						? Integer.parseInt(CodeResourceUtil.JEECG_UI_FIELD_SEARCH_NUM) : -1));

		data.put(Globals.field_row_num, Integer.valueOf(FIELD_ROW_NUM));
		try {
			this.mainColums = dbFiledUtil.readTableColumn(tableName);
			data.put(Globals.mainColums, this.mainColums);
			data.put(Globals.columns, this.mainColums);

			this.originalColumns = dbFiledUtil.readOriginalTableColumn(tableName);
			data.put(Globals.originalColumns, this.originalColumns);

			for (Columnt c : this.originalColumns) {
				if (c.getFieldName().toLowerCase().equals(CodeResourceUtil.JEECG_GENERATE_TABLE_ID.toLowerCase())) {
					data.put(Globals.primary_key_type, c.getFieldType());
				}
			}

			this.subTabFtl.clear();
			for (SubTableEntity subTableEntity : subTabParam) {
				SubTableEntity po = new SubTableEntity();
				List<Columnt> subColum = dbFiledUtil.readTableColumn(subTableEntity.getTableName());
				po.setSubColums(subColum);
				po.setEntityName(subTableEntity.getEntityName());
				po.setFtlDescription(subTableEntity.getFtlDescription());
				po.setTableName(subTableEntity.getTableName());
				po.setEntityPackage(subTableEntity.getEntityPackage());

				String[] fkeys = subTableEntity.getForeignKeys();
				List<String> foreignKeys = new ArrayList<String>();
				for (String key : fkeys) {
					if (CodeResourceUtil.JEECG_FILED_CONVERT) {
						foreignKeys.add(WifunsReadTable.formatFieldCapital(key));
					} else {
						String keyStr = key.toLowerCase();
						String field = keyStr.substring(0, 1).toUpperCase() + keyStr.substring(1);
						foreignKeys.add(field);
					}
				}

				po.setForeignKeys((String[]) foreignKeys.toArray(new String[0]));
				this.subTabFtl.add(po);
			}

			data.put(Globals.subTab, this.subTabFtl);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		long serialVersionUID = NonceUtils.randomLong() + NonceUtils.currentMills();

		data.put(Globals.serialVersionUID, String.valueOf(serialVersionUID));
		return data;
	}

	public void generateToFile() throws TemplateException, IOException {
		CodeFactoryOneToMany codeFactoryOneToMany = new CodeFactoryOneToMany();
		codeFactoryOneToMany.setIGenerator(new CodeGenerateOneToMany());
		List<ExecuteModel> generateFiles = new ArrayList<ExecuteModel>();
		if (createFileProperty.isJspFlag()) {
			ExecuteModel exe = new ExecuteModel("onetomany/jspListTemplate.ftl", "jspList");
			generateFiles.add(exe);  
			ExecuteModel exe1 = new ExecuteModel("onetomany/jspTemplate.ftl", "jsp");
			generateFiles.add(exe1);  
		}

		if (createFileProperty.isServiceImplFlag()) {
			ExecuteModel exe = new ExecuteModel("onetomany/serviceImplTemplate.ftl", "serviceImpl");
			generateFiles.add(exe);
		}
		if (createFileProperty.isServiceIFlag()) {
			ExecuteModel exe = new ExecuteModel("onetomany/serviceITemplate.ftl", "service");
			generateFiles.add(exe);
		}
		if (createFileProperty.isActionFlag()) {
			ExecuteModel exe = new ExecuteModel("onetomany/controllerTemplate.ftl", "controller");
			generateFiles.add(exe);
		}
		if (createFileProperty.isEntityFlag()) {
			ExecuteModel exe = new ExecuteModel("onetomany/entityTemplate.ftl", "entity");
			generateFiles.add(exe);
		}
		if (createFileProperty.isPageFlag()) {
			ExecuteModel exe = new ExecuteModel("onetomany/pageTemplate.ftl", "page");
			generateFiles.add(exe);
		}
		codeFactoryOneToMany.invoke(generateFiles);
	}

	public static void main(String[] args) {
		List<SubTableEntity> subTabParamIn = new ArrayList<SubTableEntity>();

		SubTableEntity po = new SubTableEntity();

		po.setTableName("jeecg_order_custom");

		po.setEntityName("OrderCustom");

		po.setEntityPackage("order");

		po.setFtlDescription("订单客户明细");

		po.setPrimaryKeyPolicy(JeecgKey.UUID);
		po.setSequenceCode(null);

		po.setForeignKeys(new String[] { "GORDER_OBID", "GO_ORDER_CODE" });
		subTabParamIn.add(po);

		SubTableEntity po2 = new SubTableEntity();
		po2.setTableName("jeecg_order_product");
		po2.setEntityName("OrderProduct");
		po2.setEntityPackage("order");
		po2.setFtlDescription("订单产品明细");
		po2.setForeignKeys(new String[] { "GORDER_OBID", "GO_ORDER_CODE" });

		po2.setPrimaryKeyPolicy(JeecgKey.UUID);
		po2.setSequenceCode(null);
		subTabParamIn.add(po2);

		CodeParamEntity codeParamEntityIn = new CodeParamEntity();
		codeParamEntityIn.setTableName("jeecg_order_main");
		codeParamEntityIn.setEntityName("OrderMain");
		codeParamEntityIn.setEntityPackage("order");
		codeParamEntityIn.setFtlDescription("订单抬头");
		codeParamEntityIn.setFtl_mode(FTL_MODE_B);

		codeParamEntityIn.setPrimaryKeyPolicy(JeecgKey.UUID);

		codeParamEntityIn.setSequenceCode(null);
		codeParamEntityIn.setSubTabParam(subTabParamIn);

		oneToManyCreate(subTabParamIn, codeParamEntityIn);
	}

	public static void oneToManyCreate(List<SubTableEntity> subTabParamIn, CodeParamEntity codeParamEntityIn) {
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
			String[] fkeys = sub.getForeignKeys();
			List<String> foreignKeys = new ArrayList<String>();
			for (String key : fkeys) {
				if (CodeResourceUtil.JEECG_FILED_CONVERT) {
					foreignKeys.add(WifunsReadTable.formatFieldCapital(key));
				} else {
					String keyStr = key.toLowerCase();
					String field = keyStr.substring(0, 1).toUpperCase() + keyStr.substring(1);
					foreignKeys.add(field);
				}

			}

			try {
				new CodeGenerate(sub.getEntityPackage(), sub.getEntityName(), sub.getTableName(), sub.getFtlDescription(),
						subFileProperty,
						StringUtils.isNotBlank(sub.getPrimaryKeyPolicy()) ? sub.getPrimaryKeyPolicy() : "uuid",
						sub.getSequenceCode(), (String[]) foreignKeys.toArray(new String[0])).generateToFile();
			} catch (TemplateException e) { 
				e.printStackTrace();
			} catch (IOException e) { 
				e.printStackTrace();
			}
		}

		try {
			new CodeGenerateOneToMany(codeParamEntityIn).generateToFile();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("----jeecg----Code----Generation------[一对多数据模型：" + codeParamEntityIn.getTableName()
				+ "]------ 生成完成。。。");
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