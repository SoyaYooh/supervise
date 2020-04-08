package com.linkcheers.supervise.freemarker.schema;
import com.linkcheers.supervise.freemarker.db.Cloumns;
import com.linkcheers.supervise.freemarker.db.Convert;
import com.linkcheers.supervise.freemarker.db.Table;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bxk
 */
@Component
public class OracleHandle implements DataSourceHandle, InitializingBean {
	private static String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
	private static String DATABASE_URL = "jdbc:oracle:thin:@120.27.144.145:1521:ORCL";
	private static String DATABASE_URL_PREFIX = "jdbc:oracle:thin:@";
	private static String DATABASE_USER = "supervision";
	private static String DATABASE_PASSWORD = "lq_supervision";
	private static Connection con = null;
	private static DatabaseMetaData dbmd = null;


	@Override
	public void init(String url, String user, String pw) {
		try {
			DATABASE_URL = DATABASE_URL_PREFIX + url;
			DATABASE_USER = user;
			DATABASE_PASSWORD = pw;
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
			ResultSet rs = dbmd.getTables("null", DATABASE_USER.toUpperCase(), "%", new String[]{"TABLE"});
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
		List<String> columns = new ArrayList<String>();
		try {
			ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
			while (rs.next()) {
				columns.add(rs.getString("COLUMN_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return columns;
	}

	@Override
	public Table getTableCloumns(String tableName) {
		Table table = new Table();
		List<Cloumns> columns = new ArrayList<Cloumns>();
		try {
			Statement stmt = con.createStatement();

			String sql =
					"select " +
							"         comments as \"comments\"," +
							"         a.COLUMN_NAME \"columnName\"," +
							"         a.DATA_TYPE as \"dataType\"," +
							"         b.comments as \"comment\"," +
							"         decode(c.column_name,null,'false','true') as \"isKey\"," +
							"         decode(a.NULLABLE,'N','true','Y','false','') as \"notNull\"," +
							"         '' \"sequence\"" +
							"   from " +
							"       all_tab_columns a, " +
							"       all_col_comments b," +
							"       (" +
							"        select a.constraint_name, a.column_name" +
							"          from user_cons_columns a, user_constraints b" +
							"         where a.constraint_name = b.constraint_name" +
							"               and b.constraint_type = 'P'" +
							"               and a.table_name = '" + tableName + "'" +
							"       ) c" +
							"   where " +
							"     a.Table_Name=b.table_Name " +
							"     and a.column_name=b.column_name" +
							"     and a.Table_Name='" + tableName + "'" +
							"     and a.owner=b.owner " +
							"     and a.owner='" + DATABASE_USER.toUpperCase() + "'" +
							"     and a.COLUMN_NAME = c.column_name(+)" +
							"  order by a.COLUMN_ID";
			String cmSql = "select b.comments as \"tableComments\" " +
					"  from user_tables a,user_tab_comments b where a.table_name=b.table_name" +
					"  and a.table_name='" + tableName + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Cloumns cloumns = new Cloumns();
				cloumns.setColumnName(rs.getString("columnName"));
				cloumns.setDataType(rs.getString("dataType"));
				cloumns.setComment(rs.getString("comment"));
				cloumns.setIsKey(rs.getString("isKey"));
				cloumns.setNotNull(rs.getString("notNull"));
				cloumns.setComments(rs.getString("comments"));
				cloumns.setFieldName(Convert.fieldToProperty(rs.getString("columnName")));
				cloumns.setFieldType(Convert.processTypeOracleConvert(rs.getString("dataType")).getType());
				cloumns.setJdbcType(Convert.processTypeJdbcConvert(rs.getString("dataType")).getType());
				columns.add(cloumns);
			}
			ResultSet cmRs = stmt.executeQuery(cmSql);
			if (cmRs.next()) {
				table.setTableComments(cmRs.getString("tableComments"));
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
		StrategyFactory.register("Oracle", this);
	}
}
