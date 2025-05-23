<%@ page import="com.progetto.viniliprogetto.Model.Ordine" %>
<%@ page import="com.progetto.viniliprogetto.Model.Utente" %>
<%@ page import="java.util.List" %>
<%
	Ordine ordine = (Ordine) request.getAttribute("ordine");
	Utente utente = (Utente) request.getAttribute("utente");
	List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");

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

<div class="container my-4">
    <div class="row">
        <div class="col-md-6">
            <div class="input-group mb-3">
                <input type="text" id="search-input" class="form-control"
                       placeholder="Cerca per destinatario, numero ordine...">
                <button id="search-button" class="btn btn-primary" type="button">Cerca</button>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <table class="table table-striped">
        <thead>
			<tr>
				<th onclick="sortTable(0)">Numero ordine</th>
				<th onclick="sortTable(1)">Destinatario</th>
				<th onclick="sortTable(3)">Data Acquisto</th>
				<th onclick="sortTable(4)">Totale</th>
				<th onclick="sortTable(5)">Stato</th>
			</tr>
        </thead>
        <tbody>
			<c:forEach items="${ordine}" var="ordine">
				<tr>
					<td><a href="ordine-in-dettaglio.jsp"><%= ordine.getId() %></a></td>
					<td><%= ordine.getUtente().getCognome() %> <%= ordine.getUtente().getNome() %></td>
					<td><%= ordine.getData() %></td>
					<td><%= ordine.getTotale() %>&euro;</td>
					<td>
						<c:choose>
							<c:when test="${ordine.stato == 'S'}">
								Spedito
							</c:when>
							<c:when test="${ordine.stato == 'P'}">
								Pagato
							</c:when>
							<c:when test="${ordine.stato == 'A'}">
								Annullato
							</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
        </tbody>
    </table>
</div>

<script src="script.js"></script>


<%@ include file = "../footer.jsp" %>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
