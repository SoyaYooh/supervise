package ${entity.packageName}.dto;
<#if entity.isLombok?exists>
    <#if entity.isLombok=="Y">
import lombok.Data;
    </#if>
</#if>
import com.linkcheers.supervise.dto.BaseDto;
<#--<#if entity.isSwagger?exists>
    <#if entity.isSwagger=="Y">
import io.swagger.annotations.ApiModelProperty;
    </#if>
</#if>-->
import java.util.Date;
<#if entity.ormType??>
<#if entity.ormType=="jpa">
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
</#if>
<#if entity.ormType=="mybatis-plus">
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
</#if>
</#if>
/**
 * @author ${entity.author}
 * @date   ${.now}
 */
<#if entity.isLombok?exists>
    <#if entity.isLombok=="Y">
@Data
    </#if>
</#if>
<#if entity.ormType?exists>
    <#if entity.ormType=="jpa">
@Entity
@Table(name = "${entity.table.tableName}")
    </#if>
    <#if entity.ormType=="mybatis-plus">
@TableName("${entity.table.tableName}")
    </#if>
</#if>
public class ${entity.table.entityName} extends BaseDto<${entity.table.entityName}>{
 /********** 属性 ***********/
<#list entity.table.cloumns as property>
    <#if property.comment?exists>
     /**
	 * ${property.comment}
	 */
    </#if>
    <#if property.isKey=="true">
        <#if entity.ormType?exists>
            <#if entity.ormType=="jpa">
    @Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
            </#if>
            <#if entity.ormType=="mybatis-plus">
   @TableId(value = "${property.columnName}",type= IdType.UUID)
            </#if>
        </#if>
    </#if>
    <#if property.isKey=="false">
        <#if entity.ormType?exists>
            <#if entity.ormType=="jpa">
   	@Column(name = "${property.columnName}")
            </#if>
            <#if entity.ormType=="mybatis-plus">
  	@TableField(value = "${property.columnName}")
            </#if>
        </#if>
    </#if>
<#--    <#if entity.isSwagger?exists>
        <#if entity.isSwagger=="Y">
    @ApiModelProperty(value = "${property.columnName}")
        </#if>
    </#if>-->
    private ${property.fieldType} ${property.fieldName};
</#list>
<#if entity.isLombok?exists>
    <#if entity.isLombok=="N">
     /********** get/set ***********/
        <#list entity.table.cloumns as property>
    public ${property.fieldType} get${property.fieldName?cap_first}() {
        return ${property.fieldName};
    }
    public void set${property.fieldName?cap_first}(${property.fieldType} ${property.fieldName}) {
        this.${property.fieldName} = ${property.fieldName};
    }
        </#list>
    </#if>
</#if>
}