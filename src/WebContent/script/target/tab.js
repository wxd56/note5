var selectedDate_DAY = ""; //�û���Ŀ��ѡ�������
var selectWeek =""; //�û�ѡ�����ں�
/**
	ѡ��һ����ǩҳ
	@param n 	 ��ǩҳ����ţ���1��ʼ
	@param count ��ǩҳ������ 
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