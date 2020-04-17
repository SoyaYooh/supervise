package ${entity.packageName}.service;

<#if entity.ormType??>
	<#if entity.ormType=="mybatis"||entity.ormType=="jpa">
		<#if entity.ormType=="mybatis">
import com.github.pagehelper.Page;
		</#if>
import org.springframework.data.domain.Pageable;
	</#if>
	<#if entity.ormType=="mybatis-plus">
import ${entity.packageName}.mapper.${entity.table.entityName}Mapper;
import com.linkcheers.supervise.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
	</#if>
</#if>
import java.util.List;
import ${entity.packageName}.dto.${entity.table.entityName};
/**
 * @author ${entity.author}
 * @date   ${.now}
 * @description
 */
public  interface I${entity.table.entityName}Service<#if entity.ormType??><#if entity.ormType=="mybatis-plus"> extends IService<${entity.table.entityName},${entity.table.entityName}Mapper></#if></#if>{
<#if entity.method??>
	<#if entity.method?contains("add")>
    /**
	 * 新增<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>接口
	 * @param  ${entity.table.entityName?uncap_first}
	 * @return
	 */
	void add${entity.table.entityName}(${entity.table.entityName} ${entity.table.entityName?uncap_first});
    </#if>
	<#if entity.method?contains("delete")>
	/**
	 * 删除<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>接口
	 * @param  ${entity.table.entityName?uncap_first}
	 * @return
	 */
	void remove${entity.table.entityName}(${entity.table.entityName} ${entity.table.entityName?uncap_first});
	</#if>
	<#if entity.method?contains("query")>
   /**
	 * 查询<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>列表接口
	 * @param  ${entity.table.entityName?uncap_first}
     * @param  page
	 * @return
    */
   IPage<${entity.table.entityName}> get${entity.table.entityName}Page(${entity.table.entityName} ${entity.table.entityName?uncap_first},Page page);
	 /**
	 * 查询<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>列表接口
	 * @param  ${entity.table.entityName?uncap_first}
	 * @return
    */
   List<${entity.table.entityName}> get${entity.table.entityName}List(${entity.table.entityName} ${entity.table.entityName?uncap_first});
	 /**
	 * 查询<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>接口
	 * @param  vo
	 * @return
    */
	${entity.table.entityName} get${entity.table.entityName}(${entity.table.entityName} vo);
   </#if>
</#if>
}