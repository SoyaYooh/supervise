package com.linkcheers.supervise.esaypoi.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "BATCH_ATTACH")
public class ExportTask  {

	@Excel(name="用户名")
	private String username;
	private String password;
	@Excel(name="邮件",width = 25)
	private String email;
	@Excel(name="年纪")
	private Integer age;
	@Excel(name = "头像",type = 2,savePath = "/images/head",height = 23)
	private String headImage;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
