<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>나만의 가계부</title>
	<link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css">
	<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png">
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/login">
		<div>
			<h1>로그인</h1>
			<div>
				아이디 <input type="text" name="memberId" required>
			</div>
			<div>
				비밀번호 <input type="password" name="memberPw" required>
			</div>
			<div>
				<button type="submit">로그인</button>
				<a href="${pageContext.request.contextPath}/addMember">회원가입</a>
			</div>
		</div>
	</form>
</body>
</html>