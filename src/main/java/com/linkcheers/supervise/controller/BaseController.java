package com.linkcheers.supervise.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.linkcheers.supervise.dto.ResultMsg;
import com.linkcheers.supervise.file.util.FileClient;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public abstract class BaseController<T> {

	public static final Integer EXPORT_MAX = 10000;

	@Value("${win.upload.local.path}")
	private String uploadPath;
	@Value("${win.downLoad.local.path}")
	private String downLoadPath;

	/**
	 * 上传Excel
	 *
	 * @return
	 */
	@RequestMapping("/importExcel")
	@ResponseBody
	public ResultMsg importExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response, T t) throws Exception {
		ResultMsg msg = new ResultMsg();
		try {
			ImportParams params = new ImportParams();
			//设置从第一行开始导入
			params.setTitleRows(1);
			//启动导入时的一个验证功能
			params.setNeedVerfiy(true);
			//获取导入Excel的方法
			ExcelImportResult<T> result = ExcelImportUtil.importExcelMore(file.getInputStream(), t.getClass(), params);
			if (result.isVerfiyFail()) {
				Workbook workbook = result.getFailWorkbook();
				String fileName = "导入失败" + DateUtil.getExcelDate(new Date());
				renderExcel(request, response, fileName, workbook);
				response.setContentType("text/html;charset=utf-8");
			} else {
				//TODO: 插入业务数据
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter().write(e.getMessage());
			} catch (Exception e2) {

			}
		}
		msg.setMsg("导入成功");
		msg.setStatus(true);
		return msg;
	}

	/**
	 * 导出Excel文件
	 */
	@RequestMapping(value = "/doExport")
	public void doExport(HttpServletRequest request, HttpServletResponse response, T t) throws Exception {
		try {
			/*
			有排序的时候
			final PageRequest page2 = new PageRequest(
					0, 20, new Sort(
					new Sort.Order(Sort.Direction.ASC, "lastName"),
					new Sort.Order(Sort.Direction.DESC, "salary"))
			);*/
			//查询条数
			int importNum = 200;
			Workbook workbook = null;
			//第一个是导出的接口
			ExportParams exportParams = new ExportParams("", "导出");
			if (importNum > EXPORT_MAX) {
				for (int i = 0; i < importNum / EXPORT_MAX + 1; i++) {
					List<T> result = new ArrayList<T>();
					workbook = ExcelExportUtil.exportBigExcel(exportParams, t.getClass(), result);
					result.clear();
				}
				ExcelExportUtil.closeExportBigExcel();
			} else {
				List<T> result = new ArrayList<T>();
				workbook = ExcelExportUtil.exportExcel(exportParams, t.getClass(), result);
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
	 * 上传附件
	 *
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public ResultMsg upload(MultipartFile file) {
		ResultMsg result = new ResultMsg();
		try {
			FileClient.uploadFile(file, uploadPath, file.getName());
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setMsg("查询失败！");
			return result;
		}
		return result;
	}

	/**
	 * 下载附件
	 *
	 * @return
	 */
	@RequestMapping("/downLoad")
	@ResponseBody
	public void downLoad(HttpServletResponse response, String fileName) {
		try {
			FileClient.download(response, downLoadPath, fileName);
		} catch (Exception e) {
			e.printStackTrace();
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
