<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><!-- page/${moduleName}/${moduleName}List.jsp --></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@include file="/common/meta.jsp"%>
		<script type="text/javascript" src="<%=basePath%>script/${moduleName}.js?<%=date.getTime()%>"></script>
	</head>
	<body>
		<div class="box2" panelTitle="查询条件" roller="false">
			<form action="${moduleName}!list.action?param=all" id="sForm" method="post">
				<table class="tableStyle" id="leaderTable" sortMode="true">
					<tr>
						<td width="5%" align="center">序号</td>
						<#list table.baseColumns as column>
						<td>
						${column.remarks}
						</td>
						</#list>
						<td width="20%" align="center">操作</td>
					</tr>
					<tr>
						<td>
						<#list table.baseColumns as column>
						<#if (column.isDate() )>
							<s:date name="${column.javaProperty}" <%--format="yyyy-MM-dd"--%> />
						<#else>
							<s:property value="${column.javaProperty}" />
						</#if>
						</td>
						</#list>
						<td rowspan="<s:property value='#size' />">
							<span class="img_view hand" title="查看" onclick="${moduleName}View('<s:property value="id"/>');"></span>
							<span class="img_edit hand" title="修改" onclick="${moduleName}Edit1('<s:property value="id"/>');"></span>
							<span class="img_delete hand" title="删除" onclick="${moduleName}Delete('<s:property value="id"/>');"></span>
						</td>
					</tr>
					<s:if test="leaderList.size==0">
					<tr>
						<td colspan="11" align="center">
							无数据显示...
						</td>
					</tr>
					</s:if>
				</table>
			</form>
		</div>
		<div id="scrollContent">
			<table class="tableStyle" id="${moduleName}Table" sortMode="true">
				<td rowspan="<s:property value='#size' />">
					<span class="img_view hand" title="查看" onclick="${moduleName}View('<s:property value="id"/>');"></span>
					<span class="img_edit hand" title="修改" onclick="${moduleName}Edit('<s:property value="id"/>');"></span>
					<span class="img_delete hand" title="删除" onclick="${moduleName}Delete('<s:property value="id"/>');"></span>
				</td>
			</table>
		</div>
		<div style="height: 35px; bottom: 0; right: 0; position: absolute; width: 100%;">
			<div class="float_left padding5">&nbsp;&nbsp;共有<s:property value="pageModel.pageNum" />条记录</div>
			<div class="float_right padding5"><s:property value="pageModel.pageShow" escape="false" /></div>
			<div class="clear"></div>
		</div>
	</body>
</html>