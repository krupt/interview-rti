<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav id="authPanel" class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<ul class="nav navbar-nav navbar-left">
			<li>
				<a class="navbar-brand" href="<c:url value="/" />">
					<i class="fa fa-home fa-2x"></i>RTI-Project
				</a>
			</li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<sec:authorize access="isAuthenticated()" var="authenticated">
				<li class="user-nav">
					<a href="<c:url value="/user/info" />"><i class="fa fa-user fa-2x"></i><sec:authentication property="principal.description" /></a>
				</li>
			</sec:authorize>
			<c:if test="${not authenticated}">
				<li class="login-nav">
					<a href="<c:url value="/login" />" title="Вход"><i class="fa fa-sign-in fa-2x"></i>ВХОД</a>
				</li>
			</c:if>
			<c:if test="${authenticated}">
				<li>
					<form action="<c:url value="/logout" />" method="post">
						<button class="logout-btn text-danger"><i class="fa fa-sign-out fa-2x"></i>ВЫХОД</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					</form>
				</li>
			</c:if>
		</ul>
	</div>
</nav>