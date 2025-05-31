<%@ page import="com.progetto.viniliprogetto.Model.Ordine" %>
<%@ page import="com.progetto.viniliprogetto.Model.Utente" %>
<%@ page import="com.progetto.viniliprogetto.Model.VinileInOrdine" %>
<%@ page import="com.progetto.viniliprogetto.Controller.DettagliOrdineServlet" %>
<%
    Ordine ordine = (Ordine) request.getAttribute("ordine");
    Utente utente = (Utente) request.getAttribute("utente");
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Record Road - Dettagli dell'ordine</title>
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
<%@ include file="header.jsp" %>


<section class="container-fluid">
    <div class="row">
        <h1>Dettaglio ordine</h1>
        <div class="col-md-4 mb-4 mb-md-0">
            <form class="px-4" method="GET" action="DettagliOrdineServlet">
                <h2 class="title"><strong>Indirizzo di spedizione</strong></h2>
                <div class="mb-3">
                    <% if (utente != null && utente.isAdmin()) { %>
                    <h5>
                        <strong>Destinatario: </strong><%= ordine.getUtente().getNome() %> <%= ordine.getUtente().getCognome() %>
                    </h5>
                    <% } %>
                    <h5><%= ordine.getIndirizzo().getStrada() %>,
                        <%= ordine.getIndirizzo().getNumeroCivico()%>
                    </h5>
                    <h5><%= ordine.getIndirizzo().getCitta() %>
                    </h5>
                    <h5><%= ordine.getIndirizzo().getCap() %>
                    </h5>
                </div>
            </form>
        </div>
        <div class="col-md-4 mb-4 mb-md-0">
            <h2 class="title"><strong>Ordine </strong><i>#<%=ordine.getId()%>
            </i><br><strong>Stato: </strong>
                <%= ordine.getStato() %>
            </h2>
            <div class="mb-1">
                <% for (VinileInOrdine vio : ordine.getViniliInOrdineList()) { %>
                <div class="d-flex mb-3">
                    <img src="<%= request.getContextPath() %>/img/Cover/<%= vio.getVinile().getCopertina() %>"
                         alt="Copertina Album" width="120px" class="img-fluid">
                    <div class="ms-3">
                        <h5><%= vio.getQuantita() %>x <%= vio.getPrezzo()%>&euro;</h5>
                        <h5><%= vio.getVinile().getTitolo()%>
                        </h5>
                        <h5><%= vio.getVinile().getAutore()%>
                        </h5>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
        <div class="col-md-4">
            <h2 class="title">
                <strong>Totale: </strong>
                <i><%=ordine.getTotale()%>&euro;</i>
            </h2>
            <% if (utente != null && utente.isAdmin() && ordine.getStato().equals("P")) { %>
            <form class="border border-dark px-4 py-3 mt-5" method="POST"
                  action="<%= request.getContextPath()%>/dettagliordine">
                <button type="submit" class="btn btn-primary" name="azione"
                        value="<%= DettagliOrdineServlet.AZIONE_ANNULLA%>">Annulla ordine
                </button>
                <button type="submit" class="btn btn-primary" name="azione"
                        value="<%= DettagliOrdineServlet.AZIONE_SPEDISCI%>">Segna come spedito
                </button>
                <input type="hidden" name="ordine" value="<%= ordine.getId() %>">
            </form>
            <% } %>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>

</html>
