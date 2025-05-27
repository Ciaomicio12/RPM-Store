<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Record Road - Inventario</title>
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
                <form class="col d-flex my-0 my-md-3" method="get" action="cerca">
                    <input class="form-control me-2" list="ricerca-datalist" name="ricerca" type="search"
                           onkeyup="ricercaajax(this.value)" placeholder="Cerca per titolo o artista..."
                           aria-label="Search" required>
                    <datalist id="ricerca-datalist"></datalist>
                    <button class="btn btn-outline-success" type="submit">Cerca</button>
                </form>
            </div>
        </div>
        <div class="col-md-6 text-md-end">
            <a href="<%= request.getContextPath() %>/admin/aggiunta-mofica" class="btn btn-success">Aggiungi Nuovo
                Album</a>
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
                    <td><a href="<%= request.getContextPath() %>/vinile?ean=${vinile.ean}">${vinile.titolo}</a>
                    </td>
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

<script>function ricercaajax(str) {
    var dataList = document.getElementById('ricerca-datalist');
    if (str.length == 0) {
        dataList.innerHTML = '';
        return;
    }

    var xmlHttpReq = new XMLHttpRequest();
    xmlHttpReq.responseType = 'json';
    xmlHttpReq.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            dataList.innerHTML = '';

            for (var i in this.response) {

                var option = document.createElement('option');

                option.value = this.response[i];

                dataList.appendChild(option);
            }
        }
    }
    xmlHttpReq.open("GET", "ricercaajax?q=" + encodeURIComponent(str), true);
    xmlHttpReq.send();
}</script>

<%@ include file="../footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>