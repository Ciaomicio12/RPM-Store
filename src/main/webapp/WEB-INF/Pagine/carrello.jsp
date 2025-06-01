<%@ page import="java.util.List" %>
<%@ page import="com.progetto.viniliprogetto.Controller.CarrelloServlet" %>
<%@ page import="com.progetto.viniliprogetto.Model.Carrello" %>
<%@ page import="com.progetto.viniliprogetto.Model.Utente" %>
<%
    Carrello carrello = (Carrello) session.getAttribute("carrello");
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css"/>
</head>
<body>
<%@ include file="header.jsp" %>
<%
%>
<section class="container">
    <% if (!carrello.getViniliInCarrello().isEmpty() && carrello.getViniliInCarrello().size() > 0) { %>
    <div class="random-albums my-3" class="row">

        <c:forEach items="${carrello.viniliInCarrello}" var="vinileincarrello">
            <div class="cart-item col-12" id="${vinileincarrello.vinile.ean}">
                <div class="col-12 row my-5 my-sm-4 mx-auto">
                    <div class="col-9 col-sm-3 mx-auto">
                        <a href="<%= request.getContextPath()%>/vinile?ean=${vinileincarrello.vinile.ean}">
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
                    <input class="textForm" value="1" type="number" placeholder="quantita"
                           onchange="aggiornaQuantita(this)"/>
                </div>
                <div class="item-actions">
                    <button class="btn-outline-danger rounded-pill" onclick="rimuovi(this)">Rimuovi dal carrello
                    </button>
                </div>
            </div>
        </c:forEach>

        <div class="prezzo col-4 col-sm-3 mt-4 mt-sm-0">
            <h3>Totale provvisorio (<span id="quantita-carrello"><%= carrello.getQuantita() %></span> articoli): </h3>
            <h2 class="display-3" id="totale-carrello"><strong><%= carrello.getTotale() %>&euro;</strong></h2>
            <a class="btn-outline-danger rounded-pill"
               href="<%= request.getContextPath() %>/cliente/riepilogopagamento">Procedi
                all'ordine</a>
        </div>
    </div>
    <% } else { %>
    <div style="min-height: 50vh">
        <h3>Il carrello è vuoto</h3>
    </div>
    <% } %>
</section>


<%@ include file="footer.jsp" %>

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
            let json = JSON.parse(resp);
            if (json.status === "OK") {
                cartItem.remove();
                let length = $(".cart-item").length
                if (length > 0) {
                    $("#quantita-carrello").html(json.quantita);
                    $("#totale-carrello").html(json.totale + "&euro;");
                } else {
                    $(".prezzo").html("<h3>Il carrello è vuoto</h3>");
                }
            } else {
                console.error("Qualcosa è andato storto");
            }
        }).catch(resp => {
            console.error(resp);
        });
    }

    function aggiornaQuantita(input) {
        let method = "Post";
        let url = "<%= request.getContextPath()%>/cliente/carrello";
        let action = "<%=CarrelloServlet.MODIFICA_QUANTITA%>";
        let cartItem = $(input).parent().parent();
        let ean = cartItem.attr("id");
        $.ajax({
            url: url,
            method: method,
            data: {
                action: action,
                quantita: $(input).val(),
                ean: ean
            }
        }).then(resp => {
            json = JSON.parse(resp);
            if (json.status === "OK") {
                $("#quantita-carrello").html(json.quantita);
                $("#totale-carrello").html(json.totale + "&euro;");
            } else {
                console.error("Qualcosa è andato storto");
            }
        }).catch(err => {
            console.error(err);
        })
    }
</script>

</body>
</html>