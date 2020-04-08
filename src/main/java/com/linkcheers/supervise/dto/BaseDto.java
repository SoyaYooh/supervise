package com.linkcheers.supervise.dto;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Soya
 */
@MappedSuperclass
public abstract class BaseDto<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Transient
	@TableField(value = "create_time", strategy = FieldStrategy.NOT_EMPTY)
	protected Date createTime;
	@Transient
	@TableField(value = "create_user", strategy = FieldStrategy.NOT_EMPTY)
	protected String createUser;
	@Transient
	@TableField(value = "edit_time", strategy = FieldStrategy.NOT_EMPTY)
	protected Date editTime;
	@Transient
	@TableField(value = "edit_user", strategy = FieldStrategy.NOT_EMPTY)
	protected String editUser;


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getEditUser() {
		return editUser;
	}

	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}

}
