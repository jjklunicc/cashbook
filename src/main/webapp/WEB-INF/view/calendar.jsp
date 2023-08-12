<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> <!-- jstl subsdiving 호출 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--  JSP컴파일시 자바코드로 변환되는 c:...(제어문 코드) 커스템태그 사용가능 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>나만의 가계부</title>
	<link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css">
	<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png">
	<script src="https://kit.fontawesome.com/b9a2874375.js" crossorigin="anonymous"></script>
	<script>
		function dateClick(targetDate){
			location.href = "/cash/calendarOne?targetYear=" + ${targetYear} + "&targetMonth=" + ${targetMonth} + "&targetDate=" + targetDate;
		}
	</script>
</head>
<body>
	<jsp:include page="../inc/header.jsp"></jsp:include>
	<div class="container calendar-wrapper">
		<div>
			<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth-1}"><i class="fa-solid fa-arrow-left fa-2xl"></i></a>
			<h1>${targetYear}년 ${targetMonth+1}월</h1>
			<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth+1}"><i class="fa-solid fa-arrow-right fa-2xl"></i></a>
		</div>
		<div class="calendar">
			<div>
				<c:forEach var="i" begin="0" end="${totalCell - 1}" step="1">
					<c:set var="d" value="${i-beginBlank+1}"></c:set>
					<c:set var="sunday" value=""></c:set>
					<c:set var="saturday" value=""></c:set>
					<c:if test="${i!=0 && i%7==0}">
						<c:set var="sunday" value="sunday"></c:set>
						</div><div>
					</c:if>
					
					<c:if test="${i%7==6}">
						<c:set var="saturday" value="saturday"></c:set>
					</c:if>
					
					<c:if test="${d < 1 || d > lastDate}">
						<div></div>
					</c:if>
					
					<c:if test="${!(d < 1 || d > lastDate)}">
						<c:set var="todayClass" value=""></c:set>
						<c:if test="${targetYear == todayYear && targetMonth == todayMonth && d == todayDate}">
							<c:set var="todayClass" value="today"></c:set>
						</c:if>
						<div onclick="dateClick(${d})" class="${todayClass}">
							<div>
								<b class="${sunday} ${saturday}">${d}</b>
							</div>
							<c:set var="totalGet" value="${0}"></c:set>
							<c:set var="totalSpend" value="${0}"></c:set>
							<c:forEach var="c" items="${list}">
								<c:if test="${d == fn:substring(c.cashbookDate, 8,10)}">
									<c:if test="${c.category == '수입'}">
										<c:set var="totalGet" value="${totalGet + c.price}"></c:set>
									</c:if>
									<c:if test="${c.category == '지출'}">
										<c:set var="totalSpend" value="${totalSpend - c.price}"></c:set>
									</c:if>
								</c:if>
							</c:forEach>
							<div class="cashbook-data">
								<c:if test="${totalGet != 0 }">
									<div class="getPrice">+<fmt:formatNumber value="${totalGet}" pattern="###,###" /></div>
								</c:if>
								<c:if test="${totalSpend != 0 }">
									<div class="spendPrice"><fmt:formatNumber value="${totalSpend}" pattern="###,###" /></div>
								</c:if>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div>
			<c:if test="${htList.size() > 0}">
				<h2>이달의 해시태그</h2>
			</c:if>
			<div class="hashtags">
				<c:forEach var="m" items="${htList}">
					<a href="${pageContext.request.contextPath}/cashbookListByTag?word=${m.word}&targetYear=${targetYear}&targetMonth=${targetMonth}">#${m.word} (${m.cnt})</a>
				</c:forEach>
			</div>
		</div>
	</div>
	

</body>
</html>