<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 


<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>판매 게시판</title>
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

#username{
position: absolute;
top: 30px;
right: 250px;
font-size: 20px;
font-family: 'Hanna', serif;
color: olive; 
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

#wrapHeader{
	
}

.wrapBody {
	width: 971px; /* 1024*768로 해상도를 맞추어서 설계 */
	/* 가운데 정렬을 위한 바깥쪽 여백 설정 */
	margin: 0;
	margin-left: auto;
	margin-right: auto;
}

h1 {
	color: silver; /* 글 색상 */
}

/*아래 스타일은 게시물 스타일*/
.wrap {
	width: 971px; /* 1024*768로 해상도를 맞추어서 설계 */
	/* 가운데 정렬을 위한 바깥쪽 여백 설정 */
	margin: 0;
	margin-left: auto;
	margin-right: auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	font-size: 12px; /* 글꼴 크기 */
	line-height: 24px; /* 줄 간격 */
}

table td, th {
	border: #d3d3d3 solid 1px; /* 경계선 색상 스타일 굵기 */
	padding: 5px; /* 안쪽 여백 */
}

th {
	background: silver; /* 배경색 */
}

img {
	width: 220px; /* 이미지 너비(가로) */
	height: 300px; /* 이미지 높이(세로) */
}

a {
	text-decoration: none; /* 링크 밑줄 없애기 */
	color: black; /* 글 색상 */
}

a:HOVER {
	text-decoration: underline; /* 밑줄 */
	color: green; /* 글 색상 */
}
</style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<%@ include file="../common/header.jsp"%>
		<div class="wrapBody" align="center">
			<table class="list">
				<tr>
					<td align="center" colspan="9">
						<form id="regform" action="${contextPath}/boardSale" method="get">
							<input type="hidden" id="pageNum" value="${pageNum }"/>
							<p style="font-size: 50px; font-weight: bold;">
								판매 게시판
							</p>
						</form>				
					</td>
				</tr>
				<tr>
					<th width="50px">번호</th>
					<th width="70px">머리말</th>
					<th width="500px">제목</th>
					<th width="">작성자</th>
					<th width="">조회수</th>
					<th width="">찜횟수</th>
					<th width="">등록일</th>
				</tr>
	
				<c:set var="num" value="${totalCount - ((pageNum - 1) * listCount) }" ></c:set>
				<c:forEach var="board" items="${boardList}">
					<tr class="record">
						<td align="center"><c:out value="${num}" /></td>
						<td align="center" style="color: blue;"><c:out value="[${board.head_id}]" /></td>
						<td align="center"><a href="${contextPath}/boardView?board_no=${board.board_no }" style="font-weight: bold">${board.title}</a> </td>
						<td align="center"><c:out value="${board.writer}" /> </td>					
						<td align="center"><c:out value="${board.hit}" /> </td>					
						<td align="center"><c:out value="${board.wish_count}" /> </td>					
						<td align="center"><c:out value="${board.regdate}" /> </td>					
					</tr>
					<c:set var="num" value="${num-1 }"></c:set>
				</c:forEach>
				<tr>
					<td align="center" colspan="9">${page_navigator }</td>
				</tr>
			</table>
			<br>
			<c:if test="${not empty sessionScope.member.member_id}">
				<input type="button" style="width: 90px; height: 30px; background: white; cursor: pointer;" id="write" name="write" onclick="location.href='${contextPath}/boardWrite'" value="글쓰기">
			</c:if>
		</div>
	
</html>
