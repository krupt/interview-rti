<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<ul class="nav navbar-nav navbar-right">
			<sec:authorize access="isAuthenticated()" var="isAuthenticated">
				<li>
					<a href=""><span class="glyphicon glyphicon-user"></span>&nbsp;<sec:authentication property="principal"/></a>
				</li>
			</sec:authorize>
			<c:if test="${not isAuthenticated}">
				<li>
					<a href="login" title="Вход"><span class="glyphicon glyphicon-log-in">&nbsp;</span>ВХОД</a>
				</li>
			</c:if>
			<c:if test="${isAuthenticated}">
				<li>
					<a href="logout" title="Выход">ВЫХОД&nbsp;<span class="glyphicon glyphicon-log-out"></span></a>
				</li>
			</c:if>
		</ul>
	</div>
</nav>