<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Debtors Prison</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<!-- 	<link rel="stylesheet" href="/css/my_stype.css"/> -->

<!-- For any Bootstrap that uses JS or jQuery-->
<!-- 	<script src="/webjars/jquery/jquery.min.js"></script> -->
<!-- 	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script> -->
<!-- YOUR own local Javascript -->
<!-- 	<script src="/js/my_script.js"></script> -->
</head>
<body>
	<div class="w-75 p-3 container">
		<h1>You're in the debtors prison</h1>
		<img alt="prison" src="/prison.jpg">
		
		<!--Total Gold Display-->
		<div class="d-flex justify-content-start gap-1">
			<h2>
				Your Gold:
				<c:out value="${gold}"></c:out>
			</h2>
		</div>
		<form class="w-50 mt-4 d-flex justify-content-start" method="POST" action="/reset">
			<input class="w-25" type="submit" name="submit" value="Reset Game">
		</form>
		
	</div>
</body>
</html>