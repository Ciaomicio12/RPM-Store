<%@ page import="java.util.List" %>
<%@ page import="com.progetto.viniliprogetto.Controller.CarrelloServlet" %>
<%@ page import="com.progetto.viniliprogetto.Model.Carrello" %>
<%@ page import="com.progetto.viniliprogetto.Model.Ordine" %>
<%@ page import="com.progetto.viniliprogetto.Model.Utente" %>
<%
    Ordine ordine = (Ordine) request.getAttribute("ordine");
    Utente utente = (Utente) request.getAttribute("utente");

%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Record Road - Carrello</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .nav-link:hover {
            text-decoration: underline;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"
            integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="http://localhost:8080/ViniliProgetto_war_exploded/styledettaglio.css"/>
</head>
<body>
<%@ include file="header.jsp" %>
<%
    Carrello carrello = (Carrello) session.getAttribute("carrello");
%>
<section class="container">
    <div class="random-albums my-3" class="row">

        <c:forEach items="${carrello.viniliInCarrello}" var="vinileincarrello">
            <div class="cart-item col-12" id="${vinileincarrello.vinile.ean}">
                <div class="col-12 row my-5 my-sm-4 mx-auto">
                    <div class="col-9 col-sm-3 mx-auto">
                        <a href="dettaglio-album.html">
                            <img src="<%= request.getContextPath()%>/img/Cover/${vinileincarrello.vinile.copertina}"
                                 alt="Copertina Album 1" class="img-fluid">
                        </a>
                    </div>
                    <div class="col-12 col-sm-9">
                        <h4>${vinileincarrello.vinile.autore}</h4>
                        <h4>${vinileincarrello.vinile.titolo}</h4>
                        <h4>${vinileincarrello.vinile.prezzo}&euro;</h4>
                        <h4>${vinileincarrello.vinile.ean}</h4>
                    </div>
                </div>
                <div class="item-quantity">
                    <input class="textForm" value="1" type="number" placeholder="quantita"/>
                </div>
                <div class="item-actions">
                    <button class="btn-outline-danger rounded-pill" onclick="rimuovi(this)">Rimuovi dal carrello
                    </button>
                </div>
            </div>
        </c:forEach>


        <script>
            function rimuovi(button) {
                let method = "Post";
                let url = "<%= request.getContextPath()%>/cliente/carrello";
                let action = "<%=CarrelloServlet.RIMUOVI_VINILE%>";
                let cartItem = $(button).parent().parent();
                let ean = cartItem.attr("id");
                $.ajax({
                    method: method,
                    url: url,
                    data: {
                        action: action,
                        ean: ean
                    }
                }).then(resp => {
                    console.log(resp);
                    cartItem.remove();
                }).catch(resp => {
                    console.error(resp);
                });
            }
        </script>

        <div class="prezzo col-4 col-sm-3 mt-4 mt-sm-0">
            <h3>Totale provvisorio (<%= carrello.getQuantita() %>articoli): </h3>
            <h2 class="display-3"><strong><%= carrello.getTotale() %>&euro;</strong></h2>
            <a class="btn-outline-danger rounded-pill"
               href="<%= request.getContextPath() %>/cliente/riepilogopagamento?id=<%=ordine.getUtente() %>Procedi
                all'ordine"></a>
        </div>
    </div>
</section>


<%@ include file="footer.jsp" %>

</body>
</html>