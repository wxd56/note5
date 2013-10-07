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
	var diaryID = "${targetDiary.id}";
	
	$(
		function (){  
			checkHideStateInCookie();		
		}  
	);
	
	function handler_ctrl_s(){
		saveDoc();
	}
</script>

<script type="text/javascript" src="${baseURL }script/public.js"></script>
  
<script type="text/javascript" src="${baseURL }script/diary/diary.js"></script>
<script type="text/javascript" src="${baseURL }script/keyEventHandler.js"></script>
   
<link rel="stylesheet" type="text/css" 	href="${baseURL }style/footer.css"></link>
<link rel="stylesheet" type="text/css" 	href="${baseURL }style/header.css"></link>
<link rel="stylesheet" type="text/css" 	href="${baseURL }style/diary/diary.css"></link> 
<title>日志系统5.0</title>

</head>
<body >
	<table id="headerBar" style="border:1px solid  #777">
		<tr>
			<td style="padding-left: 12px; padding-top: 6px;width: 500px;">
				<a href="${baseURL }diary/list.do">日志列表</a>
			</td>			
			<td style="text-align: center;">
				 
					标题：<input name="docTitle"  id="docTitle" value="${targetDiary.title }"/>
			 				
			</td>
			<td style = "text-align:right;">
				<ul class="ULMenu"> 
					<li ><a href="newDiary.do"   >新建 </a></li>
					 
						<li><a  href="javascript:void(0)"  onclick="saveDoc()">保存 </a></li>
						<li><a  href="javascript:void(0)"   onclick="deleteDiary()">删除 </a></li>
				 			
				</ul>				
			</td>			
		</tr>
	</table>

	<table id="bodyTable" style="margin-top:16px;">
		<tr>

			<td id="leftColumn">
				<p class="columTitle">日志列表</p> <!-- 子分类 -->
				<ul class="verticalULMenul" style="height: 530px">
			 	<c:forEach items="${pageResult.result }"  var="diary">
			 		<li>
			 			<a href="list.do?pageNo=${pageResult.pageNo}&id=${diary.id}" >${diary.title }</a>
			 		</li>
			 	</c:forEach>
				</ul>
				<div class="pageDIV">
					当前第${pageResult.pageNo }页，共${pageResult.totalPage }页
					<%-- 每页${pageResult.pageSize }条 --%>
					&nbsp;&nbsp;&nbsp;
					<c:if test="${pageResult.pageNo != 1 }">
						<a
							href="${baseURL }diary/list.do?pageNo=${pageResult.pageNo -1}&pageSize=${pageResult.pageSize}">上一页</a>
					</c:if>
					<c:if test="${pageResult.pageNo != pageResult.totalPage }">
						<a
							href="${baseURL }diary/list.do?pageNo=${pageResult.pageNo +1}&pageSize=${pageResult.pageSize}">下一页</a>
					</c:if>
				</div>
			</td>

			<td id="rightColumn">
				<input type="hidden" id="diaryID" value="${targetDiary.id }" />
				<!--  查看编辑文档 -->
				<table id="editDocTable">
					<tr>
						<td style="padding: 0;margin:0;">
								<textarea id="diaryContent"  rows="18" style="width: 97%; height: 99%;">${targetDiary.plainText }</textarea>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>