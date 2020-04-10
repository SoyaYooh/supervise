package com.linkcheers.supervise.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkcheers.supervise.SuperMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * 通用Service
 *
 * @author Soya
 * @param <T>
 */
public class BaseService<T,E extends SuperMapper<T>> implements IService<T,E> {
    @Autowired
	protected E mapper;

	@Override
	public T selectByKey(Serializable key) {
		return mapper.selectById(key);
	}

	@Override
	public int save(T entity) {
		return mapper.insert(entity);
	}

	@Override
	public int delete(Serializable id) {
		return mapper.deleteById(id);
	}

	@Override
	public int updateAll(T entity) {
		return mapper.updateById(entity);
	}

	@Override
	public int updateByCondition(T entity, Wrapper<T> updateWrapper) {
		return mapper.update(entity, updateWrapper);
	}


	@Override
	public IPage<T> selectPage(Page<T> page, QueryWrapper<T> selectWrapper) {
		return mapper.selectPage(page, selectWrapper);
	}

	@Override
	public List<T> selectList(QueryWrapper<T> selectWrapper) {
		return mapper.selectList(selectWrapper);
	}
}

