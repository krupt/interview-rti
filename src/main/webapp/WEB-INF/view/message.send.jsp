<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Отправить письмо</title>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" />
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<%@ include file="head.jsp" %>
<div class="container">
	<form id="message_form" class="form-horizontal" role="form">
		<div class="form-group">
			<label for="recipient" class="col-sm-2 control-label">Получатель</label>
			<div class="col-sm-10">
				<select id="recipient" name="recipient" class="form-control">
					<c:forEach items="${recipients}" var="recipient">
						<c:if test="${not recipient.id.equals(currentUserId)}">
							<option value="${recipient.id}">${recipient.descr}&nbsp;&lt;${recipient.email}&gt;</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="topic" class="col-sm-2 control-label">Тема</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="topic" name="topic">
			</div>
		</div>
		<div class="form-group">
			<label for="message" class="col-sm-2 control-label">Сообщение</label>
			<div class="col-sm-10">
				<textarea class="form-control" id="message" name="message" required rows="10"></textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-lg btn-success">Отправить</button>
				<button type="button" id="form_open" class="btn btn-lg btn-primary">Открыть форму</button>
			</div>
		</div>
	</form>
</div>
<div class="modal fade" id="results_dialog" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body center">
				<div id="waiting_body center">
					<img src="<c:url value="/resources/img/process.gif" />" align="middle">
					<span style="margin-left: 10px;">Подождите...</span><br><br>
				</div>
			</div>
			<div class="modal-footer">
				<button id="close_results" type="button" class="btn btn-primary">Закрыть</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/message.js" />"></script>
</body>
</html>