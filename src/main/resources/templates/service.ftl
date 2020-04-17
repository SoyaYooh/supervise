package ${entity.packageName}.service;

<#if entity.ormType??>
	<#if entity.ormType=="mybatis"||entity.ormType=="jpa">
		<#if entity.ormType=="mybatis">
import com.github.pagehelper.Page;
		</#if>
import org.springframework.data.domain.Pageable;
	</#if>
	<#if entity.ormType=="mybatis-plus">
import ${entity.packageName}.dto.${entity.table.entityName}Mapper;
import com.linkcheers.supervise.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
	</#if>
</#if>
import ${entity.packageName}.dto.${entity.table.entityName};
/**
 * @author ${entity.author}
 * @date ${entity.date}
 * @description
 */
public  interface I${entity.table.entityName}Service<#if entity.ormType??><#if entity.ormType=="mybatis-plus"> extends IService<${entity.table.entityName},${entity.table.entityName}Mapper></#if></#if>{
<#if entity.method??>
	<#if entity.method?contains("add")>
    /**
	 * 新增<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>接口
	 * @param  vo
	 * @return
	 */
	void add${entity.table.entityName}(${entity.table.entityName} vo);
    </#if>
	<#if entity.method?contains("delete")>
	/**
	 * 删除<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>接口
	 * @param  vo
	 * @return
	 */
	void remove${entity.table.entityName}(${entity.table.entityName} vo);
	</#if>
	<#if entity.method?contains("query")>
   /**
	 * 查询<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>列表接口
	 * @param  vo
     * @param  pageable
	 * @return
    */
   Page<${entity.table.entityName}> get${entity.table.entityName}List(${entity.table.entityName} vo,Page page);
	 /**
	 * 查询<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>接口
	 * @param  vo
	 * @return
    */
	${entity.table.entityName} get${entity.table.entityName}(${entity.table.entityName} vo);
   </#if>
</#if>
}