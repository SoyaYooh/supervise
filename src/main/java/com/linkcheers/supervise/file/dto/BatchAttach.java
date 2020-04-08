package com.linkcheers.supervise.file.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "BATCH_ATTACH")
public class BatchAttach {
	private static final long serialVersionUID = 1L;
	@Excel(name = "上传人员")
	private String  userId;
	@Excel(name = "上传时间")
	private Date createTime;
	@Excel(name = "关联的模块记录ID")
	private String moduleRecordId;
	@Excel(name = "模块类型")
	private String moduleType;
	@Excel(name = "业务类型")
	private String busType;
	@Excel(name = "附件类型")
	private String attachType;
	@Excel(name = "文件类型")
	private String fileType;
	@Excel(name = "附件描述")
	private String attachDesc;
	@Excel(name = "上传文件名称")
	private String upFileName;
	@Excel(name = "FTP文件名称")
	private String ftpFileName;
	@Excel(name = "本地存放路径")
	private String localPath;
	@Excel(name = "远程基础路径")
	private String ftpPath;




}
