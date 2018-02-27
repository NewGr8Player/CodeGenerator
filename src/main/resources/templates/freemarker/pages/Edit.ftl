<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><!-- page/${moduleName}/${moduleName}Edit.jsp --></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@include file="/common/meta.jsp"%>
		<script src="<%=basePath%>static/libs/js/form/validationRule.js" type="text/javascript"></script>
		<script src="<%=basePath%>static/libs/js/form/validation.js" type="text/javascript"></script>
		<#if (table.hasDateColumn)>
		<script src="<%=basePath%>static/js/form/datePicker/WdatePicker.js" type="text/javascript"></script>
		</#if>
		<script type="text/javascript" src="<%=basePath%>script/${moduleName}.js?<%=date.getTime()%>"></script>
		<script type="text/javascript">
			var fixedObj=0;
			function customHeightSet(contentHeight){
				$("#scrollContent").height(contentHeight-fixedObj);
			}
		</script>
	</head>
	<body>
		<div id="scrollContent">
			<s:form method="post" id="${moduleName}Form">
			<#list table.primaryKeys as key>
				<s:hidden name="${moduleName}.${key.javaProperty}" />
			</#list>
				<table class="tableStyle" formMode="true">
				<#list table.baseColumns as column>
					<tr>
						<td>${column.remarks}</td>
						<td>
							<#if (column.isDate() )>
								<s:textfield name="${moduleName}.${column.javaProperty}" readonly="true" cssClass="date validate[custom[date]]">
									<s:param name="value"><s:date name="${moduleName}.${column.javaProperty}" format="yyyy-MM-dd"/></s:param>
								</s:textfield>
							<#else>
								<s:textfield name="${moduleName}.${column.javaProperty}" <#if (!column.nullable)>cssClass="validate[required]"</#if> />
							</#if>
						</td>
					</tr>
				</#list>
				</table>
			</s:form>
		</div>
	</body>
</html>