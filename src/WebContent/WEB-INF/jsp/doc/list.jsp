<%@ page language="java" contentType="text/html; charset=UTF-8"  	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
	var ctxPath = "${baseURL}";	  
	
	//页面加载完毕
	function bodyLoad(){
		renderUeditor();
		checkHideStateInCookie();		
	} 
	var editor_a1;
	function renderUeditor(){
		var myEditor = document.getElementById("myEditor1");
		if(myEditor == null){
			return;
		}
		editor_a1 = new baidu.editor.ui.Editor();
	    editor_a1.render( 'myEditor1' );         
	    editor_a1.setHeight(555);									        
	    editor_a1.addListener("keydown",function(type, evt){
	        	//保存
	        	if(evt.ctrlKey && evt.keyCode == 83){     
	        		saveDoc('${selectedCategoryId}','${document.id}');
	    	    	evt.preventDefault(); 
	    	    }
	    	    
	    	    //隐藏
	    	    if(evt.ctrlKey && evt.keyCode == 72){
	    	    	showHideDiv();
	    	    	evt.preventDefault();
	    	    }
	    	    //没有按下 ctrl shit alt 键时设置为待保存
	    		if(!evt.ctrlKey  && !evt.shiftKey && !evt.altKey){
	    			setToSave(true);
	    		}					    		
	    	}
	    );		
	}
</script>

<script type="text/javascript" src="${baseURL }script/public.js"></script>
<script type="text/javascript" src="${baseURL }ueditor/editor_config.js"></script>
<script type="text/javascript" src="${baseURL }ueditor/editor_all.js"></script>
<script type="text/javascript" src="${baseURL }script/doc/doc.js"></script>
<script type="text/javascript" src="${baseURL }script/keyEventHandler.js"></script>
   
<link rel="stylesheet" type="text/css" 	href="${baseURL }style/footer.css"></link>
<link rel="stylesheet" type="text/css" 	href="${baseURL }style/header.css"></link>
<link rel="stylesheet" type="text/css" 	href="${baseURL }style/doc/doc.css"></link>
<link rel="stylesheet" href="${baseURL }ueditor/themes/default/ueditor.css"/>
 

<title>文档管理系统3.0</title>
</head>
<body onload="bodyLoad();"  >
   <!--  显示控制台 -->
   <div id="console" ></div>
	<table id="headerBar">
		<tr>
			<td style="padding-left: 12px; padding-top: 6px;width: 500px;">
				<a href="${baseURL }doc/show.do">文档分类</a>
				<c:forEach items="${parentList }" var="category" > 
						-><a href="${baseURL }doc/show.do?categoryId=${category.id}">${category.name }</a>										
				</c:forEach>				
			</td>			
			<td style="text-align: center;">
				<c:if  test="${docId != null }">
					标题：<input name="docTitle"  id="docTitle" value="${document.title }"/>
				</c:if> 				
			</td>
			<td style = "text-align:right;">
				<ul class="ULMenu"> 
					<li ><a href="javascript:void(0)" style="color:white" onclick="newDoc('${selectedCategoryId}')">新建文档</a></li>
					<c:if  test="${docId != null }">
						<li><a  href="javascript:void(0)" style="color:white" onclick="saveDoc('${selectedCategoryId}','${document.id}')">保存文档</a></li>
					</c:if>
					<c:if  test="${docId != null }">
						<li><a  href="javascript:void(0)" style="color:white" onclick="deleteDoc('${selectedCategoryId}','${document.id}')">删除文档</a></li>
					</c:if>						
				</ul>				
			</td>			
		</tr>
	</table>

	<table id="bodyTable">
		 <tr>
		 	<c:if  test="${docId == null }">
		 	<td id="leftColumn">
		 		<p class="columTitle">文档分类列表</p>
		 		<!-- 子分类 -->
		 		<ul class="verticalULMenul">
		 		<c:forEach items="${subList }"  var="category">
		 			<li class="item">
		 				<a href="${baseURL }doc/show.do?categoryId=${category.id}">${category.name }</a>
		 			</li>
		 		</c:forEach>
		 		</ul>
			</td>
			</c:if>
			<td id="rightColumn">				
				<c:choose>
					<c:when test="${docId != null }">
						<!--  查看编辑文档 -->
						<table id="editDocTable">
							<tr>
								<td>								  
								   <script type="text/plain" id="myEditor1" style="width:100%;height: 100%;">${document.content }</script>								 
								</td>
							</tr>
						</table>	
					</c:when>
					<c:otherwise>
							<!--  文档列表 -->
							<table id="listDocTable"  >
								<tr><th style="width: 60%;">文档名称</th><th>创建时间</th><th>最后修改时间</th></tr>
								<c:forEach items="${docs}"  var="doc">
								<tr>
									<td>
										<a href="${baseURL }doc/show.do?categoryId=${selectedCategoryId}&docId=${doc.id}">${doc.title }</a>
									</td>
									<td>
										<fmt:formatDate value="${doc.createDate }" pattern="yyyy-MM-dd HH:mm"/>										
									</td>
									<td>
										<fmt:formatDate value="${doc.lastModify}" pattern="yyyy-MM-dd HH:mm"/>										
									</td>
								</tr>
								</c:forEach>
							</table>
							<!--  打开文档的历史操作  -->
							<div id="optHistory" >
								<p class="columTitle">最近打开的文档</p>
								<ul class="verticalULMenul">
									<c:forEach items="${historyList }"  var="item">
										<li class="item">
											<a href="${baseURL }doc/show.do?categoryId=${item.categoryID}&docId=${item.docID}">${item.docName }</a>
										</li>
									</c:forEach>									
							</ul>
							</div>
					</c:otherwise>
				</c:choose>				
			</td>
		</tr>
	</table>
	
	<%@include file="../common/footer.jsp" %>
</body>
</html>