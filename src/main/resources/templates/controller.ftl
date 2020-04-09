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
<#if property.ormType??>
    <#if property.ormType=="mybatis"||property.ormType=="jpa">
import java.util.UUID;
        <#if property.ormType=="mybatis">
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
        </#if>
import org.springframework.data.domain.Pageable;
    </#if>
    <#if property.ormType=="mybatis-plus">
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
@RequestMapping("/${entity.table.entityName} ")
public class ${entity.table.entityName}Controller{
	@Autowired
	private I${entity.table.entityName}Service service;

    /**
	 * 新增
	 * @return
	 */
	@RequestMapping("/add${entity.table.entityName}")
    @ResponseBody
	public ResultMsg add${entity.table.entityName}(${entity.table.entityName} vo){
		ResultMsg result = new ResultMsg();
		try {
<#if entity.dataSourceType=="Oracle">
            vo.setId(UUID.randomUUID().toString());
</#if>
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

     /**
	 * 删除
	 * @return
	 */
	@RequestMapping("/remove${entity.table.entityName}")
    @ResponseBody
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


     /**
	 * 修改
	 * @return
	 */
	@RequestMapping("/edit${entity.table.entityName}")
    @ResponseBody
	public ResultMsg edit${entity.table.entityName}(${entity.table.entityName} vo){
		ResultMsg result = new ResultMsg();
		try {
	        service.edit${entity.table.entityName}(vo);
            result.setCode(true);
            result.setMsg("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
            result.setCode(false);
            result.setMsg("修改失败！");
            return result;
		}
		return result;
	}
    /**
	 * 查询
	 *
	 * @return
	 */
	@RequestMapping("/get${entity.table.entityName}List")
	@ResponseBody
	public ResultMsg get${entity.table.entityName}List(${entity.table.entityName} vo,Pageable pageable) {
        ResultMsg result=new ResultMsg();
        try {
          Page<${entity.table.entityName}> page = service.get${entity.table.entityName}List(vo,pageable);
          page.forEach(a->{
            //TODO:转义
           });
          result.setData(page != null ? page.toPageInfo() : new PageInfo<>());
         } catch (Exception e) {
          e.printStackTrace();
	      result.setCode(false);
          result.setMsg("查询失败！");
           return result;
         }
       return result;
    }
}