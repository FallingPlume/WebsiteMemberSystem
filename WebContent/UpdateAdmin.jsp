<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改管理员信息</title>

<!--网页样式-->
<style type="text/css">
	body{
		margin: 0;
		background-size: cover;
		background-image: url(img/background.jpg);
	}
	table{
		height: 350px;
		margin: 20px auto;
	}
	#loginTable{
		width: 600px;
		height: 400px;
		margin: 50px auto;
		border-radius: 25px;
		border: 1px solid transparent;
		background-color: rgba(255,255,255,0.6);
	}
	#updateBtn{
		width: 120px;
		height: 45px;
		margin: 10px 15px;
		border-radius: 25px;
		border-color: transparent;
		cursor: pointer;
		background-color: rgb(0,123,255);
		transition: background-color 0.5s;
	}
	#updateBtn:hover{
		background-color: #0066DD;
	}
	#updateBtn:focus{
		outline: transparent;
	}
	#backBtn{
		display: inline-block;
		width: 120px;
		height: 45px;
		margin: 10px 15px;
		line-height: 45px;
		border-radius: 25px;
		text-decoration: none;
		color: black;
		font-size: small;
		background-color: rgb(0,123,255);
		transition: background-color 0.5s;
	}
	#backBtn:hover{
		background-color: #0066DD;
	}
	select{
		width: 320px;
		height: 30px;
		border: 1px solid gray;
		border-radius: 3px;
		outline: transparent;
		background-color: rgba(255,255,255,0.5);
		transition: border-color 1s;
	}
	select:focus{
		border-color: rgb(0,123,255);
	}
	.opt{
		font-size: medium;
	}
	.input{
		width: 300px;
		height: 10px;
		border: 1px solid gray;
		border-radius: 3px;
		outline: transparent;
		padding: 10px 10px;
		background-color: rgba(255,255,255,0.5);
		transition: border-color 1s;
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
		$("#updateBtn").click(function(){
			var adminName = $("#adminName").val();
			var adminAccount = $("#adminAccount").val();
			var adminPwd = $("#adminPwd").val();
			
			if(adminName == ""){
				alert("昵称不能为空");
				$("#adminName").focus();
				return;
			}
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
				alert("修改失败");
				$(location).attr("href", "<%=basePath%>UpdateAdmin.jsp");
			}
			if(error == "isHas"){
				alert("修改失败，账号与其他管理员冲突");
				$(location).attr("href", "<%=basePath%>UpdateAdmin.jsp");
			}
		}, 50);
		
	});
	
</script>

</head>
<body>
	<div id="loginTable">
		
		<form id="form" action="<%=basePath%>UpdateAdminServlet" method="post">
			<input type="hidden" name="id" value="${updateAdmin.id}">
			<table>
				<tr>
					<td>
						<b>管理员昵称</b><br>
						<input type="text" name="adminName" value="${updateAdmin.adminName}" id="adminName" class="input" />
					</td>
				</tr>
				<tr>
					<td>
						<b>管理员账号</b> <font size="1px">不能与其他管理员重复</font><br>
						<input type="text" name="adminAccount" value="${updateAdmin.adminAccount}" id="adminAccount" class="input" />
					</td>
				</tr>
				<tr>
					<td>
						<b>管理员密码</b><br>
						<input type="text" name="adminPwd" id="adminPwd" value="${updateAdmin.adminPwd}" class="input" />
					</td>
				</tr>
				<tr>
					<td>
						<b>权限等级</b><br>
						<select name="adminLevel">
							<option <c:if test="${updateAdmin.adminLevel eq '2'}">selected</c:if> class="opt" value="2">普通管理员</option>
							<option <c:if test="${updateAdmin.adminLevel eq '1'}">selected</c:if> class="opt" value="1">高级管理员</option>
						</select>
					</td>
				</tr>
				<tr align="center">
					<td>
						<a id="backBtn" href="<%=basePath%>AdminListServlet">返回</a>
						<input type="button" id="updateBtn" value="修改" />
					</td>
				</tr>
			</table>
		</form>
		
	</div>
	
</body>
</html>