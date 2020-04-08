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
import java.util.UUID;

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
public class ${entity.table.entityName} extends OriginalDto<${entity.table.entityName}>{
    private static final long serialVersionUID = 1L;
 /********** 属性 ***********/
<#list entity.table.cloumns as property>
    <#if property.comment?exists>
     /**
	 * ${property.comment}
	 */
    </#if>
    <#if property.isKey=="true">
    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
    </#if>
    @Column(name = "${property.columnName}")
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
	@Override
	public void preInsert() {
		if (getIsNewRecord()) {
			String uuId = UUID.randomUUID().toString();
			setId(uuId);
		}
	}

	@Override
	public void preInsert(Long id) {
		if (getIsNewRecord()) {
			setId(String.valueOf(id));
		}
	}

	@Transient
	@JsonIgnore
	@Override
	public boolean getIsNewRecord() {
		return isNewRecord || StringUtils.isBlank(getId());
	}
}