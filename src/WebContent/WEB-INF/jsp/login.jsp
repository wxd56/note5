<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" " http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<script type="text/javascript"  src="${baseURL }script/jquery-2.0.3.js"></script>
<title>Note5</title> 
  <style type="text/css">
  	#container{
  		width: 100%;
  		height: 100%;  		
  		text-align: center;
  	}
  	
  	#formTable{
  		margin: auto;
  		margin-top:200px;
  	}
  	
  	#formTable td{
  		padding-top:6px;
  	}
  	#password{
  		width:120px;
  	}
  </style>
  <script type="text/javascript">
 $(function(){
  		$("#password")[0].focus();
  	}
  );
  </script>
</head>
<body > 
	<div id="container">
	<form action="../login/doLogin.do"   method="post">
		<table id="formTable">
			<tr>
				<td>请输入密码：</td>
				<td>	<input type="password" name="password" id="password" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="提交" style="width:100px;">
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>