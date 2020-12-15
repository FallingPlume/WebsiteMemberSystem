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
<title>修改管理员信息</title>

<!--网页样式-->
<style type="text/css">
	body{
		margin: 0;
		background-size: cover;
		background-image: url(img/background.jpg);
	}
	table{
		margin: 25px auto;
		height: 280px;
	}
	#loginTable{
		width: 600px;
		height: 350px;
		margin: 50px auto;
		border-radius: 25px;
		border: 1px solid transparent;
		background-color: rgba(255,255,255,0.6);
	}
	#updateBtn{
		width: 120px;
		height: 45px;
		margin: 10px 20px;
		border-radius: 25px;
		border-color: transparent;
		cursor: pointer;
		color: white;
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
		margin: 10px 20px;
		line-height: 45px;
		border-radius: 25px;
		text-decoration: none;
		color: white;
		font-size: small;
		background-color: rgb(0,123,255);
		transition: background-color 0.5s;
	}
	#backBtn:hover{
		background-color: #0066DD;
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
			var newAdminName = $("#newAdminName").val();
			var newAdminAccount = $("#newAdminAccount").val();
			var newAdminPwd = $("#newAdminPwd").val();
			
			if(newAdminName == ""){
				alert("昵称不能为空");
				$("#newAdminName").focus();
				return;
			}
			if(!/^[a-zA-Z0-9_-]{4,16}$/.test(newAdminAccount)){
				alert("账号输入不合法");
				$("#newAdminAccount").focus();
				$("#newAdminAccount").val("");
				return;
			}
			if(!/^[a-zA-Z0-9_-]{4,16}$/.test(newAdminPwd)){
				alert("密码输入不合法");
				$("#newAdminPwd").focus();
				$("#newAdminPwd").val("");
				return;
			}
			$("#form").submit();
		});
		
		//延迟弹出提示框
		setTimeout(function(){
			var error = "<%=request.getParameter("error")%>";
			if(error == "yes"){
				alert("修改失败");
				location.href = "<%=request.getContextPath()%>" + "/UpdateAdminInfo.jsp";
			}
			if(error == "isHas"){
				alert("修改失败，账号与其他管理员冲突");
				location.href = "<%=request.getContextPath()%>" + "/UpdateAdminInfo.jsp";
			}
		}, 50);
		
	});
	
</script>

</head>
<body>
	<div id="loginTable">
		
		<form id="form" action="<%=basePath%>UpdateAdminInfoServlet" method="post">
			<input type="hidden" name="id" value="${loginAdmin.id}">
			<table>
				<tr>
					<td>
						<b>新昵称</b><br>
						<input type="text" value="${loginAdmin.adminName}" name="newAdminName" autocomplete="off" id="newAdminName" class="input" />
					</td>
				</tr>
				<tr>
					<td>
						<b>新账号</b> <font size="1px">不能与其他管理员重复</font><br>
						<input type="text" value="${loginAdmin.adminAccount}" name="newAdminAccount" id="newAdminAccount" class="input" />
					</td>
				</tr>
				<tr>
					<td>
						<b>新密码</b><br>
						<input type="text" value="${loginAdmin.adminPwd}" name="newAdminPwd" id="newAdminPwd" class="input" />
					</td>
				</tr>
				<tr align="center">
					<td>
						<a id="backBtn" href="<%=basePath%>ManageTable.jsp">返回</a>
						<input type="button" id="updateBtn" value="修改" />
					</td>
				</tr>
			</table>
		</form>
		
	</div>
	
</body>
</html>