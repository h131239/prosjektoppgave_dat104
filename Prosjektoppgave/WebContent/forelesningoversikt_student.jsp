<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forelesningoversikt for "${bruker.fornavn}"</title>
</head>
<body>
	<h2>Forelesningoversikt for "${bruker.fornavn}"</h2>

	<table border="1">
		<tr bgcolor="#cccccc">
			<th align="left">Col1</th>
			<th>Col2</th>
			<th>Col3</th>
		</tr>
		<c:forEach items='${forelesninger}' var="forelesning">
			<c:choose>
				<c:when test="${forelesning.aktiv}">
					<tr bgcolor="#2C8632">
						<td>${forelesning.fagkode}</td>
						<td>${forelesning.tidsbeskrivelse}</td>
						<td>
							<form action=FagoversiktForeleser method="POST">
								<input type="hidden" name="fagkode" value="${fag.fagkode}">
								<input type="submit" value="Velg" />
							</form>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td>${forelesning.fagkode}</td>
						<td>${forelesning.tidsbeskrivelse}</td>
						<td>
							<form action=FagoversiktForeleser method="POST">
								<input type="hidden" name="fagkode" value="${fag.fagkode}">
								<input type="submit" value="Velg" />
							</form>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</table>
	<form action=Login method="get">
		<input type="hidden" name="logout" value=true> <input
			type="submit" value="Logg ut" />
	</form>
</body>
</html>