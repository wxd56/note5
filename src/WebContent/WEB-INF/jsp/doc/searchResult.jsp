<%@ page language="java" contentType="text/html; charset=UTF-8"  	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"  src="${baseURL }script/jquery-2.0.3.js"></script>
<script type="text/javascript">
	var ctxPath = "${baseURL}";	  
	$(
		function init(){
			$('#keyWords')[0].focus();
		}		
	);
</script>

<link rel="stylesheet" type="text/css" 	href="${baseURL }style/footer.css"></link>
<link rel="stylesheet" type="text/css" 	href="${baseURL }style/header.css"></link>

<title>文档管理系统3.0</title>
</head>
<body onload="bodyLoad();"  >
  	<div style="text-align:center;heigth:60px;padding-top:20px;">
  			<form id="searchForm"  action="${baseURL }doc/search.do" method="post">
							<input name="keyWords"   id="keyWords"   type="text"  style="width:300px;" placeholder="输入文档标题进行搜索"/>
			</form>
  	</div>
	
	<div style="font-weight: bold;padding-left:20px;">用时${time }秒,搜索${count }条。</div>
	<div style="padding-left:20px;padding-top:20px;">
		<c:forEach items="${docs }" var="doc">
			<a href="${baseURL }doc/show.do?categoryId=${doc.category.id}&docId=${doc.id}">${doc.title }</a><br><br><br>
		</c:forEach>
	</div>
	
	<c:if test="${count eq 0 }">
		<div style="height:400px;"></div>
	</c:if>
	<%@include file="../common/footer.jsp" %>
</body>
</html>