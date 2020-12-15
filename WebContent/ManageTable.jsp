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
<title>管理面板</title>

<!--网页样式-->
<style type="text/css">
	
	body{
		margin: 0px;
		background-image: url(img/background.jpg);
		background-attachment: fixed;
		background-size: cover;
	}
	#menuTable{
		position: fixed;
		width: 250px;
		top: 0px;
		bottom: 0px;
		box-shadow: 0px 0px 15px black;
		background-color: rgba(52,58,64,0.8);
	}
	#menuTitle{
		margin: 15px auto;
		height: 45px;
		text-align: center;
		color: rgb(193,198,207);
	}
	#adminID{
		height: 45px;
		border-bottom: 1px solid gray;
		text-align: center;
		font-family: 新宋体;
		color: rgb(193,198,207);
	}
	#colorSel{
		width: auto;
		height: 23px;
		text-align: center;
	}
	.color{
		width: 20px;
		height: 20px;
		margin: 0px 5px;
		cursor: pointer;
		border: 3px solid transparent;
	}
	.color:hover{
		border-color: rgb(193,198,207);
	}
	#color1{
		background-color: rgb(0,125,173);
	}
	#color2{
		background-color: rgb(53,79,92);
	}
	#color3{
		background-color: rgb(84,175,253);
	}
	ul{
		padding: 0px;
		list-style: none;
	}
	.list{
		display: block;
		height: 50px;
		width: 230px;
		margin: 5px auto;
		text-align: center;
		line-height: 50px;
		border-radius: 5px;
		color: rgb(152,191,217);
		text-decoration: none;
	}
	.list:hover{
		background-color: rgba(255,255,255,0.2);
	}
	#muenBottom{
		position: absolute;
		width: 70px;
		margin: 30px auto;
		left: 0px;
		right: 0px;
		bottom: 0px;
	}
	#signOut{
		text-decoration: none;
		color: rgb(152,191,217);
		border: 2px solid transparent;
	}
	#signOut:hover{
		border-bottom: 2px solid rgb(152,191,217);
	}
	#pageInfo{
		width: 600px;
		height: 80px;
		margin: 25px auto;
		padding: 25px;
		border-radius: 10px;
		color: white;
		background-color: rgba(100,100,100,0.7);
	}
	#mainBody{
		position: absolute;
		left: 250px;
		right: 0px;
		top: 0px;
		bottom: 0px;
	}
	#list{
		padding-bottom: 20px;
	}
	table, tr{
		height: 50px;
	}
	th{
		width: 160px;
	}
	td{
		width: 300px;
	}
	table{
		border-collapse: collapse;
		background-color: rgba(255,255,255,0.5);
		text-align: center;
		margin: 30px auto;
	}
	table tr:nth-child(even){
		background-color: rgba(230,230,230,0.5);
	}
	#updateInfoBtn{
		display: block;
		width: 120px;
		height: 40px;
		margin: 0px 10px;
		text-align: center;
		text-decoration: none;
		line-height: 40px;
		background-color: #0077FF;
		color: aliceblue;
		border-radius: 5px;
		transition: background-color 0.5s;
	}
	#updateInfoBtn:hover{
		background-color: #0066DD;
	}
	#adminInfo{
		background-color: rgb(0,123,255);
	}
	
</style>

<!--网页脚本-->
<script src="js/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.cookie.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		if($.cookie("background") != null){
			$("body").css("background-image", "url("+$.cookie("background")+")");
		}
		
		var flag = true;
		$("#pwd").click(function(){
			if(flag){
				$("#pwd").html("${loginAdmin.adminPwd}");
				flag = false;
			}else{
				$("#pwd").html("点击显示（再次点击隐藏）");
				flag = true;
			}
		})
		
		$("#color1").click(function(){
			var background = "img/pixiv56342681_1.jpg";
			$.cookie("background", background);
			window.location.reload();
		});
		$("#color2").click(function(){
			var background = "img/background.jpg";
			$.cookie("background", background);
			window.location.reload();
		});
		$("#color3").click(function(){
			var background = "img/illust_44631706_20180710_181525.png";
			$.cookie("background", background);
			window.location.reload();
		});
		
	});
	
</script>

</head>
<body>

	<div id="menuTable">
		<h2 id="menuTitle">管理面板</h2>
		<h3 id="adminID">管理员：${loginAdmin.adminName}</h3>
		
		<div id="colorSel">
			<input type="button" class="color" id="color1">
			<input type="button" class="color" id="color2">
			<input type="button" class="color" id="color3">
		</div>
		
		<ul>
			<li><a id="adminInfo" class="list" href="<%=basePath%>ManageTable.jsp">管理员信息</a></li>
			<li><a class="list" href="<%=basePath%>AdminListServlet">管理员列表</a></li>
			<li><a class="list" href="<%=basePath%>MemberListServlet">会员列表</a></li>
		</ul>
		<div id="muenBottom">
			<a id="signOut" onClick="return confirm('确定要退出登录吗？');" href="<%=basePath%>SignOutServlet">退出登录</a>
		</div>
	</div>
	<div id="mainBody">
		<div id="pageInfo">
			<c:choose>
				<c:when test="${loginAdmin.adminLevel eq '1'}">
					欢迎您：${loginAdmin.adminName}<br>
					您的权限级别为<b>高级管理员</b>，您可以管理站内所有会员以及其他普通管理员。<br>
					在本页面您可以查看和修改自己的信息。
				</c:when>
				<c:when test="${loginAdmin.adminLevel eq '2'}">
					欢迎您：${loginAdmin.adminName}<br>
					您的权限级别为<b>普通管理员</b>，您可以管理站内所有会员，但没有权限管理高级管理员和其他普通管理员，以及添加管理员。<br>
					在本页面您可以查看和修改自己的信息。
				</c:when>
			</c:choose>
		</div>
		<div id="list">
			<table>
				<tr>
					<th>管理员ID</th>
					<td>${loginAdmin.id}</td>
				</tr>
				<tr>
					<th>管理员名称</th>
					<td>${loginAdmin.adminName}</td>
				</tr>
				<tr>
					<th>管理员账号</th>
					<td>${loginAdmin.adminAccount}</td>
				</tr>
				<tr>
					<th>管理员密码</th>
					<td id="pwd">点击显示（再次点击隐藏）</td>
				</tr>
				<tr>
					<th>管理员等级</th>
					<td>${empty loginAdmin?'':loginAdmin.adminLevel eq '1'? '高级管理员':'普通管理员'}</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<a id="updateInfoBtn" href="<%=basePath%>UpdateAdminInfo.jsp">修改信息</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
</body>
</html>