package code.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import code.pojo.Columnt;
import code.pojo.TableConvert;
import code.util.CodeResourceUtil;
import code.util.CodeStringUtils;
/**
 * 调用数据库获取表的字段名称
 * @author Administrator
 *
 */
public class WifunsReadTable {
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(WifunsReadTable.class);
	//版本
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -5324160085184088010L;
	//数据库相关
	private Connection conn;
	private Statement stmt;
	private String sql;
	private ResultSet rs;

	public static void main(String[] args) throws SQLException {
		try {
			//List<Columnt> cls = new WifunsReadTable().readTableColumn("wifunsshop_person");
			List<Columnt> cls = new WifunsReadTable().readOriginalTableColumn("wifunsshop_person");
			
			for (Columnt c : cls){
				System.out.print(c.getFieldName()+"==");System.out.println(c.getFieldType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(ArrayUtils.toString(new WifunsReadTable().readAllTableNames()));
	}

	/**
	 * 获取 数据全部库表的 colum-名称
	 * @return
	 * @throws SQLException
	 */
	public List<String> readAllTableNames() throws SQLException {
		List<String> tableNames = new ArrayList<String>();
		try {
			Class.forName(CodeResourceUtil.DIVER_NAME);
			this.conn = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME,
					CodeResourceUtil.PASSWORD);
			this.stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			if (CodeResourceUtil.DATABASE_TYPE.equals("mysql")) {
				this.sql = MessageFormat.format(
						"select distinct table_name from information_schema.columns where table_schema = {0}",
						new Object[] { TableConvert.getV(CodeResourceUtil.DATABASE_NAME) });
			}

			this.rs = this.stmt.executeQuery(this.sql);
			while (this.rs.next()) {
				String tableName = this.rs.getString(1);
				tableNames.add(tableName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (this.stmt != null) {
					this.stmt.close();
					this.stmt = null;
					System.gc();
				}
				if (this.conn != null) {
					this.conn.close();
					this.conn = null;
					System.gc();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
		return tableNames;
	}
	/**
	 *  获取的是数据库中的字段和类型
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public List<Columnt> readTableColumn(String tableName) throws Exception {
		List<Columnt> columntList = new ArrayList<Columnt>();
		try {
			Class.forName(CodeResourceUtil.DIVER_NAME);
			this.conn = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME,
					CodeResourceUtil.PASSWORD);
			this.stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			if (CodeResourceUtil.DATABASE_TYPE.equals("mysql")) {
				this.sql = MessageFormat.format(
						"select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}",
						new Object[] { TableConvert.getV(tableName.toUpperCase()),
								TableConvert.getV(CodeResourceUtil.DATABASE_NAME) });
			}

			this.rs = this.stmt.executeQuery(this.sql);
			this.rs.last();
			int fieldNum = this.rs.getRow();
			int n = fieldNum;

			if (n > 0) {
				Columnt columnt = new Columnt();

				if (CodeResourceUtil.WIFUNS_FILED_CONVERT){
					columnt.setFieldName(formatField(this.rs.getString(1).toLowerCase()));
				} else {
					columnt.setFieldName(this.rs.getString(1).toLowerCase());
				}

				columnt.setFieldDbName(this.rs.getString(1).toUpperCase());
				columnt.setFieldType(formatField(this.rs.getString(2).toLowerCase()));

				columnt.setPrecision(this.rs.getString(4));
				columnt.setScale(this.rs.getString(5));
				columnt.setCharmaxLength(this.rs.getString(6));
				columnt.setNullable(TableConvert.getNullAble(this.rs.getString(7)));

				formatFieldClassType(columnt);
				columnt.setFiledComment(
						StringUtils.isBlank(this.rs.getString(3)) ? columnt.getFieldName() : this.rs.getString(3));

				String[] ui_filter_fields = new String[0];
				if (CodeResourceUtil.WIFUNS_GENERATE_UI_FILTER_FIELDS != null) {
					ui_filter_fields = CodeResourceUtil.WIFUNS_GENERATE_UI_FILTER_FIELDS.toLowerCase().split(",");
				}

				if ((!CodeResourceUtil.WIFUNS_GENERATE_TABLE_ID.equals(columnt.getFieldName()))
						&& (!CodeStringUtils.isIn(columnt.getFieldDbName().toLowerCase(), ui_filter_fields))) {
					columntList.add(columnt);
				}
				while (this.rs.previous()) {
					Columnt po = new Columnt();

					if (CodeResourceUtil.WIFUNS_FILED_CONVERT){
						po.setFieldName(formatField(this.rs.getString(1).toLowerCase()));
					}else {
						po.setFieldName(this.rs.getString(1).toLowerCase());
					}

					po.setFieldDbName(this.rs.getString(1).toUpperCase());

					if ((!CodeResourceUtil.WIFUNS_GENERATE_TABLE_ID.equals(po.getFieldName()))
							&& (!CodeStringUtils.isIn(po.getFieldDbName().toLowerCase(), ui_filter_fields))) {
						po.setFieldType(formatField(this.rs.getString(2).toLowerCase()));

						po.setPrecision(this.rs.getString(4));
						po.setScale(this.rs.getString(5));
						po.setCharmaxLength(this.rs.getString(6));
						po.setNullable(TableConvert.getNullAble(this.rs.getString(7)));

						formatFieldClassType(po);
						po.setFiledComment(
								StringUtils.isBlank(this.rs.getString(3)) ? po.getFieldName() : this.rs.getString(3));
						columntList.add(po);
					}
				}
			} else {
				throw new Exception("该表不存在或者表中没有字段");
			}

		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (this.stmt != null) {
					this.stmt.close();
					this.stmt = null;
					System.gc();
				}
				if (this.conn != null) {
					this.conn.close();
					this.conn = null;
					System.gc();
				}
			} catch (SQLException e) {
				throw e;
			}

		}

		List<Columnt> rsList = new ArrayList<Columnt>();
		for (int i = columntList.size() - 1; i >= 0; i--) {
			Columnt ch = (Columnt) columntList.get(i);
			rsList.add(ch);
		}
		return rsList;
	}

	public List<Columnt> readOriginalTableColumn(String tableName) throws Exception {
		List<Columnt> columntList = new ArrayList<Columnt>();
		try {
			Class.forName(CodeResourceUtil.DIVER_NAME);
			this.conn = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME,
					CodeResourceUtil.PASSWORD);
			this.stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			if (CodeResourceUtil.DATABASE_TYPE.equals("mysql")) {
				this.sql = MessageFormat.format(
						"select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}",
						new Object[] { TableConvert.getV(tableName.toUpperCase()),
								TableConvert.getV(CodeResourceUtil.DATABASE_NAME) });
			}
			this.rs = this.stmt.executeQuery(this.sql);
			this.rs.last();
			int fieldNum = this.rs.getRow();
			int n = fieldNum;

			if (n > 0) {
				Columnt columnt = new Columnt();

				if (CodeResourceUtil.WIFUNS_FILED_CONVERT){
					columnt.setFieldName(formatField(this.rs.getString(1).toLowerCase()));
				} else {
					columnt.setFieldName(this.rs.getString(1).toLowerCase());
				}

				columnt.setFieldDbName(this.rs.getString(1).toUpperCase());

				columnt.setPrecision(TableConvert.getNullString(this.rs.getString(4)));
				columnt.setScale(TableConvert.getNullString(this.rs.getString(5)));
				columnt.setCharmaxLength(TableConvert.getNullString(this.rs.getString(6)));
				columnt.setNullable(TableConvert.getNullAble(this.rs.getString(7)));

				columnt.setFieldType(
						formatDataType(this.rs.getString(2).toLowerCase(), columnt.getPrecision(), columnt.getScale()));

				formatFieldClassType(columnt);
				columnt.setFiledComment(
						StringUtils.isBlank(this.rs.getString(3)) ? columnt.getFieldName() : this.rs.getString(3));
				
				String[] ui_filter_fields = new String[0];
				if (CodeResourceUtil.WIFUNS_GENERATE_UI_FILTER_FIELDS != null) {
					ui_filter_fields = CodeResourceUtil.WIFUNS_GENERATE_UI_FILTER_FIELDS.toLowerCase().split(",");
				}

				if ((!CodeResourceUtil.WIFUNS_GENERATE_TABLE_ID.equals(columnt.getFieldName()))
						&& (!CodeStringUtils.isIn(columnt.getFieldDbName().toLowerCase(), ui_filter_fields))) {
					columntList.add(columnt);
				} 
				while (this.rs.previous()) {
					Columnt po = new Columnt();

					if (CodeResourceUtil.WIFUNS_FILED_CONVERT){
						po.setFieldName(formatField(this.rs.getString(1)));
					} else {
						po.setFieldName(this.rs.getString(1));
					}
					
					po.setFieldDbName(this.rs.getString(1).toUpperCase());
					
					if ((!CodeResourceUtil.WIFUNS_GENERATE_TABLE_ID.equals(po.getFieldName()))
							&& (!CodeStringUtils.isIn(po.getFieldDbName().toLowerCase(), ui_filter_fields))) {
						po.setPrecision(TableConvert.getNullString(this.rs.getString(4)));
						po.setScale(TableConvert.getNullString(this.rs.getString(5)));
						po.setCharmaxLength(TableConvert.getNullString(this.rs.getString(6)));
						po.setNullable(TableConvert.getNullAble(this.rs.getString(7)));

						po.setFieldType(
								formatDataType(this.rs.getString(2).toLowerCase(), po.getPrecision(), po.getScale()));

						formatFieldClassType(po);
						po.setFiledComment(
								StringUtils.isBlank(this.rs.getString(3)) ? po.getFieldName() : this.rs.getString(3));
						columntList.add(po);
					} 
				}
			} else {
				throw new Exception("该表不存在或者表中没有字段");
			}
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (this.stmt != null) {
					this.stmt.close();
					this.stmt = null;
					System.gc();
				}
				if (this.conn != null) {
					this.conn.close();
					this.conn = null;
					System.gc();
				}
			} catch (SQLException e) {
				throw e;
			}

		}

		List<Columnt> rsList = new ArrayList<Columnt>();
		for (int i = columntList.size() - 1; i >= 0; i--) {
			Columnt ch = (Columnt) columntList.get(i);
			rsList.add(ch);
		}
		return rsList;
	}
	/**
	 * 
	 * @Title. formatField
	 * @Description. 全部转小写
	 * @param field
	 * @return String
	 */
	public static String formatField(String field) {
		String[] strs = field.split("_");
		field = "";
		int m = 0;
		for (int length = strs.length; m < length; m++) {
			if (m > 0) {
				String tempStr = strs[m].toLowerCase();
				tempStr = tempStr.substring(0, 1).toUpperCase() + tempStr.substring(1, tempStr.length());

				field = field + tempStr;
			} else {
				field = field + strs[m].toLowerCase();
			}
		}
		return field;
	}
	/**
	 * 首字母大写转化
	 * @param field
	 * @return
	 */
	public static String formatFieldCapital(String field) {
		String[] strs = field.split("_");
		field = "";
		int m = 0;
		for (int length = strs.length; m < length; m++) {
			if (m > 0) {
				String tempStr = strs[m].toLowerCase();
				tempStr = tempStr.substring(0, 1).toUpperCase() + tempStr.substring(1, tempStr.length());

				field = field + tempStr;
			} else {
				field = field + strs[m].toLowerCase();
			}
		}
		field = field.substring(0, 1).toUpperCase() + field.substring(1);
		return field;
	}
	/**
	 * 检查表是否存在
	 * @param tableName
	 * @return
	 */
	public boolean checkTableExist(String tableName) {
		try {
			System.out.println("数据库驱动: " + CodeResourceUtil.DIVER_NAME);
			Class.forName(CodeResourceUtil.DIVER_NAME);
			this.conn = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME,
					CodeResourceUtil.PASSWORD);
			this.stmt = this.conn.createStatement(1005, 1007);

			if (CodeResourceUtil.DATABASE_TYPE.equals("mysql")) {
				this.sql = ("select column_name,data_type,column_comment,0,0 from information_schema.columns where table_name = '"
						+ tableName.toUpperCase() + "'" + " and table_schema = '" + CodeResourceUtil.DATABASE_NAME
						+ "'");
			}

			this.rs = this.stmt.executeQuery(this.sql);
			this.rs.last();
			int fieldNum = this.rs.getRow();
			if (fieldNum > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	//目前不知道
	private void formatFieldClassType(Columnt columnt) {
		String fieldType = columnt.getFieldType();
		String scale = columnt.getScale();

		columnt.setClassType("inputxt");

		if ("N".equals(columnt.getNullable())) {
			columnt.setOptionType("*");
		}
		if (("datetime".equals(fieldType)) || (fieldType.contains("time"))){
			columnt.setClassType("easyui-datetimebox");
		}else if ("date".equals(fieldType)){
			columnt.setClassType("easyui-datebox");
		}else if (fieldType.contains("int")){
			columnt.setOptionType("n");
		}else if ("number".equals(fieldType)) {
			if ((StringUtils.isNotBlank(scale)) && (Integer.parseInt(scale) > 0)){
				columnt.setOptionType("d");
			}
		} else if (("float".equals(fieldType)) || ("double".equals(fieldType)) || ("decimal".equals(fieldType))){
			columnt.setOptionType("d");
		}else if ("numeric".equals(fieldType)){
			columnt.setOptionType("d");
		} 
	}
	/**
	 * 数据库类型转为java类型
	 * @param dataType
	 * @param precision
	 * @param scale
	 * @return
	 */
	private String formatDataType(String dataType, String precision, String scale) {
		if (dataType.contains("char")){
			dataType = "java.lang.String";
		}else if (dataType.contains("int")){
			dataType = "java.lang.Integer";
		}else if (dataType.contains("float")){
			dataType = "java.lang.Float";
		}else if (dataType.contains("double")){
			dataType = "java.lang.Double";
		}else if (dataType.contains("number")) {
			if ((StringUtils.isNotBlank(scale)) && (Integer.parseInt(scale) > 0)){
				dataType = "java.math.BigDecimal";
			}else if ((StringUtils.isNotBlank(precision)) && (Integer.parseInt(precision) > 10)){
				dataType = "java.lang.Long";
			}else{
				dataType = "java.lang.Integer";
			}
		} else if (dataType.contains("decimal")){
			dataType = "BigDecimal";
		}else if (dataType.contains("date")){
			dataType = "java.util.Date";
		}else if (dataType.contains("time")) {
			dataType = "java.util.Date";
		} else if (dataType.contains("blob")){
			dataType = "byte[]";
		}else if (dataType.contains("clob")){
			dataType = "java.sql.Clob";
		}else if (dataType.contains("numeric")){
			dataType = "BigDecimal";
		}else {
			dataType = "java.lang.Object";
		}
		return dataType;
	}
}
