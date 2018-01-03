<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
		"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
	<class name="${basePackage}.${moduleName}.bean.${table.className}" table="${table.tableName}" lazy="true">
		<id name="id" type="java.lang.String">
			<column name="id" length="50" />
			<generator class="assigned" />
		</id>
		<#list table.baseColumns as column>
		<property name="${column.javaProperty}" type="${column.fullJavaType}">
			<column name="${column.columnName}">
				<comment>${column.remarks}</comment>
			</column>
		</property>
		</#list>
	</class>
</hibernate-mapping>