package com.linkcheers.supervise.freemarker;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.linkcheers.supervise.dto.ResultMsg;
import com.linkcheers.supervise.freemarker.db.Cloumns;
import com.linkcheers.supervise.freemarker.db.Condition;
import com.linkcheers.supervise.freemarker.db.Table;
import com.linkcheers.supervise.freemarker.schema.DataSourceHandle;
import com.linkcheers.supervise.freemarker.schema.StrategyFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author bxk
 */
@Controller
@RequestMapping("/generator")
public class GeneratorController {

	public static final Integer EXPORT_MAX = 10000;
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
	/**
	 * 导出Excel文件
	 */
	@RequestMapping(value = "/doExport")
	@ResponseBody
	public void doExport(Condition condition, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			//查询条数
			int importNum = 200;
			Workbook workbook = null;
			//第一个是导出的接口
			ExportParams exportParams = new ExportParams("test", "导出");
			if (importNum > EXPORT_MAX) {
				for (int i = 0; i < importNum / EXPORT_MAX + 1; i++) {
					DataSourceHandle strategy = (DataSourceHandle) StrategyFactory.getDataSource(condition.getDataSourceType());
					strategy.init(condition.getUrl(),condition.getUserName(), condition.getPassWord());
					Table table = strategy.getTableCloumns(condition.getTableName());
					List<Cloumns> result = table.getCloumns();
					workbook = ExcelExportUtil.exportBigExcel(exportParams, Cloumns.class, result);
					result.clear();
				}
				ExcelExportUtil.closeExportBigExcel();
			} else {
				DataSourceHandle strategy = (DataSourceHandle) StrategyFactory.getDataSource(condition.getDataSourceType());
				strategy.init(condition.getUrl(),condition.getUserName(), condition.getPassWord());
				Table table = strategy.getTableCloumns(condition.getTableName());
				List<Cloumns> result = table.getCloumns();
				workbook = ExcelExportUtil.exportExcel(exportParams,Cloumns.class, result);
			}
			String filename = "(" + DateUtil.getExcelDate(new Date()) + ")";
			renderExcel(request, response, filename, workbook);
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter().write(e.getMessage());
			} catch (Exception e2) {

			}
		}
	}
	/**
	 * 判断是否为IE浏览器
	 *
	 * @param request
	 * @return
	 */
	protected static boolean isIE(HttpServletRequest request) {
		return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 || request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0 || request.getHeader("USER-AGENT").toLowerCase().indexOf("edge") > 0;
	}
	protected void renderExcel(HttpServletRequest request, HttpServletResponse response, String filename, Workbook workbook) throws IOException {

		if (workbook instanceof HSSFWorkbook) {
			filename = filename + ".xls";
		} else {
			filename = filename + ".xlsx";
		}
		if (isIE(request)) {
			filename = URLEncoder.encode(filename, "UTF8");
		} else {
			filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.setHeader("content-disposition", "attachment;filename=" + filename);
		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
	}
}
