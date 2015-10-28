<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Вход</title>
<%@ include file="link.jsp" %>
</head>
<body>
<div class="container">
	<c:if test="${not empty error}"> 
		<div class="alert alert-danger">
			${error}
		</div>
	</c:if>
	<form class="form-horizontal col-sm-offset-3" method="post" role="form">
		<div class="form-group">
			<label for="username" class="col-sm-2 control-label">E-mail</label>
			<div class="col-sm-6 input-img">
				<input type="email" class="form-control" id="username" name="username" required autofocus>
				<i class="fa fa-user-secret text-primary"></i>
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Пароль</label>
			<div class="col-sm-6 input-img">
				<input type="password" class="form-control" id="password" name="password" required>
				<i class="fa fa-unlock-alt text-warning"></i>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label>
						<input type="checkbox" name="remember-me">Запомнить меня
					</label>
				</div>
				<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-lg btn-success"><i class="fa fa-sign-in"></i>&nbsp;Войти</button>
			</div>
		</div>
	</form>
</div>
</body>
</html>