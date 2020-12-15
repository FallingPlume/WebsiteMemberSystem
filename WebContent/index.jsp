<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员登录</title>

<!--网页样式-->
<style type="text/css">
	body{
		margin: 0;
		background-size: cover;
		background-image: url(img/background.jpg);
	}
	#title{
		text-align: center;
		margin-top: 100px;
		font-family: 宋体;
		color: #B0C4DE;
		text-shadow: 0px 0px 15px blue;
	}
	table{
		height: 180px;
		margin: 25px auto;
	}
	#loginTable{
		width: 600px;
		height: 300px;
		margin: auto;
		border-radius: 25px;
		border: 1px solid transparent;
		background-color: rgba(255,255,255,0.6);
	}
	#loginButton{
		width: 280px;
		height: 45px;
		margin-top: 25px;
		border-radius: 25px;
		border-color: transparent;
		cursor: pointer;
		background-color: steelblue;
		transition: background-color 0.5s;
	}
	#loginButton:hover{
		background-color: #4677A0;
	}
	#loginButton:focus{
		outline: transparent;
	}
	.input{
		width: 300px;
		height: 15px;
		font-size: large;
		border: 1px solid gray;
		border-radius: 25px;
		outline: transparent;
		padding: 10px 15px;
		transition: border-color 1s;
		background-color: rgba(255,255,255,0.6);
	}
	.input:focus{
		border-color: rgb(0,123,255);
	}
</style>

<!--网页脚本-->
<script src="js/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		//输入验证
		$("#loginButton").click(function(){
			var adminAccount = $("#adminAccount").val();
			var adminPwd = $("#adminPwd").val();
			
			if(!/^[a-zA-Z0-9_-]{4,16}$/.test(adminAccount)){
				alert("账号输入不合法");
				$("#adminAccount").focus();
				$("#adminAccount").val("");
				return;
			}
			if(!/^[a-zA-Z0-9_-]{4,16}$/.test(adminPwd)){
				alert("密码输入不合法");
				$("#adminPwd").focus();
				$("#adminPwd").val("");
				return;
			}
			$("#form").submit();
		});
		
		//延迟弹出提示框
		setTimeout(function(){
			var error = "<%=request.getParameter("error")%>";
			if(error == "yes"){
				alert("登录失败，账号或密码错误");
				location.href = "<%=request.getContextPath()%>" + "/index.jsp";
			}
		}, 50);
		
	});
	
</script>

</head>
<body>
	<h1 id="title">欢迎使用会员管理系统</h1>
	<div id="loginTable">
		
		<form id="form" action="<%=basePath%>LoginServlet" method="post">
			<table>
				<tr>
					<td>管理员账号：</td>
					<td><input type="text" name="adminAccount" autocomplete="off" id="adminAccount" class="input" /></td>
				</tr>
				<tr>
					<td>管理员密码：</td>
					<td><input type="password" name="adminPwd" id="adminPwd" class="input" /></td>
				</tr>
				<tr align="center">
					<td colspan="2"><input type="button" id="loginButton" value="登录" /></td>
				</tr>
			</table>
		</form>
		
	</div>
	
</body>
</html>