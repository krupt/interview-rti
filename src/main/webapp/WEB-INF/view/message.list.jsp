<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Просмотр всех сообщений</title>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" />
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<%@ include file="head.jsp" %>
<div class="container">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Id</th>
				<th>Отправитель</th>
				<th>Получатель</th>
				<th>Тема</th>
				<th>Сообщение</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${messages}" var="message">
				<tr>
					<td>${message.id}</td>
					<td>${message.sender.descr}&nbsp;&lt;${message.sender.email}&gt;</td>
					<td>${message.recipient.descr}&nbsp;&lt;${message.recipient.email}&gt;</td>
					<td>${message.topic}</td>
					<td>${message.message}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>