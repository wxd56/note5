 
/**
 * 向服务器发送请求，同步请求，直到服务器返回数据后才会结束
 * @param url	服务器的地址
 * @param data	通过post发送的数据，如果为空，则通过get发送
 */
function sendReqSync(url,data){
	var xhr = new XMLHttpRequest();
	var returnDate =null;
	
	//定义回调函数
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				returnDate = xhr.responseText;
			}
		}		
	};
	
	if(data == null || data == undefined){
		xhr.open("GET",url,false);
		xhr.send();
	}else{
		xhr.open("POST",url,false);
		xhr.send(data);
	}
	
	return returnDate;
}

/**
 * 向服务器异步发送请求，不会有任何返回数据
 * @param url	服务器的地址
 * @param data	通过post发送的数据，如果为空，则通过get发送
 * @param callBack 服务器返回数据后的回调函数
 */
function sendReqAsyn(url,data,callBack){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		callBack(xhr);
	};
	if(data == null || data == undefined){
		xhr.open("GET",url,true);
		xhr.send();
	}else{
		xhr.open("POST",url,true);
		xhr.send(data);
	}
}

function submit(objID){
	$(objID).submit();
}
/**
 * 代表控制台的对象
 */
$(
		function (){
			$("<div/>").attr("id","console").appendTo($('body')[0]);
		}
);
var console = {
		width:300,
		content:'',
		show:function(param){
			$("#console").css("display","block");
			if(param == null){
				$("#console").html( this.content);
			}else{
				$('#console').html(param);
			}
		},
		hide:function(delay){
			if(delay == null){
				$('#console').css('display',"none");
			}else{
				//延时隐藏
				setTimeout(function(){
					$('#console').css('display',"none");
				}, delay);
			}		
		}
};

var isChanaged = true;