package ${entity.packageName}.dto;
<#if entity.isLombok?exists>
    <#if entity.isLombok=="Y">
import lombok.Data;
    </#if>
</#if>
import com.common.dto.OriginalDto;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
<#if property.ormType??>
<#if property.ormType=="jpa">
import java.util.UUID;
</#if>
<#if property.ormType=="mybatis-plus">
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
</#if>
</#if>
/**
 * @author ${entity.author}
 * @date ${entity.date}
 */
<#if entity.isLombok?exists>
    <#if entity.isLombok=="Y">
@Data
    </#if>
</#if>
@Entity
@Table(name = "${entity.table.tableName}")
public class ${entity.table.entityName} extends BaseDto<${entity.table.entityName}>{
 /********** 属性 ***********/
<#list entity.table.cloumns as property>
    <#if property.comment?exists>
     /**
	 * ${property.comment}
	 */
    </#if>
    <#if property.isKey=="true">
        <#if property.ormType??>
        <#if property.ormType=="jpa">
    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
        </#if>
        <#if property.ormType=="mybatis-plus">
 	@TableId(value = "ID",type= IdType.UUID)
        </#if>
    </#if>
    <#if property.ormType=="mybatis-plus">
    @TableField(value = "${property.columnName}")
    </#if>
    <#if property.ormType=="jpa">
    @Column(name = "${property.columnName}")
    </#if>
    </#if>
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