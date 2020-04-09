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
	 * 创建时间,默认为当前时间
	 */
	private String date;
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
	 * 数据库密码
	 */
	private String passWord;
	/**
	 * 是否需要生成文件路径
	 */
	private String isFilePath;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.format(new Date());
		this.date = formatter.format(new Date());
		this.isLombok = "N";;
	}

	public String getOrmType() {
		return ormType;
	}

	public void setOrmType(String ormType) {
		this.ormType = ormType;
	}
}
