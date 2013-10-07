/**
 * 此文件用于监听页面上的键盘消息，用于进行通用的处理，某些页面也可以通过注册自己的键盘事件的监听者来进行覆盖
 */
window.onkeydown = keyEventHandler;
function keyEventHandler() {
	if (event.ctrlKey && event.keyCode != 17) {	
		
		if(event.keyCode == 83){
			//ctrl + s
			event.preventDefault();
			if(handler_ctrl_s !== null){
				handler_ctrl_s();
			}
			
		}
		
		if(event.keyCode == 72){
			//处理ctrl + h			
			showHideDiv();
			event.preventDefault();
		}
	}
} 

/**
 * 显示遮盖层
 */
function showHideDiv(){
	if(!isCreated){
		createHideDiv();
		isCreated = true;
		isHided = true;
	}else{
		if(isHided){
			show();
			isHided = false;
		}else{
			hide();
			isHided = true;
		}
	}	
	
	//将遮盖的状态写入到cookie中去
	document.cookie = "isHided=" + isHided;
}
var isHided = false;
var isCreated = false;
var myDiv;

function createHideDiv(){
	 myDiv = document.createElement("div");
	 myDiv.innerHTML = "<h1> </h1>";
	 myDiv.style.position = "absolute";
	 myDiv.style.top = "0";
	 myDiv.style.left = "0";
	 myDiv.style.width = "100%";
	 myDiv.style.height = "100%";
	 myDiv.style.zIndex = "100000";
	 myDiv.style.backgroundColor = "gray";
	 myDiv.setAttribute("id","myHidenDiv");
	  document.body.appendChild(myDiv);
}

function  show(){
	myDiv.style.display = "none"; 
}

function hide(){
	myDiv.style.display = "block";
}
 

function checkHideStateInCookie(){	
	//得到cookie的值
	var index = document.cookie.indexOf("isHided=");
	if(index != -1){
		end=document.cookie.indexOf(";",index);
		if (end==-1) end=document.cookie.length;
		var value = document.cookie.substring(index + 8,end);
		if(value == 'true'){
			showHideDiv();
		}		
	}
}