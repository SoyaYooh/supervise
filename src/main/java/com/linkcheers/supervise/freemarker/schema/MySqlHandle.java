package com.linkcheers.supervise.freemarker.schema;

import com.linkcheers.supervise.freemarker.db.Cloumns;
import com.linkcheers.supervise.freemarker.db.Convert;
import com.linkcheers.supervise.freemarker.db.Table;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bxk
 */
@Component
public class MySqlHandle implements DataSourceHandle, InitializingBean {
	private static String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	private static String DATABASE_URL = "jdbc:mysql://localhost:3306/yoyo";
	private static String DATABASE_URL_PREFIX = "jdbc:mysql://";
	private static String DATABASE_USER = "root";
	private static String DATABASE_PASSWORD = "tenny123";
	private static Connection con = null;
	private static DatabaseMetaData dbmd = null;

	@Override
	public void init(String db_url, String db_user, String db_pw) {
		try {
			DATABASE_URL = DATABASE_URL_PREFIX + db_url;
			DATABASE_USER = db_user;
			DATABASE_PASSWORD = db_pw;
			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
			dbmd = con.getMetaData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getTables() {
		List<String> tables = new ArrayList<String>();

		try {
			ResultSet rs = dbmd.getTables(null, DATABASE_USER, null, new String[]{"TABLE"});
			while (rs.next()) {
				tables.add(rs.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tables;
	}

	@Override
	public List<String> getColumns(String tableName) {
		return null;
	}

	@Override
	public Table getTableCloumns(String tableName) {
		Table table = new Table();
		List<Cloumns> columns = new ArrayList<Cloumns>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select column_name, data_type, column_key, is_nullable, column_comment from information_schema.columns where table_name='" + tableName+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Cloumns cloumns = new Cloumns();
				cloumns.setColumnName(rs.getString("column_name"));
				cloumns.setDataType(rs.getString("data_type"));
				cloumns.setComment(rs.getString("column_comment"));
				cloumns.setIsKey(StringUtils.isEmpty(rs.getString("column_key")) ? "false" : "true");
				cloumns.setNotNull(rs.getString("is_nullable").equals("YES") ? "false" : "true");
				//cloumns.setComments(rs.getString("column_comment"));
				cloumns.setFieldName(Convert.fieldToProperty(rs.getString("column_name")));
				cloumns.setFieldType(Convert.processTypeOracleConvert(rs.getString("data_type")).getType());
				cloumns.setJdbcType(Convert.processTypeJdbcConvert(rs.getString("data_type")).getType());
				columns.add(cloumns);
			}
			String sql2="select table_comment from information_schema.tables  where table_name='" + tableName+"'";
			ResultSet rs2 = stmt.executeQuery(sql2);
			if (rs2.next()) {
				table.setTableComments(rs2.getString("table_comment"));
			}
			table.setTableName(tableName);
			table.setEntityName(Convert.tableNameConvert(tableName));
			table.setCloumns(columns);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != con) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return table;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		StrategyFactory.register("Mysql", this);
	}
}
