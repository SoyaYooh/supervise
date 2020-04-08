package com.linkcheers.supervise.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkcheers.supervise.SuperMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * 通用Service
 *
 * @param <T>
 */
public class BaseService<T,E extends SuperMapper<T>> implements IService<T,E> {
    @Autowired
	protected E mapper;

	@Override
	public T selectByKey(Serializable key) {
		//说明：根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
		return mapper.selectById(key);
	}

	@Override
	public int save(T entity) {
		//说明：保存一个实体，null的属性也会保存，不会使用数据库默认值
		return mapper.insert(entity);
	}

	@Override
	public int delete(Serializable id) {
		//说明：根据主键字段进行删除，方法参数必须包含完整的主键属性
		return mapper.deleteById(id);
	}

	@Override
	public int updateAll(T entity) {
		//说明：根据主键更新实体全部字段，null值会被更新
		return mapper.updateById(entity);
	}

	@Override
	public int updateByCondition(T entity, Wrapper<T> updateWrapper) {
		//根据主键更新属性不为null的值
		return mapper.update(entity, updateWrapper);
	}

	@Override
	public IPage<T> selectPageByCondition(Page<T> page, QueryWrapper<T> selectWrapper) {
		//说明：根据Example条件进行查询
		//重点：这个查询支持通过Example类指定查询列，通过selectProperties方法指定查询列
		return mapper.selectPage(page, selectWrapper);
	}

	@Override
	public IPage<T> selectPage(Page<T> page, QueryWrapper<T> selectWrapper) {
		//说明：根据Example条件进行查询
		//重点：这个查询支持通过Example类指定查询列，通过selectProperties方法指定查询列
		return mapper.selectPage(page, selectWrapper);
	}
}

