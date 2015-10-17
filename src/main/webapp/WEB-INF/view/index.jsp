<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Главная страница</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="resources/css/style.css" />
<script type="text/javascript" src="resources/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="head.jsp" %>
<div class="container">
	<sec:authorize access="isAuthenticated()" var="authenticated" />
	<c:if test="${not authenticated}">
		<div class="alert alert-warning alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			<b>Вход не выполнен. </b> Некоторые возможности не доступны
		</div>
	</c:if>
	<ul class="list-group col-md-4">
		<li class="list-group-item">
			<a href="about">О Компании</a>
		</li>
		<c:if test="${authenticated}">
			<li class="list-group-item">
				<a href="message/send">Отправить письмо</a>
			</li>
		</c:if>
		<sec:authorize access="hasRole('ADMIN')">
			<li class="list-group-item">
				<a href="message/list">Список всех писем</a>
			</li>
		</sec:authorize>
	</ul>
</div>
</body>
</html>