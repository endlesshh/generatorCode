package com.wifuns.pojo.onetomany;

import java.util.List;

public class CodeParamEntity {
	private String entityPackage;
	private String tableName;
	private String entityName;
	private String ftlDescription;
	private String primaryKeyPolicy;
	private String sequenceCode;
	private String ftl_mode = "A";
	List<SubTableEntity> subTabParam;

	public List<SubTableEntity> getSubTabParam() {
		return this.subTabParam;
	}

	public void setSubTabParam(List<SubTableEntity> subTabParam) {
		this.subTabParam = subTabParam;
	}

	public String getEntityPackage() {
		return this.entityPackage;
	}

	public String getTableName() {
		return this.tableName;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public String getFtlDescription() {
		return this.ftlDescription;
	}

	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void setFtlDescription(String ftlDescription) {
		this.ftlDescription = ftlDescription;
	}

	public String getFtl_mode() {
		return this.ftl_mode;
	}

	public void setFtl_mode(String ftl_mode) {
		this.ftl_mode = ftl_mode;
	}

	public String getPrimaryKeyPolicy() {
		return this.primaryKeyPolicy;
	}

	public String getSequenceCode() {
		return this.sequenceCode;
	}

	public void setPrimaryKeyPolicy(String primaryKeyPolicy) {
		this.primaryKeyPolicy = primaryKeyPolicy;
	}

	public void setSequenceCode(String sequenceCode) {
		this.sequenceCode = sequenceCode;
	}
}