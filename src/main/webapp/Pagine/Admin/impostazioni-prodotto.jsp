<%@ page import="java.util.List" %>
<%@ page import="com.progetto.viniliprogetto.Model.Genere" %>
<%@ page import="com.progetto.viniliprogetto.Model.Vinile" %>
<%
	Vinile vinile = (Vinile) request.getAttribute("vinile");
	List<Genere> generi = (List<Genere>) request.getAttribute("generi");
%>

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
			<form class="container" method="GET" action="dati_vinili_servlet">

				<div class="input-group mb-3">
					<div class="d-flex align-items-start">
						<img src="<%= request.getContextPath() %>/img/Cover<%= vinile==null?"senzacover.jpg" : vinile.getCopertina() %>" alt="Copertina album" class="mr-3 d-block">
						<div class="mb-0 form-control border-0">
							<input type="file" class="form-control" id="cover" required>
							<div class="mt-0">
								<p>Seleziona una copertina da inserire</p>
							</div>
						</div>
					</div>
				</div>

				<div class="mb-3">
					<label for="title">Titolo</label>
					<input type="text" id="title" value="<%= vinile != null ? vinile.getTitolo() : "" %>" name="title" required/>
				</div>

				<div class="mb-3">
					<label for="artist">Artista</label>
					<input type="text" id="artist" value="<%= vinile != null ? vinile.getAutore() : "" %>" name="artist" required/>
				</div>

				<div class="mb-3">
					<!-- iNSERIRE UNA checkbox -->
					<div class="mb-3">
						<label>Generi:
							<% if(vinile == null) { %>
								Nessun Genere
							<% } else {
								for(Genere genere: vinile.getGeneri()) { %>
								<%= genere.getNome() %>
							<% }
							}%>
						</label>
						<div class="border">
							<% for(Genere genere: generi) { %>
								<div class="form-check">
									<label class="form-check-label">
										<input class="form-check-input" type="checkbox" value="<%= genere.getId() %>">
										<%= genere.getNome() %>
								</label>
							</div>
							<% } %>
						</div>
					</div>

					<!--
					<div class="form-check">
					  <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
					  <label class="form-check-label" for="flexCheckChecked">
						Checked checkbox
					  </label>
					</div>
					-->

					<div id="genre-inputs">
						<!-- Generi aggiunti dall'utente -->
					</div>
					<div>
						<select id="genre-select" multiple>
							<!-- Generi da cui scegliere -->
						</select>
					</div>
				</div>

				<div class="mb-3">
					<label for="year">Anno</label>
					<input type="number" id="year" value="<%= vinile != null ? vinile.getAnnoPubblicazione() : "" %>" name="year" required/>
				</div>

				<div class="mb-3">
					<label for="code">EAN</label>
					<input type="text" id="code" value="<%= vinile != null ? vinile.getEan() : "" %>" name="code" required/>
				</div>

				<div class="mb-3">
					<label for="price">Prezzo</label>
					<input type="number" id="price" value="<%= vinile != null ? vinile.getPrezzo() : "" %>" name="price" required/>
				</div>

				<div class="mb-3">
					<label for="quantity">Numero di cope disponibili</label>
			    	<input type="number" id="quantity" min="1" value="1" class="quantity-input"/>
				</div>

				<button type="submit" value="elimina_prodotto" name="azione">Elimina prodotto</button>
				<button type="submit" value="aggiorna_modifiche" name="azione">Aggiorna modifiche</button>
			</form>
		<%@ include file = "../footer.jsp" %>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
				crossorigin="anonymous"></script>

		<script>
			function validateEAN() {
				const eanInput = document.getElementById('code');
				const eanValue = eanInput.value;

				if (eanValue.length < 11 || eanValue.length > 13) {
					alert('L\'EAN deve avere tra 11 e 13 cifre.');
					return false; // Blocca l'aggiornamento
				}
			}
		</script>
</body>

	</html>
