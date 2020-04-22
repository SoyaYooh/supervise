package com.linkcheers.supervise.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.List;

/**
 * @author Soya
 */
@MappedSuperclass
public abstract class BaseTree<T> extends BaseDto<T> {
	@JsonIgnore
	@TableField(exist = false)
	protected String id;
	@JsonIgnore
	@TableField(exist = false)
	protected String pid;
	@JsonIgnore
	@TableField(exist = false)
	protected boolean permit;
	@JsonIgnore
	@TableField(exist = false)
	protected T t;
	@TableField(exist = false)
	protected List<T> child;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public boolean isPermit() {
		return permit;
	}

	public void setPermit(boolean permit) {
		this.permit = permit;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public List<T> getChild() {
		return child;
	}

	public void setChild(List<T> child) {
		this.child = child;
	}
}