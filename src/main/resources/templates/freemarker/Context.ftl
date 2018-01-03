<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
		xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
	<!-- ${table.className}_START -->
	<bean id="${moduleName}Action" class="${basePackage}.${moduleName}.action.${table.className}Action" scope="session">
		<property name="${moduleName}Service" ref="${moduleName}Service" />
	</bean>
	<bean id="${moduleName}Service" class="${basePackage}.${moduleName}.service.${table.className}ServiceImpl">
		<property name="${moduleName}Dao" ref="${moduleName}Dao" />
	</bean>
	<bean id="${moduleName}Dao" class="${basePackage}.${moduleName}.dao.${table.className}DaoImpl" parent="hBaseDao">
	</bean>
	<bean id="ajax${table.className}Action" class="${basePackage}.ajax.action.Ajax${table.className}Action" scope="prototype">
		<property name="${moduleName}Service" ref="${moduleName}Service" />
	</bean>
	<!-- ${table.className}_End -->
</beans>