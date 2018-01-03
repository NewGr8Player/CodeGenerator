<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><!-- page/${moduleName}/${moduleName}Grid.jsp --></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@include file="/common/meta.jsp"%>
		<script type="text/javascript" src="<%=basePath%>static/libs/js/table/quiGrid.js"></script>
		<script type="text/javascript" src="<%=basePath%>static/js/mask.js"></script>
		<script type="text/javascript" src="<%=basePath%>script/${moduleName}.js?<%=date.getTime()%>"></script>
		<script type="text/javascript">
			var fixedObj=0;
			function customHeightSet(contentHeight){
				$("#scrollContent").height(contentHeight-fixedObj);
			}
		</script>
	</head>
	<body>
	<div class="box2" panelTitle="${table.remarks}管理" roller="false" showStatus="false">
		<form action="${moduleName}!grid.action?param=all" id="sForm" method="post">
			<s:hidden id="pageSize" name="pageSize" value="10"/>
			<s:hidden id="currPage" name="currPage" value="1"/>
			<table id="hideTable">
				<tr>
					<td>id：</td>
					<td><s:textfield name="id" /></td>
					<td>
						<button onclick="onSelect();" type="button">
							<span class="icon_find">查&nbsp;&nbsp;询</span>
						</button>
						<button onclick="${moduleName}ResetForm('sForm');" type="button">
							<span class="icon_reload">重&nbsp;&nbsp;置</span>
						</button>
						<button type="button" onclick="${moduleName}Add();">
							<span class="icon_add">新&nbsp;&nbsp;增</span>
						</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="scrollContent">
		<div id="mainGrid"></div>
		<div id="pageContent" style="height:35px;"></div>
	</div>
	<script type="text/javascript">
		var g_pageNo = 1;
		var g_pageSize = 10;
		//数据表格使用
		var g;
		function initComplete(){
			g = $("#mainGrid").quiGrid({
				columns: [
				<#list table.baseColumns as column>
					{display:"${column.remarks}",name:"${column.javaProperty}",align:"center",width:"${80 / table.getBaseColumnsCount() }%"},
				</#list>
					{display:"操作",isAllowHide:false,align:"center",width:"20%",
						render: function (rowdata, rowindex, value, column){
							var back = "<div class='padding_left5'>"
							back += "<span class='blue underLine hand' onclick='${moduleName}Edit(\""+rowdata.id +"\");'>编辑</span>&nbsp;&nbsp;";
							back += "<span class='blue underLine hand' onclick='${moduleName}View(\""+rowdata.id +"\");'>查看</span>&nbsp;&nbsp;";
							back += "<span class='blue underLine hand' onclick='${moduleName}Delete(\""+rowdata.id +"\");'>删除</span>";
							back += "</div>";
							return back;
						}
					}
				],
				data:[],
				sortName: 'id',
				rownumbers:true,
				dataAction:'local',
				checkbox:false,
				usePager: false,
				height: '100%',
				width:"99%",
				heightDiff:-40,
				percentWidthMode:true
			});
		}

		$(function(){
			onSelect();
		});
		function onSelect(){
			$("#currPage").val(1);
			var url="ajax${table.className}!grid.action";
			var frmData = $("#sForm").serializeArray();
			$.post(url,frmData,function(result){
				gridData = eval('('+result.gridJson+')');
				//刷新表格
				g.loadData(gridData);
				drawPage(url,gridData,frmData);
			},"json");
		}
	</script>
	</body>
</html>