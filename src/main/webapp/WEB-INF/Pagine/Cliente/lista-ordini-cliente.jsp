<%@ page import="com.rpm_store.Model.Ordine" %>
<%@ page import="java.util.List" %>
<%
    List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");

%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>RPM-Store - Lista ordini</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/style.css"/>
    <style>
        .nav-link:hover {
            text-decoration: underline;
        }
        th {
            cursor: pointer;
            user-select: none;
        }

        .sort-arrow {
            font-size: 0.8em;
            margin-left: 4px;
            color: gray;
        }
    </style>
    <script>
        let sortDirection = {};

        function sortTable(columnIndex) {
            const table = document.querySelector("table");
            const rows = Array.from(table.rows).slice(1); // Skip the header row
            const tbody = table.querySelector("tbody");

            const direction = sortDirection[columnIndex] = !sortDirection[columnIndex]; // toggle
            const factor = direction ? 1 : -1;

            rows.sort((a, b) => {
                const cellA = a.cells[columnIndex].innerText.trim();
                const cellB = b.cells[columnIndex].innerText.trim();

                const isNumeric = !isNaN(cellA.replace(',', '.')) && !isNaN(cellB.replace(',', '.'));
                const aVal = isNumeric ? parseFloat(cellA.replace(',', '.')) : cellA.toLowerCase();
                const bVal = isNumeric ? parseFloat(cellB.replace(',', '.')) : cellB.toLowerCase();

                return aVal > bVal ? factor : aVal < bVal ? -factor : 0;
            });

            tbody.innerHTML = '';
            rows.forEach(row => tbody.appendChild(row));
        }
    </script>
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
    </div>
    <div class="container">
        <table class="table table-striped">
            <thead>
            <tr>
                <th onclick="sortTable(0)">Numero ordine <span class="sort-arrow">▲▼</span></th>
                <th onclick="sortTable(1)">Destinatario <span class="sort-arrow">▲▼</span></th>
                <th onclick="sortTable(2)">Data Acquisto <span class="sort-arrow">▲▼</span></th>
                <th onclick="sortTable(3)">Totale <span class="sort-arrow">▲▼</span></th>
                <th onclick="sortTable(4)">Stato <span class="sort-arrow">▲▼</span></th>
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
