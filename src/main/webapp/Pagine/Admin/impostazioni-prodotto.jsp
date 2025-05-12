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
	  		<h2>Impostazioni prodotto</h2>
			<form method="GET" action="dati_vinili_servlet">

				<img src="path_to_album_cover.jpg" alt="Copertina album">
				<a href="#">Modifica copertina</a>

			    <label for="title">Titolo</label>
			    <input type="text" id="title" name="title" required/>

			    <label for="artist">Artista</label>
			    <input type="text" id="artist" name="artist" required/>

			    <label for="genre">Genere</label>
			    <input type="text" id="genre" name="genre" required/>

			    <label for="year">Anno</label>
			    <input type="number" id="year" name="year" required/>

			    <label for="code">ISBN</label>
			    <input type="text" id="code" name="code" required/>

			    <label for="price">Prezzo</label>
			    <input type="number" id="price" name="price" required/>

			    <input type="number" min="1" value="1" class="quantity-input"/>

				<button type="submit" value="elimina_prodotto" name="azione">Elimina prodotto</button>
				<button type="submit" value="aggiorna_modifiche" name="azione">Aggiorna modifiche</button>
			</form>
		<%@ include file = "../footer.jsp" %>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
				crossorigin="anonymous"></script>
</body>

	</html>
