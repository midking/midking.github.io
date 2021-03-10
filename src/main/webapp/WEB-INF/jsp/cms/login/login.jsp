<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script src="/_resource/js/vendor/jquery-3.5.1.min.js"></script>
	<script src="/_resource/js/commonDev.js"></script>
	<title>로그인</title>
</head>
<body>
	<p>환영합니다...</p>
	<form id="loginForm" name="loginForm" method="post">
		<label for="userId">아이디</label>
		<input type="text" name="userId" id="userId" />
		<label for="password">비밀번호</label>
		<input type="password" name="password" id="password"/>
		<label for="saveId">아이디 저장</label>
		<input type="checkbox" id="saveId" value="N" />
		<input type="button" value="로그인" onclick="login();"/>
	</form>
	
	<script>
		$(document).ready(function(){
			// 쿠키에 저장된 아이디 값이 있다면 불러옴
			if(getCookie("userId") && "${cookie.userId.value }"){
				$("#userId").val(unescape("${cookie.userId.value }"));
				$("#saveId").prop("checked", true);
			}
				
			// 사용자 아이디, 비밀번호 입력 후 엔터 누르면 로그인
			$($("#userId"), $("#password")).on("keydown", function(e){
				if (e.keyCode == 13) {
		            e.preventDefault();
		            login();
				}
			});
		});
	
		// 로그인 유효성 검사 - 로그인 이전에 실시
		function validateLogin(){
			if(!$.trim($("#userId").val())){
				alert("아이디를 입력해 주세요.");
				$("#userId").focus();
				return false;
			}
			if(!$.trim($("#password").val())){
				alert("비밀번호를 입력해 주세요.");
				$("#password").focus();
				return false;
			}
			
			return true;
		}
	
		// 로그인
		function login(){
			if(validateLogin()){
				// 아이디 저장 체크 - 쿠키에 아이디 저장
				// 아이디 저장 비체크 - 아이디 쿠키 삭제
				if($("#saveId").is(":checked")){
		    		if(!getCookie("userId")){
				    	setCookie("userId", $("#userId").val(), 30);		    				    			
		    		}
		    	}else{
		    		deleteCookie("userId");
		    	}
				
				$.ajax({
					url : "/login.do",
					type : "POST",
					dataType : "json",
					data : $("#loginForm").serialize(),
					success : function(result){
						if(result){
							alert(result + "님 로그인 성공");
						} else{
							alert("로그인 실패");
						}
					},
					error : function(e){
						alert("서버 오류가 발생했습니다.");
						console.error(e);
					}
				});
			}
		}
	</script>
</body>
</html>