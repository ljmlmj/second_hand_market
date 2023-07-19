<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글쓰기 화면</title>

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
	color: white;
	text-shadow: 1px 1px 3px black;
}

#username {
	position: absolute;
	top: 30px;
	right: 250px;
	font-size: 20px;
	font-family: 'Hanna', serif;
	color: olive;
}

.leftnav {
	position: absolute;
	top: 60px;
	right: 250px;
	font-size: 40px;
	font-family: 'Hanna', serif;
	cursor: pointer;
	text-decoration: none;
	color: olive;
}

.leftnav:hover {
	color: white;
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

/*아래 스타일은 게시물 스타일*/
#wrap {
	width: 971px; /* 1024*768로 해상도를 맞추어서 설계 */
	/* 가운데 정렬을 위한 바깥쪽 여백 설정 */
	margin: 0;
	margin-left: auto;
	margin-right: auto;
}

table {
	width: 70%;
	border-collapse: collapse;
	font-size: 12px; /* 글꼴 크기 */
	line-height: 24px; /* 줄 간격 */
}

table td {
	width: 90%;
	border: #d3d3d3 solid 1px; /* 경계선 색상 스타일 굵기 */
	padding: 5px; /* 안쪽 여백 */
}

th {
	width: 10%;
	border: #d3d3d3 solid 1px; /* 경계선 색상 스타일 굵기 */
	background: silver; /* 배경색 */
}

#write_error {
	color: red;
	text-align: center;
}
</style>
  

<script type="text/javascript" src='<c:url value="/ckeditor/ckeditor.js" />'></script>
<script type="text/javascript">
function goWrite(){
	if (alert("게시글을 등록하시겠습니까?")) {
		let category_id = $('#none_category').val();
		let head_id = $('#none_head').val();
		
		alert(category_id);
		
		if(category_id === 'none' || head_id === 'none'){
			alert("필수 정보를 입력하세요");
		} else{
			//alert("게시글 등록에 성공했습니다.");
			//frmWrite.submit();
		}
	}
	
	
}
</script>

</head>
<body>
<%@ include file="../common/header.jsp"%>
	
	<c:if test="${not empty sessionScope.member.member_id}">
		<div id="wrap" align="center">
			<table class="list">
				<tr>
					<td align="center" colspan="9">
						<form id="regform" action="${contextPath}/boardSale"
							method="get">
							<input type="hidden" id="pageNum" value="${pageNum }" />
						</form>
					</td>
			</table>
		</div>
	</c:if>

	<div id="wrap" align="center">
		<h1>게시판 등록</h1>
		<!-- 머리말 combobox 사용 -->
		<form id="frmWrite" name="frmWrite" method="post" enctype="multipart/form-data" action="${contextPath}/boardWrite">
			<label for="pet-select">게시판 (선택) : </label> <select name="category_id" required="required">
				<option id="none_category" value="none">=== 선택 ===</option>
				<option value="판매게시판">판매게시판</option>
				<option value="구매게시판">구매게시판</option>
			</select> <br>
			<label for="pet-select">거래 상태(선택) : </label> <select name="head_id" required="required">
				<option id="none_head" value="none">=== 선택 ===</option>
				<option value="구매중">구매중</option>
				<option value="구매완료">구매완료</option>
				<option value="판매중">판매중</option>
				<option value="판매완료">판매완료</option>
			</select> <br>
			<br>
			<table>
				<tr>
					<th>제 목</th>
					<td><input type="text" id="titlle" name="title" size="80" required /></td>
				</tr>
				<tr>
					<th align="center">작 성 자</th>
					<td><input type="text" id="writer" name="writer" size="80" value="${sessionScope.member.member_id }"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<th>사 진</th>
					<td><input type="file" id="image_id" name="image_id" required><br> (주의사항
						: 이미지를 변경하고자 할 때만 선택하시오.)</td>
				</tr>
				<tr>
					<th>내 용</th>
					<td><textarea cols="80" rows="10" id="content" name="content" >
					</textarea> 
					<script>CKEDITOR.replace('content');</script>
					</td>
				</tr>
			</table>
			<br> 
			<input type="submit" value="등록" onclick="goWrite()">
			<input type="reset" value="다시작성"> 
			<input type="button" value="목록" onclick="location.href='${contextPath}/home'">
		</form>
	</div>
</body>
</html>