<%@ page language="java" contentType="text/html; charset=UTF-8"  	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${baseURL }script/public.js"></script>
<script type="text/javascript" src="${baseURL }script/target/tab.js"></script>
<script type="text/javascript" src="${baseURL }script/target/targetV3.js"></script> 
<script type="text/javascript" src="${baseURL }script/keyEventHandler.js"></script>

<link rel="stylesheet" type="text/css" 	href="${baseURL }style/target/tab.css"></link>
<link rel="stylesheet" type="text/css" 	href="${baseURL }style/header.css"></link>
<%-- <link rel="stylesheet" type="text/css" 	href="${baseURL }style/target/target.css"></link> --%>
<link rel="stylesheet" type="text/css" 	href="${baseURL }style/footer.css"></link>

<script type="text/javascript">
	var ctxPath = "${baseURL}";	  
</script>
<title>目标管理系统5.0</title>
<style>
	
/** 页面中包含所有内容的最外层表格，用于布局*/
.bodyTable {
	width: 100%;
	height: 573px;
	border-collapse: collapse;
	padding: 0;
	margin: 0;
}

.bodyTable tr td{
	margin:0;
	padding:0;
}

/** 面板样式*/
td.pane {
	padding: 0;
	margin: 0;
	border: 1px solid #d2d2d2;
} 

/** 面板标题*/
.paneHeader {
	background-color: rgb(241, 241, 241);
	height: 20px;
	color: rgb(102, 102, 102);		
	padding-top:8px;
	padding-left:15px;
	vertical-align: middle;
} 

.paneBody {
	margin: 0;
	padding: 0;
	height: 500px;
	overflow: auto;	
}

/**  每日事务 */
#dayArrangeTD {
	width: 600px;
	padding:0;
	margin:0;
}

#weekArrangeTD {
	width: 400px;
	padding:0;
	margin:0;
}

#monthArrangeTable{
	margin: 0;
	padding: 0;
	border-collapse: collapse;
	
}

.itemTable {
	width: 100%;
	padding: 0;
	border-collapse: collapse;
} 


/**  内容Item的样式 */
.itemContent1 {
	width: 95%;
	word-break: break-all;	
}

.itemContent2 {
	vertical-align: text-top;
	text-align: left;
}

.itemTable td{
	border-top:12px solid white;
}

.toolBoxMenu {
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	background-color: rgb(241, 241, 241);
	border: 1px solid rgb(241, 241, 241);
	display: none;
	margin-left: -60px;		
}

.toolBoxMenu li{
	margin: 0;
	padding:0;
	padding: 7px 12px 3px 12px;
}

.toolBoxMenu li a {
	
	width: 100%;
	display: block;
	text-decoration: none;
	color: black;
	padding:0;
}

.toolBoxMenu li:hover {
	 background-color: white;	
}

/** 输入目标的样式 */
.addTargetTable {
	border-collapse: collapse;
	width: 90%;
}

 
 

.inputTargetTD textarea {
	width: 90%;
	height: 38px;
	margin-left: 12px;
}

.button {
	background-color: #4D90FE;
	display: block;
	text-decoration: none;
	color: white;
	border: 1px solid #3079ED;
	font-weight: bold;
	height: 20px;
	width: 40px;
	padding: 5px 1px 0 12px;
}

/** 目标已经完成*/
.finishItem {
	font-size: 13px;
	font-style: italic;
	text-decoration: line-through;
	color: gray;
	font-weight:bold; 
	background-color: white;
}

.itemTR:hover {
	background-color: rgb(241, 241, 241);
}
  
