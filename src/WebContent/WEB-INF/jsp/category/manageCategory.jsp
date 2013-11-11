<%@ page language="java" contentType="text/html; charset=UTF-8"  	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"  src="${baseURL }script/jquery-2.0.3.js"></script>
<script type="text/javascript">
	var ctxPath = "${baseURL}";
</script>

<script type="text/javascript" src="${baseURL }script/public.js"></script>

<script type="text/javascript" src="${baseURL }/script/category/category.js"></script>
<script src="${baseURL }script/tree/dhtmlxcommon.js"></script>
<script src="${baseURL }script/tree/dhtmlxtree.js"></script>

<link rel="stylesheet" type="text/css" 	href="${baseURL }style/footer.css"></link>
<link rel="stylesheet" type="text/css" 	href="${baseURL }style/header.css"></link>
<link rel="stylesheet" type="text/css"  	href="${baseURL }/style/tree/dhtmlxtree.css">
  
<title>分类管理</title>
</head>
<body onload="pageLoad()">
   	<table id="headerBar" >
		<tr>
			<td style="padding-left: 12px; padding-top: 6px;width: 500px;"> 				
				在当前分类<span id="selectedCategorySpan"  style="font-weight: bold;" >文档分类</span>下
				<input type="button" value="添加子类"  onclick="addCategory()" class="button"/>
				<input name="categoryName" id="add_cate_name"  	style="width: 200px;"  />
			</td>			
			<td style="text-align: left	;">
			 	为分类<input id="selectedCategory" style="width:200px;">
			 	<input type="button" value="重命名"  onclick="renameCategory()"  class="button"/>
			</td>						
		</tr>
	</table>	  
	<div id="bodyContainer" style="margin:50px;padding:0;border-bottom: 	1px solid #d2d2d2;padding-top:12px;">
      <div id="treeBox" style="width: 300px; height: 560px; padding:0;margin:0"></div>					
	</div>		 
		<%@include file="../common/footer.jsp"%>
</body>
</html>