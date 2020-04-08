package com.linkcheers.supervise.freemarker.schema;
import com.linkcheers.supervise.freemarker.db.Table;
import java.util.List;

/**
 * @author bxk
 */
public interface DataSourceHandle {

	public void init(String url, String user, String pw);

	public List<String> getTables();

	public List<String> getColumns(String tableName);

	public Table getTableCloumns(String tableName);
}