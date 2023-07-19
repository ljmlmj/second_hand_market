<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
	<div class="headCategory">
		<c:if test="${not empty sessionScope.member.member_id}" >
			<strong id="username">${sessionScope.member.name} 님</strong>
			<div class="leftnav" onclick= "location.href='${contextPath}/logout'">로그아웃</div>
		</c:if>
		<c:if test="${empty sessionScope.member.member_id}" >
			<div class="leftnav" onclick= "location.href='${contextPath}/login'">로그인</div>
		</c:if>
   		<a href="${contextPath}/home"><img src="${contextPath}/images/logo.png" id="logo" width="220" height="70"></a><br><br>
   		<form id="searchFrm" action="${contextPath}/boardAll" method="get">
   			<input type="hidden" id="pageNum" value="${pageNum }"/>
   			<div class="search">
      			<input type="text" class="searchText" name="searchText" placeholder="Search">
      			
      			<button class="searchBtn">검색</button>
   			</div>
   		</form>
    	<a href="${contextPath}/home" class="link">홈</a>
    	<a href="${contextPath}/boardSale" class="link">판매게시판</a>
    	<a href="${contextPath}/boardPurchase" class="link">구매게시판</a>
    	<a href="${contextPath}/wishlist" class="link">위시리스트</a>
	</div>
   <hr class="headerLine" />
</header>