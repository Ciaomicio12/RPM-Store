<%@ page import="com.progetto.viniliprogetto.Model.Ordine" %>
<%@ page import="com.progetto.viniliprogetto.Model.Utente" %>
<%@ page import="com.progetto.viniliprogetto.Model.VinileInOrdine" %>
<%
    Ordine ordine = (Ordine) request.getAttribute("ordine");
    Utente utente = (Utente) request.getAttribute("utente");
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
<%@ include file="header.jsp" %>


<section class="container-fluid">
    <h2>Dettaglio ordine</h2>
    <div class="row">
        <div class="col-md-6">
            <form class="border border-dark px-4 py-3 mt-5" method="GET" action="DettagliOrdineServlet">
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
        <div class="col-md-6">
            <h2 class="title"><strong>Ordine </strong><i>#<%=ordine.getId()%>
            </i><br><strong>Stato: </strong>
                <%= ordine.getStatoStringa() %>
            </h2>
            <div class="mb-3">
                <% for (VinileInOrdine vio : ordine.getViniliInOrdineList()) { %>
                <img src="<%= request.getContextPath() %>/img/Cover/<%= vio.getVinile().getCopertina() %>"
                     alt="Copertina Album" class="img-fluid">
                <h5><%= vio.getQuantita() %>x <%= vio.getPrezzo()%>&euro;</h5>
                <h5><%= vio.getVinile().getTitolo()%>
                </h5>
                <h5><%= vio.getVinile().getAutore()%>
                </h5>
                <% } %>
            </div>
            <% if (utente != null && utente.isAdmin() && ordine.getStato().equals("P")) { %>
            <form class="border border-dark px-4 py-3 mt-5" method="POST"
                  action="<%= request.getContextPath()%>/dettagliordine">
                <button type="submit" class="btn btn-primary" name="azione" value="annulla-ordine">Annulla ordine
                </button>
                <button type="submit" class="btn btn-primary" name="azione" value="spedisci-ordine">Segna come spedito
                </button>
                <input type="hidden" name="ordine" value="<%= ordine.getId() %>">
                <% } %>
            </form>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>

</html>
