package com.linkcheers.supervise.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		//获取到需要被填充的字段的值
		Object fieldValue = getFieldValByName("createUser", metaObject);
		//SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
		if (fieldValue == null) {
			System.out.println("*******插入操作 满足填充条件*********");
			setFieldValByName("createUser", "weiyunhui", metaObject);
			Date date = new Date();
			setFieldValByName("createTime", date, metaObject);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		Object fieldValue = getFieldValByName("edit_user", metaObject);
		if (fieldValue == null) {
			System.out.println("*******修改操作 满足填充条件*********");
			setFieldValByName("editUser", "weiyh", metaObject);
			Date date = new Date();
			setFieldValByName("editTime", date, metaObject);
		}
	}
}
