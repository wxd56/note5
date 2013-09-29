//显示ToolBox菜单的Id
var showedToolBoxId = ""; 

function mouseOverItem(type, id) {
	var obj = null;
	if (type == 'day') {
		obj = document.getElementById("boxTool" + id);
	}
	if (type == 'week') {
		obj = document.getElementById("boxToolWeek" + id);
	}

	if (type == 'month') {
		obj = document.getElementById("boxToolMonth" + id);
	}

	if (obj != null) {
		obj.style.visibility = "visible";
	}
}

function mouseOutItem(type, id) {
	var obj = null;
	if (type == 'day') {
		obj = document.getElementById("boxTool" + id);
	}
	if (type == 'week') {
		obj = document.getElementById("boxToolWeek" + id);
	}
	if (type == 'month') {
		obj = document.getElementById("boxToolMonth" + id);
	}

	if (obj != null) {
		obj.style.visibility = "hidden";
	}
}

//显示对条目进行操作的菜单
function showToolBoxMenu(type, id, e) {
	//关闭已经打开的
	hideToolBoxMenu();
	
	if (type == 'day') {
		document.getElementById("toolBoxMenu" + id).style.display = "block";
	} else if (type == 'week') {
		document.getElementById("toolBoxMenuWeek" + id).style.display = "block";
	} else if (type == 'month') {
		document.getElementById("toolBoxMenuMonth" + id).style.display = "block";
	}

	showedToolBoxId = id;
	//组织事件的传递
	if (e && e.stopPropagation) {
		e.stopPropagation();
	} else {
		//否则，我们需要使用IE的方式来取消事件冒泡
		window.event.cancelBubble = true;
		returnfalse;
	}
}

//关闭已经打开的ToolBoxMenu
function hideToolBoxMenu() {
	if (showedToolBoxId != "") {
		var obj = document.getElementById("toolBoxMenu" + showedToolBoxId);
		var objWeek = document.getElementById("toolBoxMenuWeek"
				+ showedToolBoxId);
		var objMonth = document.getElementById("toolBoxMenuMonth"
				+ showedToolBoxId);
		if (obj != null && obj.style != null) {
			obj.style.display = "none";
			showedToolBoxId = "";
		}
		if (objWeek != null) {
			objWeek.style.display = "none";
			showedToolBoxId = "";
		}
		if (objMonth != null) {
			objMonth.style.display = "none";
			showedToolBoxId = "";
		}
	}
}

/**
	删除一个目标
	@param type 	 目标种类‘day’,'week','month'
	@param id    		 目标Id,用表示目标所在的行
	@param tableId  要被删除的行所在的Table的Id
 */
function deleteItem(type, id,tableId) {
	var confirmResult = confirm("确定要删除吗？");
	if (confirmResult) {
		var table = null;
		var tr = null;

		//删除日目标
		if (type == 'day') {
			table = document.getElementById(tableId);
			tr = document.getElementById("dayArrangeTR" + id);
		}

		if (type == 'week') {
			table = document.getElementById("weekArrangeTable");
			tr = document.getElementById("weekArrangeTR" + id);
		}

		if (type == 'month') {
			table = document.getElementById("monthArrangeTable");
			tr = document.getElementById("monthArrangeTR" + id);
		}
		//发送删除消息到服务器
		var url = ctxPath + "/target/updateState.do?id=" + id + "&newState=deleted";	
		var result = sendReqSync(url,null);
		if(result != null){
			alert(result);
			return;
		}

		//开始删除
		table.deleteRow(tr.rowIndex);
		
		//更新Table Row 的序号
		updateTableOrder( tableId);	 
		
	}
}

/**
	标记目标完成
	@param type 目标类型
	@param id	目标Id
 */
function finishItem(type, id) {	
	//发送完成消息到服务器
	var url = ctxPath + "/target/updateState.do?id=" + id + "&newState=finished";	
	var result = sendReqSync(url,null);
	if(result != null){
		alert(result);
		return;
	}
	
	//日目标
	if (type == 'day') {
		var tr = document.getElementById("dayArrangeTR" + id);
		var tdRight = document.getElementById("dayArrangeItemTDRight" + id);
		tr.className += "  finishItem";
		tdRight.innerHTML = "";		
	}

	if (type == 'week') {
		var tr = document.getElementById("weekArrangeTR" + id);
		var tdRight = document.getElementById("weekArrangeItemTDRight" + id);
		tr.className += "  finishItem";
		tdRight.innerHTML = "";		
	}

	if (type == 'month') {
		var tr = document.getElementById("monthArrangeTR" + id);
		var tdRight = document.getElementById("monthArrangeItemTDRight" + id);
		tr.className += "  finishItem";
		tdRight.innerHTML = "";	
	} 
	
	//更新数据同步按钮的样式
	changeSynchronizeImg('1');
}

/**
 * 添加一个目标
 * @param type
 * @param content
 */
