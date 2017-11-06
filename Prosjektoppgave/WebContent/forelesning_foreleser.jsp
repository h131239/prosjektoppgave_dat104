<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tilbakemelding for ${forelesning.fagkode} </title>
</head>
<body>
	<h2>Tilbakemeldinger for ${forelesning.fagkode} ${forelesning.dato}</h2>

	<table border="1">
		<tr bgcolor="#cccccc">
			<th align="left">Col1</th>
			<th>Col2</th>
			<th>Col3</th>
		</tr>

		<c:forEach items='${vurderinger}' var="vurdering">
			<tr bgcolor="${vurdering.farge }">
				<td>${vurdering.id}</td>
				<td>${vurdering.kommentar}</td>
				<td>${vurdering.sluttvurdering}</td>
			</tr>
		</c:forEach>

	</table>
	<form action=Login method="get">
		<input type="hidden" name="logout" value=true> <input
			type="submit" value="Logg ut" />
	</form>
</body>
</html>