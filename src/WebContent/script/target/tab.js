var selectedDate_DAY = ""; //用户日目标选择的日期
var selectWeek =""; //用户选择星期号
/**
	选择一个标签页
	@param n 	 标签页的序号，从1开始
	@param count 标签页的总数 
*/
function showtab(n,count,selectDate){
	selectedDate_DAY = selectDate;
	selectWeek = n;
	
	for(var i=1;i<=count;i++){
		var targetElement = document.getElementById('tab'+i);
		var tabHeader = document.getElementById('tabH'+i);
        if (i==n)
		{                        
			targetElement.style.display='block';
			tabHeader.style.backgroundColor="#f1f1f1";			
        }else{
            targetElement.style.display='none';
			tabHeader.style.backgroundColor="white";
        }
    } 		
}