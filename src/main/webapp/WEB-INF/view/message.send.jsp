<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>Отправить сообщение</title>
<%@ include file="link.jsp" %>
</head>
<body>
<%@ include file="head.jsp" %>
<div class="container">
	<form id="message_form" class="form-horizontal" role="form">
		<div class="form-group">
			<label for="recipient" class="col-sm-2 control-label">Получатель</label>
			<div class="col-sm-10">
				<select id="recipient" name="recipient" class="form-control" required>
					<c:forEach items="${recipients}" var="recipient">
						<option value="${recipient.id}">${recipient.description}&nbsp;&lt;${recipient.email}&gt;</option>
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
			</div>
		</div>
	</form>
</div>
<div class="modal fade" id="results_dialog" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body center">
				<div id="waiting_body">
					<img src="<c:url value="/resources/img/process.gif" />" align="middle">
					<span style="margin-left: 10px;">Подождите...</span><br><br>
				</div>
				<div id="results_body" class="center">
				</div>
			</div>
			<div class="modal-footer">
				<button id="close_results" type="button" class="btn btn-primary">Закрыть</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/message/send.js" />"></script>
</body>
</html>