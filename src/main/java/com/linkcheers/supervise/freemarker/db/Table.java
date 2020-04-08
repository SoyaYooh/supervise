package com.linkcheers.supervise.freemarker.db;

import java.util.List;

/**
 * @author bxk
 */
public class Table {
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 实体类名称
	 */
	private String entityName;
	/**
	 * 表描述
	 */
	private String tableComments;
	/**
	 * 字段信息
	 */
	private List<Cloumns> cloumns;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getTableComments() {
		return tableComments;
	}

	public void setTableComments(String tableComments) {
		this.tableComments = tableComments;
	}

	public List<Cloumns> getCloumns() {
		return cloumns;
	}

	public void setCloumns(List<Cloumns> cloumns) {
		this.cloumns = cloumns;
	}
}
