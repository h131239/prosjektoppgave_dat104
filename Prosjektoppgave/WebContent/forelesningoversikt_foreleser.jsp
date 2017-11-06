<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Forelesningsliste - Demo</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous">
	
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js">
	
</script>
</head>
<body>
	<div class="ui container">
		<form action=Login method="GET">
			<input type="hidden" name="logout" value=true> <i class="sign out icon"></i><input
				type="submit" value="Logg ut"
				class="small negative ui labeled icon button" style="float: right;">
		</form>
		<div class="ui huge header">Forelesninger - ${fag.fagkode}</div>
		<div class="ui raised segment">

			<c:forEach items='${forelesninger}' var="forelesning">

				<div class="three column ui padded stackable grid">
					<div class="eight wide column">
						<div class="ui header">${forelesning.tidsbeskrivelse}</div>
					</div>
					<div class="tree wide right floated column">
						<div class="ui tiny horizontal statistics">
							<div class="statistic">
								<div class="value">${forelesning.rating}%</div>
								<div class="label">Samlet score</div>
							</div>
						</div>
					</div>

					<div class="three wide right floated column">
						<form action=ForelesoversiktForeleser method=post>
							<input type="hidden" name="forelesningid"
								value="${forelesning.id}"> <input type="submit"
								value="Detaljer" class="ui blue button">
						</form>
					</div>
				</div>
				<div class="ui divider"></div>
			</c:forEach>


		</div>
	</div>
</body>
</html>
