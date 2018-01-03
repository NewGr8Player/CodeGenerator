package ${basePackage}.${moduleName}.bean;

<#if (table.hasDateColumn)>
import java.util.Date;
</#if>
<#if (table.hasBigDecimalColumn)>
import java.math.BigDecimal;
</#if>

import javax.persistence.Column;
import javax.persistence.Table;

import com.sxkj.frame.utils.BeanSupport;

/**
 * @author:${author}
 * @data: ${.now?date}
 * @comment: ${table.remarks}
 */
@SuppressWarnings("serial")
@Table(name = "${table.tableName}")
public class ${table.className} extends BeanSupport {
	<#list table.primaryKeys as key>
	@Column(name = "${key.remarks}")
	private ${key.javaType} ${key.javaProperty};
	</#list>
	<#list table.baseColumns as column>
	@Column(name = "${column.remarks}")
	private ${column.javaType} ${column.javaProperty};
	</#list>

	<#list table.primaryKeys as key>
	public ${key.javaType} ${key.getterMethodName}(){
		return this.${key.javaProperty};
	}
	public void ${key.setterMethodName}(${key.javaType} ${key.javaProperty}){
		this.${key.javaProperty} = ${key.javaProperty};
	}
	</#list>
	<#list table.baseColumns as column>
	public ${column.javaType} ${column.getterMethodName}(){
		return this.${column.javaProperty};
	}
	public void ${column.setterMethodName}(${column.javaType} ${column.javaProperty}){
		this.${column.javaProperty} = ${column.javaProperty};
	}
	</#list>
}