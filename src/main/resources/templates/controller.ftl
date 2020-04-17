package ${entity.packageName};

import com.common.dto.ResultMsg;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.common.dto.PageDto;
import ${entity.packageName}.dto.${entity.table.entityName};
import ${entity.packageName}.service.I${entity.table.entityName}Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
<#if entity.ormType??>
    <#if entity.ormType=="mybatis"||entity.ormType=="jpa">
import java.util.UUID;
        <#if entity.ormType=="mybatis">
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
        </#if>
import org.springframework.data.domain.Pageable;
    </#if>
    <#if entity.ormType=="mybatis-plus">
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linkcheers.supervise.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    </#if>
</#if>
/**
 * @author ${entity.author}
 * @date ${entity.date}
 * @description
 */

@RestController
@RequestMapping("/${entity.table.entityName}")
<#if entity.isSwagger?exists>
	<#if entity.isSwagger=="Y">
        <#if entity.table.tableComments?exists>
@Api(tags = {"${entity.table.tableComments}"})
        </#if>
	</#if>
</#if>
@Slf4j
public class ${entity.table.entityName}Controller{
	@Autowired
	private I${entity.table.entityName}Service service;
<#if entity.method??>
	<#if entity.method?contains("add")>
    /**
	 * 新增
	 * @return
	 */
	@RequestMapping("/add${entity.table.entityName}")
    @ResponseBody
		<#if entity.isSwagger?exists>
			<#if entity.isSwagger=="Y">
                <#if entity.table.tableComments?exists>
   	@ApiOperation("新增${entity.table.tableComments}")
                </#if>
			</#if>
		</#if>
	public ResultMsg add${entity.table.entityName}(${entity.table.entityName} vo){
		ResultMsg result = new ResultMsg();
		try {
	        service.add${entity.table.entityName}(vo);
            result.setCode(true);
            result.setMsg("新增成功！");
		} catch (Exception e) {
			e.printStackTrace();
            result.setCode(false);
            result.setMsg("新增失败！");
            return result;
		}
		return result;
	}
    </#if>
	<#if entity.method?contains("delete")>
     /**
	 * 删除
	 * @return
	 */
	@RequestMapping("/remove${entity.table.entityName}")
    @ResponseBody
		<#if entity.isSwagger?exists>
			<#if entity.isSwagger=="Y">
                <#if entity.table.tableComments?exists>
   	@ApiOperation("删除${entity.table.tableComments}")
                </#if>
			</#if>
		</#if>
	public ResultMsg remove${entity.table.entityName}(${entity.table.entityName} vo){
		ResultMsg result = new ResultMsg();
		try {
	        service.remove${entity.table.entityName}(vo);
            result.setCode(true);
            result.setMsg("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
            result.setCode(false);
            result.setMsg("删除失败！");
            return result;
		}
		return result;
	}
  </#if>
	<#if entity.method?contains("query")>
    /**
	 * 查询
	 *
	 * @return
	 */
	@RequestMapping("/get${entity.table.entityName}List")
	@ResponseBody
		<#if entity.isSwagger?exists>
			<#if entity.isSwagger=="Y">
                <#if entity.table.tableComments?exists>
   	@ApiOperation("查询${entity.table.tableComments}")
                </#if>
			</#if>
		</#if>
	public ResultMsg get${entity.table.entityName}List(${entity.table.entityName} vo,Page page) {
        ResultMsg result=new ResultMsg();
        try {
          IPage<${entity.table.entityName}> ${entity.table.entityName?uncap_first}List = service.get${entity.table.entityName}List(vo,page);
          result.setData(${entity.table.entityName?uncap_first}List);
          //result.setData(page != null ? page.toPageInfo() : new PageInfo<>());
         } catch (Exception e) {
          e.printStackTrace();
	      result.setCode(false);
          result.setMsg("查询失败！");
           return result;
         }
       return result;
    }
	</#if>
</#if>
}
