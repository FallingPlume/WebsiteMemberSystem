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
	#mainBody{
		position: absolute;
		left: 250px;
		right: 0px;
		top: 0px;
		bottom: 0px;
	}
	#info{
		width: 800px;
		height: 30px;
		padding: 15px;
		margin: 25px auto;
		background-color: red;
		border-radius: 10px;
		color: white;
		background-color: rgba(100,100,100,0.8);
	}
	#list{
		padding-bottom: 20px;
	}
	table, tr{
		height: 50px;
	}
	th, td{
		width: 150px;
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
	.button{
		display: inline-block;
		width: 60px;
		height: 35px;
		margin: 0px 2px;
		text-decoration: none;
		line-height: 35px;
		font-size: small;
		background-color: #0077FF;
		color: aliceblue;
		border-radius: 5px;
		transition: background-color 0.5s;
	}
	.button:hover{
		background-color: #0066DD;
	}
	#addAdminBtn{
		width: 130px;
		height: 35px;
		margin: 0px 10px;
		background-color: #0077FF;
		color: aliceblue;
		outline: transparent;
		border: 1px solid transparent;
		border-radius: 5px;
		cursor: pointer;
		font-size: smaller;
		transition: background-color 0.5s;
	}
	#addAdminBtn:hover{
		background-color: #0066DD;
	}
	#flushListBtn{
		display: inline-block;
		width: 130px;
		height: 35px;
		background-color: #0077FF;
		color: aliceblue;
		border-radius: 5px;
		line-height: 35px;
		text-decoration: none;
		font-size: smaller;
		transition: background-color 0.5s;
	}
	#flushListBtn:hover{
		background-color: #0066DD;
	}
	#adminList{
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
		
		//检查权限
		$("#addAdminBtn").click(function(){
			var adminLevel = "${loginAdmin.adminLevel}";
			if(adminLevel == "2"){
				alert("您没有权限添加管理员");
				return;
			}
			$(location).attr("href", "<%=basePath%>AddAdmin.jsp");
		});
		
		//延迟弹出提示框
		setTimeout(function(){
			var error = "<%=request.getParameter("error")%>";
			if(error == "yes"){
				alert("操作失败");
				$(location).attr("href", "<%=basePath%>AdminList.jsp");
			}
			if(error == "cantAdd"){
				alert("您没有权限添加管理员");
				$(location).attr("href", "<%=basePath%>AdminList.jsp");
			}
			if(error == "cantDel"){
				alert("您没有权限删除管理员");
				$(location).attr("href", "<%=basePath%>AdminList.jsp");
			}
			if(error == "cantUpdate"){
				alert("您没有权限修改管理员信息");
				$(location).attr("href", "<%=basePath%>AdminList.jsp");
			}
			if(error == "cantDelSelf"){
				alert("不能删除自己");
				$(location).attr("href", "<%=basePath%>AdminList.jsp");
			}
		}, 50);
		
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
			<li><a class="list" href="<%=basePath%>ManageTable.jsp">管理员信息</a></li>
			<li><a id="adminList" class="list" href="">管理员列表</a></li>
			<li><a class="list" href="<%=basePath%>MemberListServlet">会员列表</a></li>
		</ul>
		<div id="muenBottom">
			<a id="signOut" onClick="return confirm('确定要退出登录吗？');" href="<%=basePath%>SignOutServlet">退出登录</a>
		</div>
	</div>
	<div id="mainBody">
		<c:if test="${loginAdmin.adminLevel eq '2'}">
			<div id="info">
				注：您的权限级别为普通管理员，因此不可以对其他管理员进行删除修改等操作。
			</div>
		</c:if>
		<div id="list">
			<table>
				<tr>
					<th>管理员ID</th>
					<th>管理员账号</th>
					<th>管理员密码</th>
					<th>管理员昵称</th>
					<th>管理员等级</th>
					<th>操作</th>
				</tr>
				<c:forEach var="admin" items="${adminList}">
					<tr>
						<td>${admin.id}</td>
						<td>${admin.adminAccount}</td>
						<td>${admin.adminPwd}</td>
						<td>${admin.adminName}</td>
						<td>${admin.adminLevel eq '1'?'高级管理员':'普通管理员'}</td>
						<td>
							<a class="button" href="<%=basePath%>UpdateAdminServlet?id=${admin.id}">修改</a>
							<a class="button" onClick="return confirm('确定要删除这个管理员吗?该操作不可撤销。');" href="<%=basePath%>DeleteAdminServlet?id=${admin.id}">删除</a>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="6">
						<input type="button" id="addAdminBtn" value="添加管理员">
						<a id="flushListBtn" href="<%=basePath%>AdminListServlet">刷新列表</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>