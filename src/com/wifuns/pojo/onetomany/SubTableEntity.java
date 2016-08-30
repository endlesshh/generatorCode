package com.wifuns.pojo.onetomany;

import java.util.List;

import com.wifuns.pojo.Columnt;

public class SubTableEntity {
	private String entityPackage;
	private String tableName;
	private String entityName;
	private String primaryKeyPolicy;
	private String sequenceCode;
	private String ftlDescription;
	private String[] foreignKeys;
	private List<Columnt> subColums;

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

	public List<Columnt> getSubColums() {
		return this.subColums;
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

	public void setSubColums(List<Columnt> subColums) {
		this.subColums = subColums;
	}

	public String[] getForeignKeys() {
		return this.foreignKeys;
	}

	public void setForeignKeys(String[] foreignKeys) {
		this.foreignKeys = foreignKeys;
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
