<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Bootstrap Demo</title>
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
		  rel="stylesheet"
		  integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<style>
		.nav-link:hover {
			text-decoration: underline;
		}
	</style>
</head>
<body>
		<%@ include file = "../header.jsp" %>
	  		<h3>Informazioni personali</h3>
			<form>
			    <label for="name">Nome</label>
			    <input type="text" id="name" name="name" required>

			    <label for="surname">Cognome</label>
			    <input type="text" id="surname" name="surname" required>

			    <label for="email">Email</label>
			    <input type="email" id="email" name="email" required>   
			</form>
			<h3>Cambio password</h3>
			<form>
			    <label for="password">Nuova password</label>
			    <input type="password" id="new-password" name="new-password" required>

			    <label for="password">Conferma nuova Password</label>
			    <input type="password" id="confirm-password" name="confirm-password" required>   
			</form>
			<input type="submit" value="Aggiorna">
			<%@ include file = "../footer.jsp" %>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
				crossorigin="anonymous"></script>
</body>

	</html>
