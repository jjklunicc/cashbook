<%@page import="cash.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberOne</title>
</head>
	<!-- css파일 -->
	<link href="<%=request.getContextPath() %>/style.css" type="text/css" rel="stylesheet">
	
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/litera/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<body>
<div class="container">
	<h1 class="title center">회원 정보</h1>
	<div class="profile">
		<img src="${pageContext.request.contextPath}/img/profile.png">
		<div class="title">${id}</div>
	<div class="password">
		<img src="${pageContext.request.contextPath}/img/password.png">
		<a href="${pageContext.request.contextPath}/modifyPassword">비밀번호 수정</a>
	</div>
	<a href="${pageContext.request.contextPath}/removeMember">회원탈퇴</a>
	</div>
</div>
</body>
</html>