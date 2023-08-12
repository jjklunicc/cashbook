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
		<h1>${targetYear}년 ${targetMonth+1}월 ${targetDate}일</h1>
		<form method="post" action="${pageContext.request.contextPath}/addCashbook" class="calendar-form button-deco">
			<input type="hidden" name="targetYear" value="${targetYear }">
			<input type="hidden" name="targetMonth" value="${targetMonth }">
			<input type="hidden" name="targetDate" value="${targetDate }">
			<select name="category">
				<option>수입</option>
				<option>지출</option>
			</select>
			<input type="text" name="memo" placeholder="메모, 해시태그등" required>
			<input type="number" name="price" placeholder="금액" required>
			
			<button type="submit">추가</button>
		</form>
		<div class="cashbook-list">
			<div class="list-header">
				<div>수입/지출</div>
				<div>금액</div>
				<div>메모</div>
				<div>발생일자</div>
				<div>&nbsp;</div>
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
					<div class="button-deco">
						<form method="post" action="/cash/removeCashbook"> 
							<input type="hidden" name="targetYear" value="${targetYear }">
							<input type="hidden" name="targetMonth" value="${targetMonth }">
							<input type="hidden" name="targetDate" value="${targetDate }">
							<input type="hidden" name="cashbookNo" value="${c.cashbookNo }">
							<button type="submit">삭제</button>
						</form>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="button-deco before-btn">
			<a href="/cash/calendar?targetYear=${targetYear }&targetMonth=${targetMonth}">이전</a>
		</div>
	</div>
</body>
</html>