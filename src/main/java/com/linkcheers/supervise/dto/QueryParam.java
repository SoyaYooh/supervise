package com.linkcheers.supervise.dto;

import lombok.Data;

@Data
public class QueryParam {
	public String key;
	public String value;
	public String operation;
	public String orAnd;
}
