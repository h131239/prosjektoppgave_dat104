<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Logg inn - Demo</title>
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
<style>
input[type="submit"] {
	color: white;
	background-color: lightblue;
	width: 200px; /* width of image */
	height: 50px; /* height of image */
	border: 0;
}
</style>
</head>
<!-- <body style="background-color:#eaf6ff;"> -->
<body>
	<!-- <div class="ui middle aligned center aligned grid">  -->
	<div class="ui three column centered stackable grid">
		<div class="row"></div>
		<div class="column center aligned">
			<h2 class="ui huge header">Logg inn</h2>
			<form method="post" action="Login" class="ui large form">
				<div class="ui raised segment">
					<div class="field">
						<div class="ui left icon input">
							<i class="user icon"></i> <input type="text" name="brukernavn"
								placeholder="Brukernavn">
						</div>
					</div>
					<div class="field">
						<div class="ui left icon input">
							<i class="lock icon"></i> <input type="password" name="passord"
								placeholder="Passord">
						</div>
					</div>
					<button class="ui fluid large blue left icon submit button"
						type="submit">Logg inn
						<i class="sign in icon"></i>
						</button>
				</div>
				<div class="ui message" style="color: red;">${loginmessage}</div>
			</form>
			<div class="ui message">
				Ny bruker? <a href="#" id="register-now">Registrer deg her</a>
				<!-- Åpne modal -->
			</div>
			<div class="ui modal">
				<i class="close icon"></i>
				<div class="header">Registrer ny bruker</div>
				<div class="content">
					<form action="post" class="ui form">
						<div class="field">
							<label>Brukernavn</label> <input type="text"
								placeholder="Brukernavn" name="brukernavn">
						</div>
						<div class="field">
							<label>Passord</label> <input type="password"
								placeholder="Passord" name="passord">
						</div>
						<div class="field">
							<label>Gjenta passord</label> <input type="password"
								placeholder="Passord" name="passord">
						</div>
					</form>
				</div>
				<div class="actions">
					<div class="ui deny button">Avbryt</div>
					<div class="ui primary labeled icon button">
						<i class="add user icon"></i> Registrer
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript">
      $('#register-now')
        .click(() => {
          $('.ui.modal')
            .modal('show')
          ;
        })
      ;
    </script>
</body>
</html>
