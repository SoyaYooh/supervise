package com.linkcheers.supervise.dto;

/**
 * 异步请求返回参数封装
 * @author shirley.chen
 *
 */
public class ResultMsg {
	/** 返回状态 ,true：成功**/
	private int code;
	/** 返回信息 **/
	private String msg;
	/** 返回参数 **/
	private Object data;
	/** 状态 **/
	private boolean status;
	/** token **/
	private String token;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
