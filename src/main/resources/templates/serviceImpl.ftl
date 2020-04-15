package ${entity.packageName}.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${entity.packageName}.dto.${entity.table.entityName};
import ${entity.packageName}.mapper.${entity.table.entityName}Mapper;
import ${entity.packageName}.service.I${entity.table.entityName}Service;
<#if entity.ormType??>
    <#if entity.ormType=="mybatis"||entity.ormType=="jpa">
        <#if entity.ormType=="mybatis">
import com.github.pagehelper.Page;
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
@Service
public class ${entity.table.entityName}ServiceImpl<#if entity.ormType=="mybatis-plus"> extends BaseService<${entity.table.entityName},${entity.table.entityName}Mapper></#if> implements I${entity.table.entityName}Service{
<#if entity.ormType=="mybatis">
	@Autowired
	private ${entity.table.entityName}Mapper mapper;

	@Override
	public void add${entity.table.entityName}(${entity.table.entityName} vo) {
		try {
            vo.preInsert();
			mapper.add${entity.table.entityName}(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public  void edit${entity.table.entityName}(${entity.table.entityName} vo) {
		try {
			mapper.edit${entity.table.entityName}(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove${entity.table.entityName}(${entity.table.entityName} vo) {
		try {
			mapper.remove${entity.table.entityName}(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Page<${entity.table.entityName}> get${entity.table.entityName}List(${entity.table.entityName} vo,Pageable pageable) {
       try {
           return mapper.get${entity.table.entityName}List(vo,pageable);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }

    @Override
    public ${entity.table.entityName} get${entity.table.entityName}(${entity.table.entityName} vo) {
       try {
           return mapper.get${entity.table.entityName}(vo);
       } catch (Exception e) {
         e.printStackTrace();
    }
       return null;
    }

    @Override
    public Integer get${entity.table.entityName}Count(${entity.table.entityName} vo) {
       try {
          return mapper.get${entity.table.entityName}Count(vo);
       } catch (Exception e) {
         e.printStackTrace();
       }
        return 0;
    }
</#if>
}
