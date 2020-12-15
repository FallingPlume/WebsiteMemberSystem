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
	#search{
		margin-top: 30px;
		color: white;
		text-align: center;
	}
	.input{
		height: 25px;
		outline: transparent;
		margin-right: 10px;
		border: 1px solid transparent;
		transition: border-color 1s;
		background-color: rgba(255,255,255,0.6);
	}
	.input:focus{
		border-color: rgb(0,123,255);
	}
	#searchButton{
		width: 65px;
		height: 35px;
		border-radius: 5px;
		border: 0px;
		color: aliceblue;
		outline: transparent;
		cursor: pointer;
		margin-left: 10px;
		background-color: #0077FF;
		transition: background-color 0.5s;
	}
	#searchButton:hover{
		background-color: #0066DD;
	}
	#reflush{
		display: inline-block;
		width: 65px;
		height: 35px;
		line-height: 35px;
		text-decoration: none;
		color: aliceblue;
		border-radius: 5px;
		font-size: smaller;
		margin-left: 10px;
		background-color: #0077FF;
		transition: background-color 0.5s;
	}
	#reflush:hover{
		background-color: #0066DD;
	}
	#list{
		padding-bottom: 20px;
	}
	select{
		height: 25px;
		border: 1px solid gray;
		border-radius: 3px;
		outline: transparent;
		background-color: rgba(255,255,255,0.5);
		transition: border-color 1s;
	}
	select:focus{
		border-color: rgb(0,123,255);
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
		margin: 20px auto;
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
	#userList{
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
		
		//延迟弹出提示框
		setTimeout(function(){
			var error = "<%=request.getParameter("error")%>";
			if(error == "yes"){
				alert("操作失败");
				$(location).attr("href", "<%=basePath%>AdminList.jsp");
			}
		}, 50);
		
		//更换背景
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
		
		//会员类别颜色
		$(".memberType").each(function(){
			if($(this).html() == "VIP会员"){
				$(this).css("color","red");
			}
			if($(this).html() == "非正式会员"){
				$(this).css("color","lightgray");
			}
		});
		
		//会员查找按钮
		$("#searchButton").click(function(){
			$("#form").submit();
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
			<li><a class="list" href="<%=basePath%>ManageTable.jsp">管理员信息</a></li>
			<li><a class="list" href="<%=basePath%>AdminListServlet">管理员列表</a></li>
			<li><a id="userList" class="list" href="<%=basePath%>MemberListServlet">会员列表</a></li>
		</ul>
		<div id="muenBottom">
			<a id="signOut" onClick="return confirm('确定要退出登录吗？');" href="<%=basePath%>SignOutServlet">退出登录</a>
		</div>
	</div>
	<div id="mainBody">
		<div id="search">
			<form id="form" action="<%=basePath%>SearchMemberServlet" method="post">
				UID：<input type="number" name="searchId" class="input" >
				会员昵称：<input type="text" name="searchName" class="input" >
				会员等级：<input type="number" name="searchLevel" class="input" >
				会员类别：<select name="searchType">
					<option value="">不选择</option>
					<option value="0">非正式会员</option>
					<option value="1">正式会员</option>
					<option value="2">VIP会员</option>
				</select>
				<input type="button" value="查找" id="searchButton" >
				<a id="reflush" href="<%=basePath%>MemberListServlet">刷新</a>
			</form>
		</div>
		<div id="list">
			<table>
				<tr>
					<th>UID</th>
					<th>会员账号</th>
					<th>会员密码</th>
					<th>会员昵称</th>
					<th>会员等级</th>
					<th>会员类别</th>
					<th>操作</th>
				</tr>
				<c:forEach var="member" items="${memberList}">
					<tr>
						<td>${member.uid}</td>
						<td>${member.memberAccount}</td>
						<td>${member.memberPwd}</td>
						<td>${member.memberName}</td>
						<td>${member.memberLevel}</td>
						<td class="memberType">${member.memberType eq '0'?'非正式会员':member.memberType eq '1'?'正式会员':'VIP会员'}</td>
						<td>
							<a class="button" href="<%=basePath%>UpdateMemberServlet?id=${member.uid}">修改</a>
							<a class="button" onClick="return confirm('确定要删除这个会员吗?该操作不可撤销。');" href="<%=basePath%>DeleteMemberServlet?id=${member.uid}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>