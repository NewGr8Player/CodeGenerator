<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE struts PUBLIC "-//Apache Software Foundation//DTD Struts Configuration 2.1//EN" "http://struts.apache.org/dtds/struts-2.1.dtd">
<struts>
	<package name="${moduleName}" extends="default">
		<action name="${moduleName}" class="${moduleName}Action">
			<result name="${moduleName}Add">/WEB-INF/page/${moduleName}/${moduleName}Add.jsp</result>
			<result name="${moduleName}Edit">/WEB-INF/page/${moduleName}/${moduleName}Edit.jsp</result>
			<result name="${moduleName}List">/WEB-INF/page/${moduleName}/${moduleName}List.jsp</result>
			<result name="${moduleName}View">/WEB-INF/page/${moduleName}/${moduleName}View.jsp</result>
			<result name="${moduleName}Grid">/WEB-INF/page/${moduleName}/${moduleName}Grid.jsp</result>
			<!-- 导出Excle表格文件 -->
			<result name="exportExcel" type="stream">
				<param name="contentType">application/vnd.ms-excel</param>
				<param name="contentDisposition">attachment;filename="${r'${downloadFileName}'}"</param>
				<param name="bufferSize">1024</param>
				<param name="inputName">excelFile</param>
			</result>
		</action>
	</package>
	<package name="ajax${table.className}" extends="ajax">
		<action name="ajax${table.className}" class="ajax${table.className}Action">
			<result type="json">
				<!-- 序列化所有属性 必须有 -->
				<param name="root">action</param>
				<!-- 序列化action继承父类的属性,当action没有父类的时候,可以不适用此单数 -->
				<param name="ignoreHierarchy">false</param>
			</result>
		</action>
	</package>
</struts>