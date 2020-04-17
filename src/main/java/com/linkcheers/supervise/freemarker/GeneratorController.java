package com.linkcheers.supervise.freemarker;

import com.linkcheers.supervise.dto.ResultMsg;
import com.linkcheers.supervise.freemarker.db.Condition;
import com.linkcheers.supervise.freemarker.db.Table;
import com.linkcheers.supervise.freemarker.schema.DataSourceHandle;
import com.linkcheers.supervise.freemarker.schema.StrategyFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bxk
 */
@Controller
@RequestMapping("/generator")
public class GeneratorController {
	/**
	 * 生成代码
	 *
	 * @param condition
	 * @return
	 */
	@RequestMapping("/generateCode")
	@ResponseBody
	public ResultMsg generateCode(@RequestBody Condition condition) {
		ResultMsg result = new ResultMsg();
		Map<String, Object> root = new HashMap<String, Object>();
		try {
			DataSourceHandle strategy = (DataSourceHandle) StrategyFactory.getDataSource(condition.getDataSourceType());
			strategy.init(condition.getUrl(),condition.getUserName(), condition.getPassWord());
			condition.setPackageName(condition.getPackageName());
			condition.setAuthor(condition.getAuthor());
			condition.setDate(condition.getDate());
	/*		Table table = strategy.getTableCloumns(condition.getTableName());
			if(table.getCloumns().size()==0){
				result.setCode(1);
				result.setStatus(false);
				result.setMsg("不存在该表，请核查表名!");
				return result;
			}
			condition.setTable(table);*/
			root.put("entity", condition);
			new Generator().loadTemplate(root);
			result.setCode(1);
			result.setStatus(true);
			result.setMsg("生成成功!");
		} catch (Exception e) {
			result.setCode(-1);
			result.setStatus(false);
			result.setMsg(e.toString());
		}
		return result;
	}
	/**
	 * 获取表信息
	 *
	 * @param condition
	 * @return
	 */
	@RequestMapping("/getTableInfo")
	@ResponseBody
	public ResultMsg getTableInfo(Condition condition) {
		ResultMsg result = new ResultMsg();
		try {
			DataSourceHandle strategy = (DataSourceHandle) StrategyFactory.getDataSource(condition.getDataSourceType());
			strategy.init(condition.getUrl(),condition.getUserName(), condition.getPassWord());
			condition.setPackageName(condition.getPackageName());
			condition.setAuthor(condition.getAuthor());
			condition.setDate(condition.getDate());
			Table table = strategy.getTableCloumns(condition.getTableName());
			result.setCode(1);
			result.setStatus(true);
			result.setData(table);
			return result;
		} catch (Exception e) {
			result.setCode(-1);
			result.setStatus(false);
			result.setMsg(e.toString());
		}
		return result;
	}
	/**
	 * 获取所有表名信息
	 *
	 * @param condition
	 * @return
	 */
	@RequestMapping("/getAllTableName")
	@ResponseBody
	public ResultMsg getAllTableName(Condition condition) {
		ResultMsg result = new ResultMsg();
		try {
			DataSourceHandle strategy = (DataSourceHandle) StrategyFactory.getDataSource(condition.getDataSourceType());
			strategy.init(condition.getUrl(),condition.getUserName(), condition.getPassWord());
			List<String> list = strategy.getTables();
			result.setCode(1);
			result.setStatus(true);
			result.setData(list);
			return result;
		} catch (Exception e) {
			result.setCode(-1);
			result.setStatus(false);
			result.setMsg(e.toString());
		}
		return result;
	}

}
