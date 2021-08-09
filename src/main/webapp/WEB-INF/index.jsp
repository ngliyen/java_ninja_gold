<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<!-- for Bootstrap CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<title>Ninja Gold Game</title>
</head>
<body>
	<div class="w-75 p-3 container">
		<!--Total Gold Display-->
		<div class="d-flex justify-content-start gap-1">
			<h2>Your Gold:<c:out value="${gold}"></c:out></h2>
		</div>
		<!--Find Gold Section-->
		<div class="d-flex gap-5 justify-content-between pt-2 pb-2">
			<!-- Farm -->
			<div class="col d-flex flex-column justify-content-between align-items-center border border-dark border-2 p-3">
				<h2>Farm</h2>
				<p>(earns 10-20 gold)</p>
				<form method="POST" action="/findgold">
					<input type="hidden" name="places" value="farm">
					<input type="submit" name="submit" value="Find Gold!">
				</form>
			</div>
			<!-- Cave -->
			<div class="col d-flex flex-column justify-content-between align-items-center border border-dark border-2 p-3">
				<h2>Cave</h2>
				<p>(earns 5-10 gold)</p>
				<form method="POST" action="/findgold">
					<input type="hidden" name="places" value="cave">
					<input type="submit" name="submit" value="Find Gold!">
				</form>
			</div>
			<!-- House -->
			<div class="col d-flex flex-column justify-content-between align-items-center border border-dark border-2 p-3">
				<h2>House</h2>
				<p>(earns 2-5 gold)</p>
				<form method="POST" action="/findgold">
					<input type="hidden" name="places" value="house">
					<input type="submit" name="submit" value="Find Gold!">
				</form>
			</div>
			<!-- Casino -->
			<div class="col d-flex flex-column justify-content-between align-items-center border border-dark border-2 p-3">
				<h2>Casino!</h2>
				<p>(earns/takes 0-50 gold)</p>
				<form method="POST" action="/findgold">
					<input type="hidden" name="places" value="casino">
					<input type="submit" name="submit" value="Find Gold!">
				</form>
			</div>
		</div>
		<!-- Activities -->
		<h2>Activities</h2>
		<div style="height:150px;" class="overflow-auto w-100 d-flex flex-column justify-content-start align-items-start border border-dark border-2">
			<c:forEach var="log" items="${logs}">
<%-- 				<c:out value="${log}"></c:out><br> --%>
				<c:if test="${fn:contains(log, 'earned')}">
					<p class="mb-0 text-success"><c:out value="${log}"></c:out></p>
				</c:if>
				<c:if test="${fn:contains(log, 'lost')}">
					<p class="mb-0 text-danger"><c:out value="${log}"></c:out></p>
				</c:if>
				<c:if test="${fn:contains(log, 'not win/lose')}">
					<p class="mb-0 text-warning"><c:out value="${log}"></c:out></p>
				</c:if>
			</c:forEach>
		</div>
		<form class="w-50 mt-4 d-flex justify-content-start" method="POST" action="/reset">
			<input class="w-25" type="submit" name="submit" value="Reset Game">
		</form>
		
	</div>
	
</body>
</html>