<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>회원가입</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		
	function fn_joinCheck(){
		var joinForm = document.joinForm;
		var pwd = joinForm.pwd.value;
		var pwd2 = joinForm.pwd2.value;
		var tel = joinForm.tel.value;
		var reg = /^[0-9]+/g;
		
		if (pwd != pwd2){
			alert("비밀번호가 일치하지 않습니다.");
			joinForm.pwd.focus();
		    return false;
		}
		if (!reg.test(tel)) {
		    alert("연락처는 숫자만 입력할 수 있습니다.");
		    joinForm.tel.focus();
		    return false;
		} else {
			alert("회원가입에 성공했습니다");
			joinForm.submit();
		}
		
	}
	
	$(document).ready(function(){
			$("#idCheck").on('click', function(e){  
	            e.preventDefault();
				var member_id = $('#member_id').val()
	            if(member_id == 0 || member_id == ""){
	               alert("아이디를 입력하세요.");
	               $('#member_id').focus();
	               return;
	            }
	            $.ajax({
	            	url : '${contextPath}/idCheck',
	                type : 'get',
	                data : {member_id: member_id},        
	                dataType : 'text', 
	                success : function(result) {
	                console.log(result);   
	                    if(result === 'true'){
	                        $('#idResult').addClass('errorMsg').text('아이디가 이미 존재합니다.');
	                        return;
	                    } else {
	                        $('#idResult').addClass('okMsg').text('사용 가능한 아이디입니다.');
	                        return;
	                    }
	                },
	                error : function(request, status, error) {
	                    console.log(error)
	                }
	            }) 
	        });
			
			$('#pwd').keyup(function(){
				   $('#pwdResult').text('');
				  });

			$('#pwd2').keyup(function(){
				if($('#pwd').val()!=$('#pwd2').val()){
				    $('#pwdResult').text('');
				    $('#pwdResult').html("비밀번호가 일치하지 않습니다.");
				}else{
					$('#pwdResult').text('');
				    $('#pwdResult').html("비밀번호가 일치합니다.");
				}
			}); 
			
		});
	</script>
	<style type="text/css">
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
	margin-top: 300px;
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
.outer {
		width: 60%;
		min-width: 650px;
		background: rgb(248, 249, 250);
		box-shadow: rgba(0, 0, 0, 0.06) 0px 0px 4px 0px;
		margin: auto;
		margin-top: 70px;
		margin-bottom: 70px;
		}
#joinForm {
		width: 400px;
		margin: auto;
		padding: 10px;
		}
#joinForm h1 {
		text-align:center;
		}
.input_area {
		border: solid 1px #dadada;
		padding: 10px 10px 14px 10px;
		background: white;
		}
.input_area input:not([type=checkbox]) {
		width: 250px;
		height: 30px;
		border: 0px;
		}
button {
		width: 100px;
		height: 35px;
		border: 0px;
		color: white;
		background: #282A35;
		margin: auto;
		}
#success {
		width: 250px;
		height: 50px;
		border: 0px;
		color: white;
		background: #282A35;
		margin: auto;
		}
	</style>
</head>
<body>
<%@ include file="../common/header.jsp"%>
	<div class="outer">
		<div id="joinInfoArea">
			<form name="joinForm" id="joinForm" action="${contextPath}/memberSign" method="post">
				<h1>회원 가입</h1>
				
				<h4>* 아이디</h4> 
				<span class="input_area"><input type="text" maxlength="13" name="member_id" id="member_id" required></span>
				<button id="idCheck" type="button">중복확인</button> <p id="idResult"></p>
				
				<h4>* 비밀번호</h4>
				<span class="input_area"><input type="password" maxlength="15" name="pwd" id="pwd" required></span>
				
				<h4>* 비밀번호 확인</h4>
				<span class="input_area"><input type="password" maxlength="15" name="pwd2" id="pwd2" required></span>
				<label id="pwdResult"></label>
				
				<h4>* 이름</h4>
				<span class="input_area"><input type="text" maxlength="5" name="name" id="name" required></span>
				
				<h4>* 성별</h4>
				<input type="radio" maxlength="5" name="gender" id="gender" value="남">남
				<input type="radio" maxlength="5" name="gender" id="gender" value="여">여
				
				<h4>연락처</h4>
				<span class="input_area"><input type="tel" maxlength="11" name="phone" id="tel" placeholder="(-없이)01012345678"></span>
				
				<h4>이메일</h4>
				<span class="input_area">
					<input type="text" name="email">
				</span>
				
				<h4>주소</h4>
				<span class="input_area"><input type="text" maxlength="11" name="address" placeholder="지역만"></span>
				<br><br>
				
				<input type="button" id="success" onclick="fn_joinCheck()" value="가입하기">
			</form>
				
		</div>
	</div>
</body>
</html>