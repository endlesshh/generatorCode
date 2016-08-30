package code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import code.constant.Globals;
import code.database.WifunsReadTable;
import code.entity.config.ExecuteModel;
import code.generate.IGenerator;
import code.generate.enums.CodeType;
import code.generate.factory.CodeFactory;
import code.pojo.Columnt;
import code.pojo.CreateFileProperty;
import code.util.CodeDateUtils;
import code.util.CodeResourceUtil;
import code.util.NonceUtils;
import freemarker.template.TemplateException;

public class CodeGenerate implements IGenerator {
	//作者
	private static String author = "ShiQiang";
	//要创建的实体类名称
	private static String entityName = "Person";
	//不要填写前缀
	private static String tableName = "person";
	//类描述：  比如 goods 商品
	private static String ftlDescription = "人员信息";
	
	
	private static CreateFileProperty createFileProperty = new CreateFileProperty();
	private List<Columnt> columns = new ArrayList<Columnt>();
	private List<Columnt> originalColumns = new ArrayList<Columnt>();
	
	
	private WifunsReadTable dbFiledUtil = new WifunsReadTable();
	
	
	public Map<String, Object> execute() {
		Map<String,Object> data = new HashMap<String,Object>();
		//传入数据 
		data.put(Globals.author, author);
		data.put(Globals.entityName, entityName);
		data.put(Globals.tableName, tableName);
		data.put(Globals.ftl_description, ftlDescription);  
		data.put(Globals.ftl_create_time, CodeDateUtils.dateToString(new Date()));
		 
		
		//配置数据
		data.put(Globals.bussiPackage, CodeResourceUtil.bussiPackage);
		data.put(Globals.wifuns_table_id, CodeResourceUtil.WIFUNS_GENERATE_TABLE_ID);
		long serialVersionUID = NonceUtils.randomLong() + NonceUtils.currentMills();
		data.put(Globals.serialVersionUID, String.valueOf(serialVersionUID));
		
		//数据库数据
		try {
			this.columns = this.dbFiledUtil.readTableColumn(Globals.DEFAULT_TABLE_SUFFIX+tableName);
			data.put(Globals.columns, this.columns);
			this.originalColumns = this.dbFiledUtil.readOriginalTableColumn(Globals.DEFAULT_TABLE_SUFFIX+tableName);
			data.put(Globals.originalColumns, this.originalColumns);
			for (Columnt c : this.originalColumns) {
				if (c.getFieldName().toLowerCase().equals(CodeResourceUtil.WIFUNS_GENERATE_TABLE_ID.toLowerCase())){
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
		
		CodeFactory codeFactory = new CodeFactory();
		codeFactory.setIGenerator(new CodeGenerate());
	
		List<ExecuteModel> generateFiles = new ArrayList<ExecuteModel>();
		//html页面
		if (createFileProperty.isHtmlFlag()) {
			if ("01".equals(createFileProperty.getHtmlMode())) {
				ExecuteModel exe = new ExecuteModel("adminlisthtml.ftl",CodeType.htmlList.getEnumName(), "system/wifuns/blue");
				ExecuteModel exe1 = new ExecuteModel("adminaddhtml.ftl",CodeType.htmlAdd.getEnumName(), "system/wifuns/blue");
				generateFiles.add(exe);  
				generateFiles.add(exe1);  
			}
		}
		
		
		
		//--------------------------------实体类
		if (createFileProperty.isEntityFlag()) {
			ExecuteModel exe = new ExecuteModel("domain.ftl",CodeType.domain.getEnumName(),"domain");
			generateFiles.add(exe);  
		}
		//--------------------------------服务
		if (createFileProperty.isServiceImplFlag()) {
			ExecuteModel exe = new ExecuteModel("serviceimpl.ftl", CodeType.serviceImpl.getEnumName(),"service/impl");
			generateFiles.add(exe);  
		}
		if (createFileProperty.isServiceIFlag()) {
			ExecuteModel exe = new ExecuteModel("service.ftl",CodeType.IService.getEnumName(),"service");
			generateFiles.add(exe);  
		}
		//controller-----------------------控制
		if (createFileProperty.isActionFlag()) {
			ExecuteModel exe = new ExecuteModel("adminaction.ftl",CodeType.adminAction.getEnumName(),"admin/action");
			generateFiles.add(exe);
		}
		
		//----------------------------------dao
		if (createFileProperty.isDaoFlag()) {
			ExecuteModel exe = new ExecuteModel("dao.ftl", CodeType.IDao.getEnumName(),"dao");
			generateFiles.add(exe);  
		}
		if (createFileProperty.isDaoImplFlag()) {
			ExecuteModel exe = new ExecuteModel("daoimpl.ftl",CodeType.daoImpl.getEnumName(),"dao/impl");
			generateFiles.add(exe);  
		}
		if (createFileProperty.isQueryFlag()) {
			ExecuteModel exe = new ExecuteModel("query.ftl", CodeType.query.getEnumName(),"domain/query");
			generateFiles.add(exe);  
		}
		
		
		codeFactory.invoke(generateFiles);
	}

	public static void main(String[] args) throws TemplateException, IOException {
		System.out.println("----wifuns--------- Code------------- Generation --------- 生成中。。。");
		new CodeGenerate().generateToFile();
		System.out.println("----wifuns--------- Code------------- Generation --------- 生成完成。。。");
	}
	
	
	//---------------------------------------------构造函数等，不重要的
	public CodeGenerate() {
	}

	public CodeGenerate(String author, String entityName, String tableName, String ftlDescription,
			CreateFileProperty createFileProperty) {
		CodeGenerate.entityName = entityName;
		CodeGenerate.author = author;
		CodeGenerate.tableName = tableName;
		CodeGenerate.ftlDescription = ftlDescription;
		CodeGenerate.createFileProperty = createFileProperty;
	}

	static {
		createFileProperty.setActionFlag(true);
		createFileProperty.setServiceIFlag(true);
		createFileProperty.setServiceImplFlag(true);
		createFileProperty.setDaoFlag(true);
		createFileProperty.setDaoImplFlag(true);
		createFileProperty.setQueryFlag(true);
		createFileProperty.setEntityFlag(true);
		createFileProperty.setHtmlFlag(true);
		createFileProperty.setHtmlMode("01");
	}
}
