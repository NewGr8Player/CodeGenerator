'use strict'

/**
 *
 * @Title: search
 * @author:${author}
 * @date: ${.now}
 * @Description: 打开新增页面
 */
function ${moduleName}Add(){
	var diag = new top.Dialog();
	diag.ID = "${moduleName}Form";
	diag.URL = "${moduleName}!add.action?isPop=true";
	diag.MessageTitle="信息添加";
	diag.Width= 500;
	diag.OKEvent = function(){
		if(diag.innerFrame.contentWindow.${moduleName}Save()){
			top.Dialog.alert("数据保存成功！",function(){onSelect();})
			diag.close();
		}
	}
	diag.show();
}

/**
 *
 * @Title: ${moduleName}Save
 * @author:${author}
 * @date: ${.now}
 * @Description: 保存数据
 */
function ${moduleName}Save(){
	var access=true;
	access = $('#${moduleName}Form').validationEngine({returnIsValid:true})
	if(access==false){
		top.Dialog.alert("数据输入有误,请检查数据!")
		return;
	}
	var msg;
	$.ajax( {
		url : "ajax${table.className}!save.action",
		type:"post",
		data : $("#${moduleName}Form").serializeArray(),
		async:false,
		success : function(data) {
			var status = data.status;
			msg = status;
			if ("" != status) {
				top.Dialog.alert(status);
			}
		},
		error : function(req) {
			top.Dialog.alert(req.responseText);
		}
	});
	top.document.getElementById("_DialogFrame_${moduleName}Form").src="${moduleName}!add.action?isPop=true";
	return (msg == '');
}

/**
 *
 * @Title: ${moduleName}Edit
 * @author:${author}
 * @date: ${.now}
 * @Description: 打开修改页面
 * @param: id
 */
function ${moduleName}Edit(id){
	var diag = new top.Dialog();
	diag.ID = "${moduleName}Form";
	diag.URL = "${moduleName}!edit.action?isPop=true&id=" + id;
	diag.OkButtonText=" 保 存 ";
	diag.MessageTitle=" 信息修改 ";
	diag.Message=" 信息修改 ";
	diag.Width= 500;
	diag.OKEvent = function(){
		if(diag.innerFrame.contentWindow.${moduleName}Update()){
			top.Dialog.alert(" 数据保存成功 ",function(){onSelect()})
			diag.close();
		}
	}
	diag.show();
}

/**
 *
 * @Title: ${moduleName}Update()
 * @author:${author}
 * @date: ${.now}
 * @Description: 更新数据
 */
function ${moduleName}Update(){
	var access=true;
	access = $('#${moduleName}Form').validationEngine({returnIsValid:true})
	if(access==false){
	top.Dialog.alert(" 数据输入有误,请检查数据! ")
		return;
	}
	var msg;
	$.ajax( {
		url : "ajax${table.className}!update.action",
		type:"post",
		data : $("#${moduleName}Form").serializeArray(),
		async:false,
		success : function(data) {
			var status = data.status;
			msg = status;
			if ("" != status) {
				top.Dialog.alert(status);
			}
		},
		error : function(req) {
			top.Dialog.alert(req.responseText)
		}
	});
	return (msg == '');
}

/**
 *
 * @Title: ${moduleName}View
 * @author:${author}
 * @date: ${.now}
 * @param: id
 * @Description: 打开查看页面
 */
function ${moduleName}View(id) {
	var diag = new top.Dialog();
	diag.ID = "${moduleName}Form";
	diag.URL = "${moduleName}!view.action?isPop=true&${moduleName}Query.id=" + id;
	diag.ShowOkButton=false;
	diag.CancelButtonText = " 关 闭 ";
	diag.MessageTitle = " 信息查看 ";
	diag.Message = " 信息查看 ";
	diag.Width=500 ;
	diag.show();
}


/**
 *
 * @Title: ${moduleName}Delete
 * @author:${author}
 * @date: ${.now}
 * @param: id
 * @Description: 删除信息
 */
function ${moduleName}Delete(id){
	top.Dialog.confirm(" 是否要删除所选信息 ",function(){
		$.ajax({
			url : "ajax${table.className}!delete.action",
			type:"post",
			data : "id="+id,
			async:false,
			success : function(data) {
				var status = data.status;
				if ("" == status) {
					top.Dialog.alert("数据删除成功！",function(){onSelect();})
				}else{
					top.Dialog.alert(status);
				}
			},
			error : function(req) {
				top.Dialog.alert(req.responseText);
			}
		});
	});
}

/**
 *
 * @Title: ${moduleName}ResetForm
 * @author:${author}
 * @date: ${.now}
 * @param: formID
 * @Description: 重置表格
 */
function ${moduleName}ResetForm(formID){
	var myForm = "#" + formID;
	var text = $(myForm + " input[type='text']");
	$.each(text, function(i, n) {
		n.value = "";
	});
	var select = $(myForm + " select");
	$.each(select, function(i, n) {
		if(n.options.length >0){
			$(n).val(n.options[0].value);
			$(n).render();
		}
	});
	onSelect();
}