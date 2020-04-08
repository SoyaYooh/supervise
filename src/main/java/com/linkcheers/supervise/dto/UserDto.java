package com.linkcheers.supervise.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data
@Entity
@Table(name = "system_user")
public class UserDto extends BaseDto {
		@Id
		@GenericGenerator(name = "uuid", strategy = "uuid")
		@GeneratedValue(generator = "uuid")
		private String id;//主键id
		private String idCard;//身份证
		private Date birthday;//出生日期
		private String name;//用户姓名
		private String policeNo;//警号
		private String password;//密码
		private String orgId;//所属组织
		private String orgDB33;//DB33
		private String position;//职位
		private String sex;//性别 0-男 1-女
		private String tel;//电话
		private String email;//邮箱
		private String policeType;//警种
		private String enable;//是否启用 1-启用 0-禁用
		private String address;//办公地址
		private String nation;//民族

//		private Date createTime;//创建时间
//
//		private String createUser;//创建人
//
//		private Date updateTime;//修改时间
//
//		private String updateUser;//修改人
	}
