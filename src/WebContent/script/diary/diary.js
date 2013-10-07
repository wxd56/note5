var toSave = false;
 

/**
 * 保存文档
 */
function saveDoc(){
	//前台显示消息
	console.show("正在保存文档....");
	 
	//向服务器发送保存的命令
	var data = $("#diaryContent").val();
	var title = $("#docTitle").val();
	var id = $("#diaryID").val();
	 

   $.post(
		   ctxPath + "diary/save.do",
		   {
			   'id':id,
			   'title':title,
			   'content':data
		   },
		   function(msg,status){
			   if(status == 'success'){
					console.show("文档保存成功！");
					
					//更新文档Id
					var returnData = msg;
					docID = returnData;					
					console.hide(3000);
			   }else{
				   console.show("文档保存失败,服务器返回错误，或无法连接！");
			   }
		   }
   );
	
	//标记“toSave”为false
	toSave = false;
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