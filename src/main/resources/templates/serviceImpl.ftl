package ${entity.packageName}.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${entity.packageName}.dto.${entity.table.entityName};
import ${entity.packageName}.mapper.${entity.table.entityName}Mapper;
import ${entity.packageName}.service.I${entity.table.entityName}Service;
import org.springframework.data.domain.Pageable;
import com.github.pagehelper.Page;
/**
 * @author ${entity.author}
 * @date ${entity.date}
 * @description
 */
@Service
public class ${entity.table.entityName}ServiceImpl implements I${entity.table.entityName}Service{

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
}
