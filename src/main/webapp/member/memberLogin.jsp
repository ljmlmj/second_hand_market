<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.javalab.vo.*"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		
		function fn_validate() {
			var frmLogin = document.frmLogin;
			var member_id = frmLogin.member_id.value;
			var pwd = frmLogin.pwd.value;

			if (member_id.length == 0 || member_id == "") {
				alert("아이디를 입력하세요.");
				frmLogin.member_id.focus();
			} else if ((pwd.length == 0 || pwd == "")) {
				alert("비밀번호를 입력하세요.");
				frmLogin.pwd.focus();
			} else {
				frmLogin.method = "post";
				frmLogin.action = "${contextPath}" + "/login"; //로그인 서블릿 호출
				frmLogin.submit();
			}
		}
		
	</script>
<style>
@import url(http://fonts.googleapis.com/earlyaccess/hanna.css);

* {
	background-color: #FBF8EF;
}

header {
	background-color: #FBF8EF;
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
}

.headCategory {
text-align: center;
}

a.link {
font-size: 25px;
text-decoration: none;
display: inline-block;
padding: 15px 25px;
color: olive; 
margin-top: 8px;
margin-left: 120px;
font-family: 'Hanna', serif;

}

a.link:link a.link:visited { 
color: olive; 
}

a.link:hover {
color:white;
text-shadow: 1px 1px 3px black;
}

.leftnav{
position: absolute;
top: 60px;
right: 250px;
font-size: 40px;
font-family: 'Hanna', serif;
cursor: pointer;
text-decoration: none;
color: olive; 
}

.leftnav:hover{
	color:white;
	text-shadow: 1px 1px 3px black;
}

#logo {
	width: 250px;
	height: 80px;
	cursor: pointer;
	margin-top: 35px;
}

.search {
	height: 40px;
	width: 400px;
	border: 1px solid silver;
	text-align: center;
	background: #ffffff;
	margin: 0 auto;
}

.searchText {
	font-size: 16px;
	width: 325px;
	padding: 10px;
	border: 1px;
	outline: none;
	float: center;
	background-color: white;
}

.searchBtn {
	height: 40px;
	width: 50px;
	border: 0px;
	background: silver;
	outline: none;
	float: right;
	color: #ffffff;
}

.headerLind {
	border: solid 1px black;
	width: 90%;
	margin-top: 90px;
}

.headerLine {
	margin-top: 10px;
}

body {
	margin-top: 450px;
}
.wrap {
	margin: 0;
	margin-left: auto;
	margin-right: auto;
	background: rgb(248, 249, 250);
	box-shadow: rgba(0, 0, 0, 0.06) 0px 0px 4px 0px;
	min-width: 650px;
}

#frmLogin {
	align-items: center size: 100%;
}

#idInput {
	color: black;
	border-width: 1px thin;
	background-color: white;
	border-color: #D8D8D8;
	width: 400px;
	height: 40px;
	font-size: 15px;
}

#pwdInput {
	color: black;
	border-width: 1px thin;
	background-color: white;
	border-color: #D8D8D8;
	width: 400px;
	height: 40px;
	font-size: 15px;
}

#loginBtn {
	color: black;
	border-width: 1px thin;
	background-color: white;
	border-color: gray;
	width: 405px;
	height: 40px;
	text-align: center;
	font-size: 15px;
	margin-top: 10px;
}

#btnSign {
	color: black;
	border-width: 1px thin;
	background-color: white;
	border-color: gray;
	width: 405px;
	height: 40px;
	text-align: center;
	font-size: 15px;
	margin-top: 10px;
}

.outer {
	width: 60%;
	min-width: 650px;
	background: rgb(248, 249, 250);
	box-shadow: rgba(0, 0, 0, 0.06) 0px 0px 4px 0px;
	margin: auto;
	margin-top: 70px;
	margin-bottom: 70px;
}

#frmLogin {
	width: 400px;
	margin: auto;
	padding: 10px;
}

.input_area {
	border: solid 1px #dadada;
	background: white;
}

#login_error {
	color: red;
	text-align: center;
}

</style>	

</head>
<body>
	<%@ include file="../common/header.jsp"%>
<div class="outer" align="center">
	<form name="frmLogin" method="post" action="${contextPath}/login">
		<p style="font-size:40px; font-weight: bold; color: silver;">로그인</p>
		<span class="input_area"><input type="text" id="idInput" name="member_id" placeholder="  아이디" required></span><br>
		<span class="input_area"><input type="password" id="pwdInput" name="pwd" placeholder="  비밀번호" required></span><br>
		<c:if test="${not empty loginErrMsg }">
 			<p id="login_error">${loginErrMsg }</p>
 		</c:if>		
		<input type="button" onclick="fn_validate();" id="loginBtn" value="로그인"><br>
		<input type="button" onclick="location.href='${contextPath}/memberSign'" id="btnSign" value="회원가입"><br><br>
	</form>
</div>
</body>
</html>