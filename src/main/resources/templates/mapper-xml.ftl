<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${entity.packageName}.mapper.${entity.table.entityName}Mapper">
    <resultMap id="BaseResultMap" type="${entity.packageName}.dto.${entity.table.entityName}">
        <#list entity.table.cloumns as property><#if property.isKey ="true"><id column="${property.columnName}" jdbcType="${property.jdbcType}" property="${property.fieldName}"/></#if ><result column="${property.columnName}" jdbcType="${property.jdbcType}" property="${property.fieldName}"/></#list>
    </resultMap>
    <!--新增-->
    <insert id="add${entity.table.entityName}" parameterType="${entity.packageName}.dto.${entity.table.entityName}">
        insert into ${entity.table.tableName}
        (
        <trim prefix="" suffixOverrides=",">
        <#list entity.table.cloumns as property>
            <#if property.isKey !="true">
            <if test=" ${property.fieldName} != null">
            ${property.columnName}<#if property_has_next>,</#if>
            </if>
            </#if>
        </#list>
        </trim>
        ) values
        (
        <trim prefix="" suffixOverrides=",">
         <#list entity.table.cloumns as property>
             <#if property.isKey !="true">
             <if test=" ${property.fieldName} != null">
             <#noparse>#{</#noparse>${property.fieldName},jdbcType=${property.jdbcType}<#noparse>}</#noparse><#if property_has_next>,</#if>
             </if>
             </#if>
         </#list>
        </trim>
        )
    </insert>
    <!--删除-->
    <delete id="remove${entity.table.entityName}" parameterType="${entity.packageName}.dto.${entity.table.entityName}">
        delete from ${entity.table.tableName}
        where <#list entity.table.cloumns as property><#if property.isKey ="true"> ${property.fieldName} </#if ></#list>=<#list entity.table.cloumns as property><#if property_index = 0><#noparse>#{</#noparse> ${property.fieldName},jdbcType=${property.jdbcType}<#noparse>}</#noparse></#if ></#list>
    </delete>
    <!--修改-->
    <update id="edit${entity.table.entityName}" parameterType="${entity.packageName}.dto.${entity.table.entityName}">
        update ${entity.table.tableName}
        <trim prefix="set" suffixOverrides=",">
         <#list entity.table.cloumns as property>
             <#if property.isKey !="true">
           <if test=" ${property.fieldName} != null">
               ${property.columnName}= <#noparse>#{</#noparse>${property.fieldName},jdbcType=${property.jdbcType}<#noparse>}</#noparse>
           </if>
             </#if>
         </#list>
        </trim>
        where <#list entity.table.cloumns as property><#if property.isKey ="true"> ${property.fieldName} </#if ></#list>=<#list entity.table.cloumns as property><#if property_index = 0><#noparse>#{</#noparse> ${property.fieldName},jdbcType=${property.jdbcType}<#noparse>}</#noparse></#if ></#list>
    </update>
    <!--查询-->
    <select id="get${entity.table.entityName}" resultMap="BaseResultMap" parameterType="${entity.packageName}.dto.${entity.table.entityName}">
        select
          *
        from ${entity.table.tableName}
        where 1=1 and<#list entity.table.cloumns as property><#if property.isKey ="true"> ${property.fieldName} </#if ></#list>=<#list entity.table.cloumns as property><#if property_index = 0><#noparse>#{</#noparse> ${property.fieldName},jdbcType=${property.jdbcType}<#noparse>}</#noparse></#if ></#list>
    </select>
    <!--查询列表-->
    <select id="get${entity.table.entityName}List" resultMap="BaseResultMap" parameterType="${entity.packageName}.dto.${entity.table.entityName}">
        select
          *
        from ${entity.table.tableName}
        where
        <where>
            <include refid="${entity.table.entityName}_Where"></include>
        </where>
    </select>
    <!--查询总数-->
    <select id="get${entity.table.entityName}Count" resultType="java.lang.Integer" parameterType="${entity.packageName}.dto.${entity.table.entityName}">
        select
        count(1)
        from ${entity.table.tableName}
        <where>
            <include refid="${entity.table.entityName}_Where"></include>
        </where>
    </select>
    <sql id="${entity.table.entityName}_Where">
        1=1
         <#list entity.table.cloumns as property>
           <if test="${property.fieldName} != null">
               and  ${property.columnName} = <#noparse>#{</#noparse>${property.fieldName},jdbcType=${property.jdbcType}<#noparse>}</#noparse>
           </if>
         </#list>
    </sql>
</mapper>