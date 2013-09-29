var toSave = false;
 

/**
 * 保存文档
 */
function saveDoc(){
	//前台显示消息
	console.show("正在保存文档....");
	 
	//向服务器发送保存的命令
	var data = $("diaryContent").value;
	var title = $("docTitle").value;
	var id = $("diaryID").value;
	 

   sendReqAsyn(ctxPath + "diary/save.do?id=" + id + "&title=" + encodeURI(title), data, saveCallBack);	
	
	//标记“toSave”为false
	toSave = false;
}
/**
 * 保存文档的的回调函数
 */
function saveCallBack(xhr){   
	var state =  xhr.readyState;
	if(state == '4'){
		if(xhr.status == 200){
			console.show("文档保存成功！");
			
			//更新文档Id
			var returnData = xhr.responseText;
			docID = returnData;
			
			console.hide(3000);
		}else{
			console.show("文档保存失败,服务器返回错误，或无法连接！");
		}		
	}	
}

/**
 * 删除日志
 *
 */
function deleteDiary(){
	window.location.href = ctxPath + "diary/deleteDiary.do?id=" + diaryID;
}

function  setToSave(save){
	toSave = save;
}