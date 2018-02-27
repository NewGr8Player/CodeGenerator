<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><!-- page/${moduleName}/${moduleName}View.jsp --></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@include file="/common/meta.jsp"%>
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
				<s:hidden id="id" name="${moduleName}.id" />
				<table class="tableStyle" formMode="view">
				<#list table.baseColumns as column>
					<tr>
						<td>${column.remarks}</td>
						<td><s:property value="${moduleName}.${column.javaProperty}" /></td>
					</tr>
				</#list>
				</table>
			</s:form>
		</div>
	</body>
</html>