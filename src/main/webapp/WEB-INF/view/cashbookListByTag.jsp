<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>해시태그별 내역(#${word})</h2>
	<table>
		<tr>
			<td>수입/지출</td>
			<td>금액</td>
			<td>메모</td>
			<td>발생일시</td>
		</tr>
		<c:forEach var="c" items="${list}">
			<tr>
				<td>${c.category}</td>
				<td>
					<c:if test="${c.category == '수입'}">
						<span style="color:blue">+<fmt:formatNumber value="${c.price}" pattern="###,###" /></span>
					</c:if>
					<c:if test="${c.category == '지출'}">
						<span style="color:red">-<fmt:formatNumber value="${c.price}" pattern="###,###" /></span>
					</c:if>
				</td>
				<td>${c.memo}</td>
				<td>${c.cashbookDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>