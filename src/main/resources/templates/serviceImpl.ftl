package ${entity.packageName}.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${entity.packageName}.dto.${entity.table.entityName};
import ${entity.packageName}.mapper.${entity.table.entityName}Mapper;
import ${entity.packageName}.service.I${entity.table.entityName}Service;
import org.apache.commons.lang3.StringUtils;
<#if entity.ormType??>
    <#if entity.ormType=="mybatis"||entity.ormType=="jpa">
        <#if entity.ormType=="mybatis">
import com.github.pagehelper.Page;
        </#if>
import org.springframework.data.domain.Pageable;
    </#if>
    <#if entity.ormType=="mybatis-plus">
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linkcheers.supervise.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    </#if>
</#if>
/**
 * @author ${entity.author}
 * @date   ${.now}
 * @description
 */
@Service
public class ${entity.table.entityName}ServiceImpl<#if entity.ormType=="mybatis-plus"> extends BaseService<${entity.table.entityName},${entity.table.entityName}Mapper></#if> implements I${entity.table.entityName}Service{
<#if entity.method??>
	@Autowired
	private ${entity.table.entityName}Mapper ${entity.table.entityName?uncap_first}mapper;
    <#if entity.method?contains("add")>
	@Override
	public void add${entity.table.entityName}(${entity.table.entityName} vo) {
		try {
            if(StringUtils.isNotBlank(vo.getId())){
        <#if entity.ormType??>
            <#if entity.ormType=="mybatis-plus">
                this.mapper.insert(vo>);
            </#if>
        </#if>
            }else{
        <#if entity.ormType??>
            <#if entity.ormType=="mybatis-plus">
                ${entity.table.entityName} ${entity.table.entityName?uncap_first}=new ${entity.table.entityName}();
            	UpdateWrapper<${entity.table.entityName}> updateWrapper = new UpdateWrapper<>();
                <#list entity.table.cloumns as cloumns>
                    <#if cloumns.columnName??>
                if(<#if cloumns.fieldType!='String'>vo.get${cloumns.fieldName?cap_first}()!=null</#if><#if cloumns.fieldType=='String'>StringUtils.isNotBlank(vo.get${cloumns.fieldName?cap_first}())</#if>){
                        ${entity.table.entityName?uncap_first}.set${cloumns.fieldName?cap_first}(vo.get${cloumns.fieldName?cap_first}());
                 }
                    </#if>
                </#list>
				updateWrapper.eq("ID", vo.getId());
                this.mapper.update(vo);
            </#if>
        </#if>
            }
		} catch (Exception e) {
				e.printStackTrace();
			throw e;
		}
	}
   </#if>
    <#if entity.method?contains("delete")>
	@Override
	public void remove${entity.table.entityName}(${entity.table.entityName} ${entity.table.entityName?uncap_first}) {
		try {
        <#if entity.ormType??>
            <#if entity.ormType=="mybatis-plus">
              this.mapper.delete(new QueryWrapper<${entity.table.entityName}>());
            </#if>
        </#if>
		} catch (Exception e) {
				e.printStackTrace();
			throw e;
		}
	}
    </#if>
    <#if entity.method?contains("query")>
	@Override
	public IPage<${entity.table.entityName}> get${entity.table.entityName}Page(${entity.table.entityName} ${entity.table.entityName?uncap_first},Page page) {
     IPage<${entity.table.entityName}> IPage;
       try {
        <#if entity.ormType??>
            <#if entity.ormType=="mybatis-plus">
            QueryWrapper<${entity.table.entityName}> wrapper=new QueryWrapper<>();
                <#list entity.table.cloumns as cloumns>
                    <#if cloumns.rule??>
                        <#list cloumns.rule as rule>
                        if(StringUtils.isNotBlank(${entity.table.entityName?uncap_first}.get${cloumns.fieldName?cap_first}())){
                          wrapper.${rule}("${cloumns.columnName}",${entity.table.entityName?uncap_first}.get${cloumns.fieldName?cap_first}());
                        }
                        </#list>
                    </#if>
                </#list>
                <#list entity.table.cloumns as cloumns>
                    <#if cloumns.rule??>
                        <#list cloumns.rule as rule>
                            <#if rule=='sort'>
            wrapper.orderBy("${cloumns.columnName}");
                            </#if>
                        </#list>
                    </#if>
                </#list>
           IPage=this.mapper.selectPage(page,wrapper);
           </#if>
        </#if>
       } catch (Exception e) {
          	e.printStackTrace();
			throw e;
       }
      return IPage;
    }
    @Override
	public List<${entity.table.entityName}> get${entity.table.entityName}List(${entity.table.entityName} ${entity.table.entityName?uncap_first}) {
     List<${entity.table.entityName}> ${entity.table.entityName?uncap_first}List=new ArrayList<>();
       try {
        <#if entity.ormType??>
            <#if entity.ormType=="mybatis-plus">
            QueryWrapper<${entity.table.entityName}> wrapper=new QueryWrapper<>();
                <#list entity.table.cloumns as cloumns>
                    <#if cloumns.rule??>
                        <#list cloumns.rule as rule>
                        if(StringUtils.isNotBlank(${entity.table.entityName?uncap_first}.get${cloumns.fieldName?cap_first}())){
                          wrapper.${rule}("${cloumns.columnName}",${entity.table.entityName?uncap_first}.get${cloumns.fieldName?cap_first}());
                        }
                        </#list>
                    </#if>
                </#list>
                <#list entity.table.cloumns as cloumns>
                    <#if cloumns.rule??>
                        <#list cloumns.rule as rule>
                            <#if rule=='sort'>
            wrapper.orderBy("${cloumns.columnName}");
                            </#if>
                        </#list>
                    </#if>
                </#list>
                ${entity.table.entityName?uncap_first}List=this.mapper.selectList(wrapper);
            </#if>
        </#if>
       } catch (Exception e) {
          	e.printStackTrace();
			throw e;
       }
      return ${entity.table.entityName?uncap_first}List;
    }
    @Override
    public ${entity.table.entityName} get${entity.table.entityName}(${entity.table.entityName} ${entity.table.entityName?uncap_first}) {
       try {
        <#if entity.ormType??>
            <#if entity.ormType=="mybatis-plus">
                QueryWrapper<${entity.table.entityName}> wrapper=new QueryWrapper<>();
                ${entity.table.entityName?uncap_first}=this.mapper.selectOne(wrapper);
            </#if>
        </#if>
       } catch (Exception e) {
         	e.printStackTrace();
			throw e;
    }
       return ${entity.table.entityName?uncap_first};
    }
  </#if>
</#if>
}
