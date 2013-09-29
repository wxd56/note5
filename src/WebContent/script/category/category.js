var tree;
function pageLoad(){
	tree= new dhtmlXTreeObject("treeBox", "100%", "100%",	0);
	tree.attachEvent("onClick", getDateFromServer);
	tree.setImagePath(ctxPath + "images/tree/");
	tree.enableDragAndDrop(true);
	tree.setDragHandler(tondrag);
	
	//初始化根节点
	tree.insertNewChild(0, "null", "文档分类", 0, 0, 0, 0,	"TOP,CHILD,CHECKED");	
}

/**
 * 处理拖动事件
 * @param id  	将要被移动的节点
 * @param id2   move To
 * @returns
 */
function tondrag(id, id2) {    
	var url = ctxPath + "category/move.do?"  + "target=" + id + "&destination=" + id2;	
	sendReqAsyn(url, null, dragCallBack);	
	return true;
};

function dragCallBack(xml){
	//alert(xml.responseText);
}

/**
 * 添加分类
 */
function addCategory(){
	
	//获得父分类的ID
	var id = tree.getSelectedItemId();
	var name = $("add_cate_name").value;
	var url = ctxPath + "category/addCateogry.do?"  + "parent=" + id ;
	
	//向服务器发送请求，返回添加的类别的Id
	var addedID = sendReqSync(url, name);
	
	//清空输入框
	$("add_cate_name").value = "";
	
	//添加到树中
	insertNode(id,name,addedID);
	
}

function insertNode(parentID,text,data){
	tree.insertNewChild(parentID,data,text,0,0,0,0,"CHILD,CHECKED");
}

/**
 * 列出所有的分类
 * @param parent	父分类的ID
 */
function listCategores(nodeID){	
	//如果已经打开，则直接返回
	 var state = tree.getOpenState(nodeID);
	 if(state == 1) return;
	
	 var categoryId = nodeID;	 
	var url = ctxPath + "category/list.do?parent=" + categoryId ;
	var msg = sendReqSync(url, null);
	
	//解析Json字符串
	var list = JSON.parse(msg).data;
	
	//如何没有打开清空旧的子节点
	tree.deleteChildItems(nodeID);
	
	//插入新节点
	for(var i =0; i< list.length; i++){
		var obj = list[i];
		insertNode(nodeID,obj.name,obj.id);		
	}
	
	//打开节点
	tree.openItem(nodeID);
}

/**
 * 重命名
 */
function renameCategory(){
	//获得分类的ID
	var id = tree.getSelectedItemId();
	var newName = $('selectedCategory').value;
	if(newName == ""){
		alert("名称不能为空");
		return;
	}
	
	//发送请求
	var url = ctxPath + "category/rename.do?id=" + id ;
	sendReqSync(url, newName);
	
	//更新树种节点的名称
	tree.setItemText(id,newName,newName);
	$('selectedCategory').value = "";
}

/**
 * 某个节点点击时的处理函数
 */
function getDateFromServer(){
	//获得父分类的ID
	var id = tree.getSelectedItemId();
	
	//设置重命名的输入框中的名称
	$('selectedCategory').value = tree.getSelectedItemText(id);
	$('selectedCategorySpan').innerHTML = tree.getSelectedItemText(id);
	
	//发送消息
	listCategores(id);	
}