<%@ page import="com.progetto.viniliprogetto.Model.Ordine" %>
<%@ page import="java.util.List" %>
<%
	List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
%>
ss
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Record Road - Lista ordini (Impostazioni da amministratore)</title>
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
		  rel="stylesheet"
		  integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<style>
		.nav-link:hover {
			text-decoration: underline;
		}
	</style>
	<link rel="stylesheet" href="<%= request.getContextPath()%>/style.css"/>
</head>
<body>
<%@ include file = "../header.jsp" %>


<div class="container">
    <table class="table table-striped">
        <thead>
			<tr>
				<th onclick="sortTable(0)">Numero ordine</th>
				<th onclick="sortTable(1)">Destinatario</th>
				<th onclick="sortTable(2)">Data Acquisto</th>
				<th onclick="sortTable(3)">Totale</th>
				<th onclick="sortTable(4)">Stato</th>
			</tr>
		</thead>
		<tbody>
		<% for (Ordine ordine : ordini) {%>
		<tr>
			<td>
				<a href="<%= request.getContextPath()%>/user/dettagliordine?id=<%= ordine.getId()%>"><%= ordine.getId() %>
				</a></td>
			<td><%= ordine.getUtente().getCognome() %> <%= ordine.getUtente().getNome() %>
			</td>
			<td><%= ordine.getData() %>
			</td>
			<td><%= ordine.getTotale() %>&euro;</td>
			<td><%= ordine.getStatoStringa() %>
			</td>
		</tr>
		<% } %>
		</tbody>
	</table>
</div>

<script src="script.js"></script>

<%@ include file="../footer.jsp" %>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
				crossorigin="anonymous"></script>
</body>

	</html>
