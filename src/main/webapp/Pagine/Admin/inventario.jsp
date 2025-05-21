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
<%@ include file="../header.jsp" %>

<div class="container my-4">
    <div class="row">
        <div class="col-md-6">
            <div class="input-group mb-3">
                <input type="text" id="search-input" class="form-control"
                       placeholder="Cerca per titolo, artista, genere...">
                <button id="search-button" class="btn btn-primary" type="button">Cerca</button>
            </div>
        </div>
        <div class="col-md-6 text-md-end">
            <a href="<%= request.getContextPath() %>/admin/aggiunta" class="btn btn-success">Aggiungi Nuovo Album</a>
        </div>
    </div>
</div>

<div class="container">
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Copertina</th>
                <th onclick="sortTable(0)">Titolo</th>
                <th onclick="sortTable(1)">Artista</th>
                <th onclick="sortTable(2)">Genere</th>
                <th onclick="sortTable(3)">Prezzo</th>
                <th onclick="sortTable(4)">EAN</th>
                <th onclick="sortTable(5)">Quantità</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${vinili}" var="vinile">
                <tr>
                    <td><img src="${pageContext.request.contextPath}/img/Cover/${vinile.copertina}" width="120px"
                             class="img-fluid"></td>
                    <td><a href="impostazioni-prodotto">${vinile.titolo}</a></td>
                    <td>${vinile.autore}</td>
                    <td>${vinile.getGenereString()}</td>
                    <td>${vinile.prezzo}&euro;</td>
                    <td>${vinile.ean}</td>
                    <td>${vinile.numeroDisponibili}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="../footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>