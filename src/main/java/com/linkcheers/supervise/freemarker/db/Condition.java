package com.linkcheers.supervise.freemarker.db;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bxk
 */
@Entity
public class Condition {
	/**
	 * 包名称,例子com.supervision.user
	 */
	private String packageName;
	/**
	 * 作者名称
	 */
	private String author;
	/**
	 * 表名称
	 */
	private String tableName;
	/**
	 * 表信息
	 */
	private Table table;
	/**
	 * 是否使用lombok插件
	 */
	private String isLombok;
	/**
	 * 数据库类型/Oracle,Mysql,SqlServer
	 */
	private String dataSourceType;
	/**
	 * 数据库url,Ip+Host+ServerName/SID，例子120.27.144.145:1521:ORCL
	 */
	private String url;
	/**
	 * 数据库用户名
	 */
	private String userName;
	/**
	 * 持久层框架类型
	 */
	private String  ormType;
	/**
	 * 持久层框架类型
	 */
	private String  isSwagger;
	/**
	 * 数据库密码
	 */
	private String passWord;
	/**
	 * 是否需要生成文件路径
	 */
	private String isFilePath;

    private String method;

	/**
	 * 开发框架
	 */
	private String frameType;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getAuthor() {
		if (StringUtils.isBlank(author)) {
			return System.getenv().get("USERNAME");
		}
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public String getIsLombok() {
		return isLombok;
	}

	public void setIsLombok(String isLombok) {
		this.isLombok = isLombok;
	}

	public String getDataSourceType() {
		return Convert.initCap(dataSourceType);
	}

	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getIsFilePath() {
		return isFilePath;
	}

	public void setIsFilePath(String isFilePath) {
		this.isFilePath = isFilePath;
	}

	public Condition() {
	}

	public String getIsSwagger() {
		return isSwagger;
	}

	public void setIsSwagger(String isSwagger) {
		this.isSwagger = isSwagger;
	}

	public String getOrmType() {
		return ormType;
	}

	public void setOrmType(String ormType) {
		this.ormType = ormType;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getFrameType() {
		return frameType;
	}

	public void setFrameType(String frameType) {
		this.frameType = frameType;
	}
}
