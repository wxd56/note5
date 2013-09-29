<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<table id="pageBottomHolder" style="margin-top: 15px;">
	<tr>
		<td style="text-align: left; font-size: 14px;">${pageTitle}</td>
		<td style="text-align: center;">
			 NOTE5</td>
		<td style="text-align: right;">
			<!--  底部链接 -->
			<ul class="ULMenu">
				<li><a href="${baseURL}target/show.do">目标</a></li>
				<li><a href="${baseURL}diary/list.do">日志</a></li>
				<li><a href="${baseURL}doc/show.do">文档</a></li>
				<li><a href="${baseURL}category/manage.do">分类管理</a></li>				
				<li><a href="${baseURL}logout.do">退出</a></li>
				
			</ul>
		</td>
	</tr>
</table>
<script>
	function 	showVersionInfo(){
		window.open("${baseURL }version/current.do");		
	}
</script>