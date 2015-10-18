<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Доступ запрещен</title>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" />
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<%@ include file="head.jsp" %>
<div class="container">
	<div class="alert alert-danger">
		Недостаточно прав
	</div>
</div>
</body>
</html>