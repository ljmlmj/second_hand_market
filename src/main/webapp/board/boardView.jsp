<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 


<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>게시글 상세보기</title>
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
td {
   background: white; /* 배경색 */
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
/*여기는 댓글 css*/
#form-commentInfo{
    width: 100%;s
    padding-left: 0px;
}

#comment-count{
    margin-bottom: 10px; 
    align:center;
}

#comment-input{
    width: 50%;
    height: 3.3em;
}

#submit{
    background-color: rgb(0, 128,255);
    width: 5.5em;
    height: 3.3em;;
    font-size: 15px;
    font-weight: bold;
    color: aliceblue;
}

#voteUp, #voteDown{
    width: 3.5em;
    height: 1.9em;
    background-color: aqua;    
}

#comments{
    margin-top: 10px;
}

.eachComment{   
    width :50%; 
    margin: 10px;  
    padding: 0.5em; 
    border-bottom: 1px solid #c1bcba;
}

.eachComment .name{
    font-size: 1.5em;
    font-weight: bold;
    margin-bottom: 0.3em;
    display: flex;
    justify-content: space-between;
}

.eachComment .inputValue{
    font-size: 1.2em;
    font-style: italic;    
}

.eachComment .time{
    font-size: 0.7em;
    color: #c1bcba;
    font-style: oblique;
    margin-top: 0.5em;
    margin-bottom: 0.5em;
    
}

.eachComment .voteDiv{
    display: flex;
    justify-content: flex-end;
}

.eachComment .deleteComment{
    background-color: red;
    color: aliceblue;
}

   </style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

</script>
</head>

   


<body>
<%@ include file="../common/header.jsp"%>
   <div class="wrap" align="center">   
   <div id="wrap" align="center">
   <h1>게시물 상세보기</h1>
<form action="${contextPath}/insertWishlist" name="FrmWishlist" method="post">
   <input type="hidden" id="member_id" name="member_id" value="${member.member_id }">
   <input type="hidden" id="board_no" name="board_no" value="${board.board_no }">
   <table border="1" summary="상품 상세조회">
      <caption>게시물 정보 보기</caption>
      <colgroup>
         <col width="100" />
         <col width="500" />
      </colgroup>
      <tbody>
         <tr>
            <th width="70px">게시판 / 말머리</th>
            <td align="left"><c:out value=" [ ${board.category_id} / ${board.head_id} ]" /> </td>   
         </tr>
         <tr>
            <th width="70px">글번호</th>
            <td align="left"><c:out value="${board.board_no}" /> </td>   
         </tr>
         
         <tr>
            <th >제목</th>
            <td align="left"><c:out value="${board.title}" /> </td>   
         </tr>
         <tr>   
            <th width="">작성자</th>
            <td align="left"><c:out value="${board.writer}" /> </td>
         </tr>
         <tr>   
            <th width="">찜횟수</th>
            <td align="left"><c:out value="${board.wish_count}" /></td>
            
         </tr>
         <tr>   
            <th width="">사진</th>
            <td align="left"><img src="<c:out value="${contextPath}/upload/${image.image_id}" />">
            </td>   
         </tr>
         <tr>      
            <th width="">내용</th>
            <td align="left"><c:out value="${board.content}" /> </td>               
         </tr>   
      </tbody>
   </table> 
   <br> 

   <p class="btn_align">
      <c:if test="${member.member_id != board.writer and not empty member.member_id }">
            <input type="submit" value="찜하기">
      </c:if>
      <input type="button" value="목록" onclick="location.href='${contextPath}/boardAll'" />
      <c:if test="${board.writer == member.member_id}">
         <input type="button" value="수정하기" onclick="location.href='${contextPath}/boardModify?board_no=${board.board_no}'" />
         <input type="button" value="삭제하기" onclick="if(confirm('삭제하시겠습니까?')) location.href='${contextPath}/boardDelete?board_no=${board.board_no}'" >
      </c:if>
      
   </p>
</form>
   
   </div>   

  <!-- 로그인 했을 경우만 댓글 작성 가능  -->
   <c:if test="${not empty member.member_id }">
        <form id="writeCommentForm" method="post" action="${contextPath}/commentInsert?board_no=${board.board_no}">
      
      <input type="hidden" name= "board_no" id="board_no" value="${board.board_no}"> 
      <input type="hidden" name= "member_id" id="member_id"  value="${member.member_id }"> 
      
      
      <!-- ID -->
      <td>
         <div>
            ${sessionScope.sessionID}
         </div>
      </td>
      <!-- 본문  -->
      <td>
         <div >
            <textarea id="eachComment" name= "comment1" id="comment1"  placeholder="댓글을 입력해 주세요."></textarea>
         </div>
      </td>
      <!-- 댓글등록버튼  -->
      <button id="submit">등록</button>
      
      </form>
   </c:if>
      
     <!-- 댓글리스트???  -->
<table>
   <tr>
      <th width="">작성자</th>
      <th width="">댓글</th>
      <th width="">등록일</th>
      <th width="">삭제</th>
   </tr>
  <c:forEach var="commentList" items="${commentList}" varStatus="status">
        <tr>
           <td>
              <c:out value="${commentList.member_id}" />
              
           </td>
           <td>
              <c:out value="${commentList.comment1}" />
           </td>
           <td>
              <c:out value="${commentList.create_date}" />
           </td>
           
           
           <td align="center">	
           <c:if test="${commentList.member_id == member.member_id}">									
         		<input type="button" value="삭제" onclick="if(confirm('삭제하시겠습니까?')) location.href='${contextPath}/commentDelete?board_no=${board.board_no}&comment_id=${commentList.comment_id}'" />
            </c:if>
           </td>
          
        </tr>
  </c:forEach>
</table>
   
    <script src="index.js">
    
    </script>


</div>
</body>