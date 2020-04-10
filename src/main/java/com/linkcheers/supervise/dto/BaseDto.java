package com.linkcheers.supervise.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

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
	@ApiModelProperty(hidden = true,readOnly=true)
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	protected Date createTime;
	@ApiModelProperty(hidden = true,readOnly=true)
	@TableField(value = "create_user", fill = FieldFill.INSERT)
	protected String createUser;
	@ApiModelProperty(hidden = true,readOnly=true)
	@TableField(value = "edit_time", fill = FieldFill.UPDATE)
	protected Date editTime;
	@ApiModelProperty(hidden = true,readOnly=true)
	@TableField(value = "edit_user", fill = FieldFill.UPDATE)
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