function addTarget(type,content) {	
	
	if(content == undefined){		
		if (type == 'day') {
			content = document.getElementById("addDayTargetTA").value;
		} else if (type == 'week') {
			content = document.getElementById("addWeekTargetTA").value;
		} else if (type == 'month') {
			content = document.getElementById("addMonthTargetTA").value;
		}
	}

	//判断是否内容为空
	if (content == "")
		return;

	//得到目标开始日期
	
	//发送消息给服务器,返回Id
	var url = ctxPath + "/target/addTarget.do?type=" + type + "&beginDate="+ selectedDate_DAY;
	var data = content;
	var id = sendReqSync(url,data);	
	var tableId = null;
	
	//页面添加目标
	if (type == 'day') {
		tableId = "dayArrangeTable" + selectWeek;
		var table = document.getElementById(tableId);
		var row = table.insertRow(table.rows.length);
		row.innerHTML = "<td></td><td class='itemContent1' id='dayArrangeItemTD"
				+ id
				+ "'>"
				+ content
				+ "</td>"
				+ "<td class='itemContent2' id='dayArrangeItemTDRight"
				+ id
				+ "'>"
				+ "<img src='"
				+ ctxPath
				+ "images/v3/select.jpg' id='boxTool"
				+ id
				+ "' style='visibility:hidden' onclick='showToolBoxMenu(\"day\",'"
				+ id
				+ "',event)'></img>"
				+ "<ul class='toolBoxMenu' id='toolBoxMenu"
				+ id
				+ "'>"
				+ "<li><a href='javascript:void(0)' onclick=\"deleteItem('day','"+ id	+ "')\">删除</a></li>"
				+ "<li><a href='javascript:void(0)' onclick=\"finishItem('day','" + id + "')\">标记完成</a></li>"				
				+ "</ul></td>";

		row.setAttribute("id", "dayArrangeTR" + id);
		row.setAttribute("onmouseout", "mouseOutItem('day'," + id + ")");
		row.setAttribute("onmouseover", "mouseOverItem('day'," + id + ")");
		row.setAttribute("class", "itemTR");

		document.getElementById("addDayTargetTA").value = "";
	}

	if (type == 'week') {
		tableId = "weekArrangeTable";
		var table = document.getElementById(tableId);
		var row = table.insertRow(table.rows.length);
		row.innerHTML = "<td></td><td class='itemContent1' id='weekArrangeItemTD"	+ id	+ "'>"
				+ content
				+ "</td>"
				+ "<td class='itemContent2' id='weekArrangeItemTDRight" 	+ id 	+ "'>"
				+ "<img src='" 
				+ ctxPath
				+ "images/v3/select.jpg' id='boxToolWeek"
				+ id
				+ "' style='visibility:hidden' onclick='showToolBoxMenu(\"week\",'"
				+ id
				+ "',event)'></img>"
				+ "<ul class='toolBoxMenu' id='toolBoxMenuWeek"
				+ id
				+ "'>"
				+ "<li><a href='javascript:void(0)' onclick=\"deleteItem('week','" + id	+ "')\">删除</a></li>"
				+ "<li><a href='javascript:void(0)' onclick=\"finishItem('week','" 	+ id + "')\">标记完成</a></li>"				
				+ "</ul></td>";

		row.setAttribute("id", "weekArrangeTR" + id);
		row.setAttribute("onmouseout", "mouseOutItem('week'," + id + ")");
		row.setAttribute("onmouseover", "mouseOverItem('week'," + id + ")");
		row.setAttribute("class", "itemTR");

		document.getElementById("addWeekTargetTA").value = "";
	}

	if (type == 'month') {
		tableId = "monthArrangeTable";
		var table = document.getElementById(tableId);
		var row = table.insertRow(table.rows.length);
		row.innerHTML = "<td></td><td class='itemContent1' id='monthArrangeItemTD"
				+ id
				+ "'>"
				+ content
				+ "</td>"
				+ "<td class='itemContent2' id='monthArrangeItemTDRight"
				+ id
				+ "'>"
				+ "<img src='"
				+ ctxPath
				+ "images/v3/select.jpg' id='boxToolMonth"
				+ id
				+ "' style='visibility:hidden' onclick='showToolBoxMenu(\"month\",'"
				+ id
				+ "',event)'></img>"
				+ "<ul class='toolBoxMenu' id='toolBoxMenuMonth"
				+ id
				+ "'>"
				+ "<li><a href='javascript:void(0)' onclick=\"deleteItem('month','" 		+ id 		+ "')\">删除</a></li>"
				+ "<li><a href='javascript:void(0)' onclick=\"finishItem('month','" 		+ id + "')\">标记完成</a></li>"				
				+ "</ul></td>";

		row.setAttribute("id", "monthArrangeTR" + id);
		row.setAttribute("onmouseout", "mouseOutItem('month'," + id + ")");
		row.setAttribute("onmouseover", "mouseOverItem('month'," + id + ")");
		row.setAttribute("class", "itemTR");

		document.getElementById("addMonthTargetTA").value = "";
	}
	
	updateTableOrder(tableId);

	//提示结果
	if(id != null){
		console.show("添加成功！");
		console.hide(4000);
		
	}else{
		alert("添加失败！");		
	} 
}

//页面加载完毕
function bodyLoad(currDate,currWeek){
	//初始化当前用户选择日期
	selectedDate_DAY = currDate; 
	
	selectWeek = currWeek; 
	
	//根据cookie显示隐藏层
	checkHideStateInCookie();
	
	//同步结果清空
	sync = "";
}

/**
 * 查看其它日期所在周和月的目标
 * @param type 为‘now'代表查看当前日期的目标
 */
function changeSelectedDate(type){
	var date = null;
	if(type == 'now'){
		date = "";
	}else{
		date = document.getElementById("inputSelectedDate").value;
	}

	window.location.href=ctxPath + "target/show.do?selectedDate=" + date;
}

/**
 * 更新Table序号
 */
function updateTableOrder(tableId){
	var tableObj = document.getElementById(tableId);
	for(var i = 0; i< tableObj.rows.length; i++){
		var tableRow = tableObj.rows[i];
		var cell0 = tableRow.cells[0];
		cell0.innerHTML = (i+1+".");
	}
}

