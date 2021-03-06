<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Просмотр всех сообщений</title>
<%@ include file="link.jsp" %>
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
				<th>Дата создания</th>
				<th>Статус</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${messages}" var="message">
				<tr>
					<td>${message.id}</td>
					<td>${message.sender.description}&nbsp;&lt;${message.sender.email}&gt;</td>
					<td>${message.recipient.description}&nbsp;&lt;${message.recipient.email}&gt;</td>
					<td>${message.topic}</td>
					<td>${message.message}</td>
					<td>${message.created}</td>
					<td>${message.status}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>