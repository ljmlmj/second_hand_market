<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WishList 화면</title>

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

	
</style>
  

<!-- JQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		function btnDelete(board_no) {
			if (confirm('삭제하시겠습니까?')) {
				location.href="${contextPath}/deleteWishlist?board_no=" + board_no;
			} else{
				return false;
			}
		}
	</script>
	

</head>
<body>
<%@ include file="../common/header.jsp"%>
	<h1 align="center" style="color: silver;">${member.name}님의 위시리스트</h1>
	<h3 align="center" style="color: silver;">찜 개수 : ${wishlists.size()}</h3>
	<input type="hidden" name="member_id" value="${member.member_id}">
	<div class="wrap" align="center">
		<table id="datatable"
			class="table table-striped table-lought dt-responsive nowrap"
			style="width: 100%">
			<thead>
				<tr>
					<th width="70px" scope="col">카테고리</th>
					<th width="70px">게시글번호</th>
					<th width="500px" scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">찜횟수</th>
					<th scope="col">찜등록일</th>
					<th scope="col">삭제</th>
				</tr>
			</thead>

			<tbody>
				<c:choose>
					<c:when test="${wishlists.size() <= 0}">
						<tr>
							<td colspan="9" align="center" height="23">장바구니가 비었습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${wishlists}" var="wishlist" varStatus="status">
							<tr class="record">
								<td align="center">
									<!-- 카테고리 --> <c:out value="${wishlist.category_id}"></c:out>
								</td>
								<td align="center" id="board_no">
									<!-- 게시글번호 --> 
									<c:out value="${wishlist.board_no}"></c:out>
									<input type="hidden" name="board_no" class="board_no" id="board_no" value="${wishlist.board_no}" >
								</td>
								<td align="center">
									<!-- 제목 --> 
									<a href="${contextPath}/boardView?board_no=${wishlist.board_no }" style="cursor: pointer; text-decoration: none; font-weight: bold;">${wishlist.title}</a>
								</td>
								<td align="center">
									<!-- 작성자 -->
									<c:out value="${wishlist.writer}"></c:out>
								</td>
								<td align="center">
									<!-- 찜횟수 --> 
									<c:out value="${wishlist.wish_count}"></c:out>
								</td>
								<td align="center">
									<!-- 찜등록일 --> 
									<c:out value="${wishlist.wishdate}"></c:out>
								</td>
								<td align="center">
									<!-- 삭제버튼 --> 
									<a href="#" onclick="btnDelete(${wishlist.board_no})">삭제</a>
								</td>
								</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<br>
	<div id="goShopping" style="text-align: center;">
			<a href="${contextPath}/home" style="color: silver; font-size: 20px; font-weight: bold;">더 둘러보기</a>
	</div>
</body>
</html>