<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Foreleser fagliste - Demo</title>
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
	<div class="ui two column centered stackable grid">
		<!-- <div class="row"></div> -->
		<!-- <div class="ui center aligned container"> -->
		<div class="column">
			<div class="ui container">
				<form action=Login method="GET">
					<input type="hidden" name="logout" value=true> <input
						type="submit" value="Logg ut"
						class="small negative ui labeled icon button"
						style="float: right;" />
				</form>
				<h1 class="ui huge header">Fagliste</h1>
				<div class="ui segment raised">
					<div class="ui list">

						<c:forEach items='${fagliste}' var="fag">
							<div class="item">
								<form action=FagoversiktForeleser method="POST">
									<input type="hidden" name="fagkode" value="${fag.fagkode}">
									<input type="submit" value="${fag.fagkode} ${fag.beskrivelse}"
										class="ui fluid right labeled icon button" />
								</form>
							</div>
						</c:forEach>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
