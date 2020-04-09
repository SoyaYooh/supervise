package ${entity.packageName}.service;

<#if property.ormType??>
	<#if property.ormType=="mybatis"||property.ormType=="jpa">
		<#if property.ormType=="mybatis">
import com.github.pagehelper.Page;
		</#if>
import org.springframework.data.domain.Pageable;
	</#if>
	<#if property.ormType=="mybatis-plus">
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
public  interface I${entity.table.entityName}Service<#if property.ormType??><#if property.ormType=="mybatis-plus"> extends IService<${entity.table.entityName},${entity.table.entityName}Mapper></#if></#if>{
<#if property.ormType??>
<#if property.ormType=="mybatis">
    /**
	 * 新增<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>接口
	 * @param  vo
	 * @return
	 */
	void add${entity.table.entityName}(${entity.table.entityName} vo);

	/**
	 * 删除<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>接口
	 * @param  vo
	 * @return
	 */
	void remove${entity.table.entityName}(${entity.table.entityName} vo);


	/**
	 * 修改<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>接口
	 * @param  vo
	 * @return
	 */
	void edit${entity.table.entityName}(${entity.table.entityName} vo);


   /**
	 * 查询<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>列表接口
	 *
	 * @param  vo
     * @param  pageable
	 * @return
    */
   Page<${entity.table.entityName}> get${entity.table.entityName}List(${entity.table.entityName} vo, Pageable pageable);

   /**
	 * 查询<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>接口
	 *
	 * @param  vo
	 * @return
    */
   ${entity.table.entityName} get${entity.table.entityName}(${entity.table.entityName} vo);

	/**
	 * 获取<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>总数
	 * @return
	 */
	Integer get${entity.table.entityName}Count(${entity.table.entityName} vo);
</#if>
</#if>
}