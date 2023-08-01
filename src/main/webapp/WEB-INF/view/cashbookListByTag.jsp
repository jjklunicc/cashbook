<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>나만의 가계부</title>
	<link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css">
	<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png">
</head>
<body>
	<jsp:include page="../inc/header.jsp"></jsp:include>
	<div class="container">
		<h2>해시태그별 내역(#${word})</h2>
		<div class="cashbook-list">
			<div class="list-header">
				<div>수입/지출</div>
				<div>금액</div>
				<div>메모</div>
				<div>발생일자</div>
			</div>
			<c:forEach var="c" items="${list}">
				<div>
					<div>${c.category}</div>
					<div>
						<c:if test="${c.category == '수입'}">
							<span style="color:blue">+<fmt:formatNumber value="${c.price}" pattern="###,###" /></span>
						</c:if>
						<c:if test="${c.category == '지출'}">
							<span style="color:red">-<fmt:formatNumber value="${c.price}" pattern="###,###" /></span>
						</c:if>
					</div>
					<div>${c.memo}</div>
					<div>${c.cashbookDate}</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>