package com.linkcheers.supervise.dto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

public  class QueryCondition{


	public <T>QueryWrapper<T> QueryCondition(T t, List< QueryParam> param){
		QueryWrapper <T>queryWrapper = new QueryWrapper<T>();
		if(param==null)return queryWrapper;
		param.forEach(q->{
			String column=q.getKey();
			String val=q.getValue();
			String opration=q.getOperation();
			String orAnd=q.getOrAnd();
			if(opration.equals("eq")){
				if(orAnd.equals("and")){
					// 嵌套and
					queryWrapper.and(wrapper -> wrapper.eq(column, val));

				}
				else if(orAnd.equals("or")){
					queryWrapper.or(wrapper -> wrapper.eq(column, val));
				}else{
					queryWrapper.eq(column, val);
				}
			}
		});
		return queryWrapper;

	}

}