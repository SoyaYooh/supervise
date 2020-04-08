package ${entity.packageName}.mapper;

import ${entity.packageName}.dto.${entity.table.entityName} ;
import org.apache.ibatis.annotations.Mapper;
import com.github.pagehelper.Page;
import org.springframework.data.domain.Pageable;
/**
 * @author ${entity.author}
 * @date ${entity.date}
 * @description
 */
@Mapper
public  interface ${entity.table.entityName}Mapper{

	/**
	 * 新增<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>数据
	 * @param  vo
	 * @return
	 */
	void add${entity.table.entityName}(${entity.table.entityName} vo);

	/**
	 * 删除<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>数据
	 * @param  vo
	 * @return
	 */
	void remove${entity.table.entityName}(${entity.table.entityName} vo);

	/**
	 * 修改<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>数据
	 * @param  vo
	 * @return
	 */
	void edit${entity.table.entityName}(${entity.table.entityName} vo);

    /**
	 * 查询<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>数据
	 * @param  vo
	 * @return
     */
    ${entity.table.entityName} get${entity.table.entityName}(${entity.table.entityName} vo);

    /**
	 * 查询<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>列表
	 *
	 * @param  vo
	 * @param  pageable
	 * @return
     */
     Page<${entity.table.entityName}> get${entity.table.entityName}List(${entity.table.entityName} vo,Pageable pageable);

     /**
	 * 查询<#if entity.table.tableComments?exists>${entity.table.tableComments}</#if>数据
	 *
	 * @param  vo
	 * @return
     */
     Integer get${entity.table.entityName}Count(${entity.table.entityName} vo);
}