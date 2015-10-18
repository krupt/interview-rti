<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<ul class="nav navbar-nav navbar-left">
			<li>
				<a class="navbar-brand" href="<c:url value="/" />">
					<span class="glyphicon glyphicon-home"></span>&nbsp;RTI-Project
				</a>
			</li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<sec:authorize access="isAuthenticated()" var="authenticated">
				<sec:authentication property="principal.id" var="currentUserId" />
				<li>
					<a href="<c:url value="/user/info" />"><span class="glyphicon glyphicon-user"></span>&nbsp;<sec:authentication property="principal.description" /></a>
				</li>
			</sec:authorize>
			<c:if test="${not authenticated}">
				<li>
					<a href="<c:url value="/login" />" title="Вход"><span class="glyphicon glyphicon-log-in">&nbsp;</span>ВХОД</a>
				</li>
			</c:if>
			<c:if test="${authenticated}">
				<li>
					<form action="<c:url value="/logout" />" method="post">
						<button type="submit" class="logout-btn text-danger">ВЫХОД&nbsp;<span class="glyphicon glyphicon-log-out"></span></button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					</form>
				</li>
			</c:if>
		</ul>
	</div>
</nav>