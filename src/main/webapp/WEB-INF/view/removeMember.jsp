<%@page import="cash.vo.Member"%>
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
	<form method="post" action="${pageContext.request.contextPath}/removeMember" class="login-form button-deco">
	   <div>
	   	  <h1>회원탈퇴 </h1>
	      <div>
	         아이디 <input type="text" name="memberId" id="memberId" value="${member.memberId}" readonly="readonly">
	      </div>
	      <div>
	         비밀번호확인 <input type="password" name="memberPw">
	      </div>
	      <div>
	      	<a href="/cash/calendar">이전</a>
	      	<button type="submit">탈퇴</button>
	      </div>
	   </div>
	</form>
</body>
</html>