</style>
</head>
<body onclick="hideToolBoxMenu()" onload="bodyLoad('${currDateTime}','${currWeek}');">
   <!--  显示控制台 -->
   <div id="console" class="tartgetV3Console"></div>
	<table id="headerBar">
		<tr>
			<td style="padding-left: 12px; padding-top: 6px;">当前选择日期：${currDateTime} &nbsp;第${currWeekOfYear }周&nbsp;${weekNames[currWeek]}</td>			
			<td style = "text-align:right;">
				<ul class="ULMenu">
					<li>
						<input style="width:70px;" type="text"  value="${currDateTime}" id="inputSelectedDate" />
					</li>
					<li ><a href="javascript:void(0)" style="color:white" onclick="changeSelectedDate('switch')">切换日期</a></li>
					<li><a  href="javascript:void(0)" style="color:white" onclick="changeSelectedDate('now')">回到现在</a></li>
				</ul>				
			</td>			
		</tr>
	</table>

	<table class="bodyTable">
		<tr>
			<td class="pane" id="dayArrangeTD">
				<div class="paneHeader">	每日事务</div>
				<div class="paneBody">
					<ul class="tabs"> 
						
						<!--  输出星期一到星期日 -->
						<c:forEach items="${dayOfWeeks }" var="day" varStatus="stat">
							<li>
								<a href="javascript:void(0)" onclick="showtab(${stat.index +1},7,'${day }');"   	id="tabH${stat.index+1 }" 
									<c:if test="${currWeek == stat.index+1}">
										style="background-color:#f1f1f1;"
									</c:if>
								>${weekNames[stat.index +1]}</a>
							</li>							
						</c:forEach>					
					</ul> 

					<!--  得到七天的目标 -->
					<c:forEach items="${dayTargetsOfWeek }" var = "dayTargets" varStatus="stat">
						<div id="tab${ stat.index+1}" 
							<c:choose>
								<c:when test="${currWeek == stat.index+1}">
									class="tab"
								</c:when>
								<c:otherwise>
									 class="tab tabHidden"
								</c:otherwise>
							</c:choose>
						>
							<table class="itemTable" id="dayArrangeTable${stat.index +1}">							
								<!--  输出每天具体的目标 -->
								<c:forEach items="${dayTargets}" var = "t" varStatus="stat2">
									<c:choose>
										<c:when test="${t.state eq 'finished' }">
												<tr class="itemTR  finishItem"  >
													<td style="vertical-align: top">${stat2.index+1}.</td>
													<td class="itemContent1" id="dayArrangeItemTD${t.id }">${t.content }</td>
													<td class="itemContent2" id="dayArrangeItemTDRight${t.id }"></td>
												</tr>
										</c:when>
										<c:otherwise>
												<tr class="itemTR" id="dayArrangeTR${t.id}"    onmouseout="mouseOutItem('day','${t.id}')"  	  onmouseover="mouseOverItem('day','${t.id}')">
													 <td>${stat2.index+1}.</td>
													 <td class="itemContent1" id="dayArrangeItemTD${t.id }">${t.content }</td>
													 <td class="itemContent2" id="dayArrangeItemTDRight${t.id }">
														<img 	src="${baseURL }images/v3/select.jpg" id="boxTool${t.id }" 		style="visibility: hidden" 		onclick="showToolBoxMenu('day','${t.id}',event)"></img>
														<ul class="toolBoxMenu" id="toolBoxMenu${t.id }">
															<li><a href="javascript:void(0)" 		onclick="deleteItem('day','${t.id}','dayArrangeTable${stat.index +1}')">删除</a></li>
															<li><a href="javascript:void(0)"  		onclick="finishItem('day','${t.id}')">标记完成</a></li>																													
														</ul>
													 </td>
											  	</tr>
										</c:otherwise>
									</c:choose>									
								</c:forEach>
							</table>
						</div>				
					</c:forEach>
				</div>
				 <!-- 面板底部输入框 -->				 
				<table class="addTargetTable">
						<tr>
							<td class="inputTargetTD"><textarea 	style="height: 30px;" id="addDayTargetTA"></textarea></td>
							<td><a href="javascript:void(0)" class="button" onclick="addTarget('day')">添加</a></td>
						</tr>
				</table>				
			</td>
			<td class="pane" id="weekArrangeTD">
				<div class="paneHeader"><!--  显示当前日期所在周的以后三周内的目标 -->
				  周目标				
				</div>
				<div class="paneBody">
					<table class="itemTable" id="weekArrangeTable">
						<c:forEach items = "${weekTargets }" var = "target" varStatus="stat">							
							   	<c:choose>
										<c:when test="${target.state eq 'finished' }">
												<tr class="itemTR   finishItem"  >
													<td>${stat.index+1}.</td>
													<td class="itemContent1" id="weekArrangeItemTD${target.id }"> ${target.content} </td>
													<td class="itemContent2" id="weekArrangeItemTDRight${target.id }">	</td>
												</tr>
										</c:when>
										<c:otherwise>
												<tr class="itemTR" id="weekArrangeTR${target.id }"   onmouseout="mouseOutItem('week','${target.id }')" 	onmouseover="mouseOverItem('week','${target.id }')">
										<td style="vertical-align: top">${stat.index+1}.</td>
													<td class="itemContent1" id="weekArrangeItemTD${target.id }"> ${target.content} </td>
													<td class="itemContent2" id="weekArrangeItemTDRight${target.id }">
														<img 	src="${baseURL }images/v3/select.jpg" id="boxToolWeek${target.id }" 	style="visibility: hidden"  onclick="showToolBoxMenu('week','${target.id}',event)"></img>
														<ul class="toolBoxMenu" id="toolBoxMenuWeek${target.id }">
															<li><a href="javascript:void(0)"  	onclick="deleteItem('week','${target.id}','weekArrangeTable')">删除</a></li>
															<li><a href="javascript:void(0)"  	onclick="finishItem('week','${target.id}')">标记完成</a></li>
														</ul>
													</td>
											   </tr>
										</c:otherwise>
								</c:choose>															
						</c:forEach>					
					</table>
				</div> <!-- 面板底部输入框 -->
				<table class="addTargetTable">
					<tr>
						<td class="inputTargetTD"><textarea  	style="height: 30px;" id="addWeekTargetTA"></textarea></td>
						<td><a href="javascript:void(0)" class="button"   	onclick="addTarget('week')">添加</a></td>
					</tr>
				</table>
			</td>
			<td class="pane" id="monthArrangeTD">
				<div class="paneHeader" >
						月目标 	
				 </div>
				<div class="paneBody">
					<table class="itemTable" id="monthArrangeTable">
						<c:forEach items="${monthTargets }" var = "t" varStatus="stat">				
							 	<c:choose>
										<c:when test="${t.state eq 'finished' }">
												<tr class="itemTR  finishItem" >
														<td>${stat.index+1}.</td>
														<td class="itemContent1" id="monthArrangeItemTD${t.id }">${t.content}</td>
														<td class="itemContent2" id="monthArrangeItemTDRight${t.id }"></td>
												</tr>
										</c:when>
										<c:otherwise>
												<tr class="itemTR" id="monthArrangeTR${t.id }"  	onmouseout="mouseOutItem('month','${t.id}')"  	onmouseover="mouseOverItem('month','${t.id}')">
														<td style="vertical-align: top">${stat.index+1}.</td>
														<td class="itemContent1" id="monthArrangeItemTD${t.id }">${t.content}</td>
														<td class="itemContent2" id="monthArrangeItemTDRight${t.id }">
															<img 	src="${baseURL }images/v3/select.jpg" id="boxToolMonth${t.id }" 		style="visibility: hidden"  		onclick="showToolBoxMenu('month','${t.id}',event)"></img>
															<ul class="toolBoxMenu" id="toolBoxMenuMonth${t.id }">
																<li><a href="javascript:void(0)"  		onclick="deleteItem('month','${t.id}','monthArrangeTable')">删除</a></li>
																<li><a href="javascript:void(0)"  		onclick="finishItem('month','${t.id}')">标记完成</a></li>																
															</ul>
														</td>
												</tr>
										</c:otherwise>
								</c:choose>							
						</c:forEach>
					</table>
			 </div>  
			    <table class="addTargetTable">
					<tr>
						<td class="inputTargetTD"><textarea style="height: 30px;"
								id="addMonthTargetTA"></textarea></td>
						<td>
							<a href="javascript:void(0)" class="button"  		onclick="addTarget('month')">添加</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<%@include file="../common/footer.jsp" %>
</body>
</html>