package com.linkcheers.supervise.freemarker.db;

import java.util.List;

/**
 * @author bxk
 */
public class Cloumns {
	/**
	 * 数据库字段名称
	 */
	private String columnName;
	/**
	 * 对象字段名
	 */
	private String fieldName;
	/**
	 * 数据库字段类型
	 */
	private String dataType;
	/**
	 * 对象字段类型
	 */
	private String fieldType;
	/**
	 * jdbc类型
	 */
	private String jdbcType;
	/**
	 * 数据库字段描述
	 */
	private String comment;
	/**
	 * 是否主键
	 */
	private String isKey;
	/**
	 * 是否不为空
	 */
	private String notNull;
	/**
	 * 描述
	 */
	private List<String> rule;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIsKey() {
		return isKey;
	}

	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}

	public String getNotNull() {
		return notNull;
	}

	public void setNotNull(String notNull) {
		this.notNull = notNull;
	}

	public List<String> getRule() {
		return rule;
	}

	public void setRule(List<String> rule) {
		this.rule = rule;
	}
}
