<%@ page import="com.progetto.viniliprogetto.Model.Ordine" %>
<%@ page import="java.util.List" %>
<%
    List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");

%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Record Road - Lista ordini</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/style.css"/>
    <style>
        .nav-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<%@ include file="../header.jsp" %>
<c:if test="${ordini.isEmpty()}">
    <div class="container" style="min-height: 50vh">
        <div class="mt-4">
            <h3>Non ci sono ordini</h3>
        </div>
    </div>
</c:if>
<c:if test="${!ordini.isEmpty()}">
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
                <th>Numero ordine</th>
                <th>Destinatario</th>
                <th>Data Acquisto</th>
                <th>Totale</th>
                <th>Stato</th>
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
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</c:if>

<%@ include file="../footer.jsp" %>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
