package com.linkcheers.supervise.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 通用接口
 */
@Service
public interface IService<T,E> {

	T selectByKey(Serializable key);

	int save(T entity);

	int delete(Serializable key);

	int updateAll(T entity);

	int updateByCondition(T entity,Wrapper<T> updateWrapper) ;

	public IPage<T> selectPageByCondition(Page<T> page , QueryWrapper<T> selectWrapper);

	public IPage<T> selectPage(Page<T> page , QueryWrapper<T> selectWrapper);

}