package com.linkcheers.supervise.file.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.linkcheers.supervise.dto.BaseDto;
import lombok.Data;

import java.util.Date;

/**
 * @author Soya
 */
@Data
@TableName("T_SYSTEM_BATCH_ATTACH")
public class BatchAttach extends BaseDto<BatchAttach> {
	@TableId(value = "ID", type = IdType.UUID)
	private String id;

	@TableField(value = "MODULE_ID")
	@Excel(name = "关联的模块记录ID")
	private String moduleId;

	@TableField(value = "MODULE_TYPE")
	@Excel(name = "模块类型")
	private String moduleType;

	@TableField(value = "BUS_TYPE")
	@Excel(name = "业务类型")
	private String busType;

	@TableField(value = "ATTACH_TYPE")
	@Excel(name = "附件类型")
	private String attachType;

	@TableField(value = "ATTACH_DESC")
	@Excel(name = "附件描述")
	private String attachDesc;

	@TableField(value = "UP_FILE_NAME")
	@Excel(name = "上传文件名称")
	private String upFileName;

	@TableField(value = "FTP_FILE_NAME")
	@Excel(name = "FTP文件名称")
	private String ftpFileName;

	@TableField(value = "LOCAL_PATH")
	@Excel(name = "本地存放路径")
	private String localPath;

	@TableField(value = "FTP_PATH")
	@Excel(name = "远程基础路径")
	private String ftpPath;


	@TableField(value = "SIZE")
	@Excel(name = "附件大小")
	private String size;
}
