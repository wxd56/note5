var toSave = false;
/**
 * 新建一个文档
 */
function newDoc(category){
	//查看是否需要保存
	if(toSave){
		alert("请先保存文档！");
		return;
	}
	
	window.location.href = ctxPath + "doc/newDoc.do?categoryId=" + category;
}

/**
 * 保存文档
 */
function saveDoc(category,docId){
	//前台显示消息
	console.show("正在保存文档....");
	 
	//向服务器发送保存的命令
	var data = editor_a1.getContent();
	var title = $("docTitle").value;
   sendReqAsyn(ctxPath + "doc/save.do?id=" + docId +"&category="+category+"&type=OTHER"+ "&title=" + encodeURI(title), data, saveCallBack);	
	
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
			//console.hide(10000);
		}		
	}	
	//public.js中，更新同步图片
	initSynchronizeImg();
}

 
/**
 * 设置加密标志
 */
function showEncryptFlag(flag){
	if(flag == '0'){
		//未加密
		$("decryptFlag").style.display="block";
		$("encryptFlag").style.display="none";
	}else{
		//加密
		$("decryptFlag").style.display="none";
		$("encryptFlag").style.display="inline";
	}
}

function deleteDoc(category,docId){
	window.location.href = ctxPath + "doc/deleteDoc.do?id=" + docId + "&categoryId=" + category;
}

 
function  setToSave(save){
	toSave = save;
}
/**
 * 打开分类的文档列表
 * @param obj			select 对象
 */
function listDocByCategories(pageNo,pageSize,obj){
	var index = obj.selectedIndex;
	var item = obj.options[index];
	
	var categoryID = item.value;
	
	window.location.href = ctxPath + "doc/other/list.do?pageNo=" + pageNo + "&pageSize=" + pageSize + "&category=" + categoryID;
}

 
 