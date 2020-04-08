package com.linkcheers.supervise.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.List;

/**
 * @author Soya
 */
@MappedSuperclass
@Data
public abstract class BaseTree<T> extends BaseDto<T> {
	@JsonIgnore
	protected String id;
	@JsonIgnore
	protected String pid;
	@JsonIgnore
	protected T t;
	protected List<T> child;
}