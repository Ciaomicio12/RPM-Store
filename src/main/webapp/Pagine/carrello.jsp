<%@ page import="com.progetto.viniliprogetto.Controller.CarrelloServlet" %>
<%@ page import="com.progetto.viniliprogetto.Model.Carrello" %>
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
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"
            integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="header.jsp" %>
<%
    Carrello carrello = (Carrello) session.getAttribute("carrello");
%>
<section class="container">
    <div class="random-albums my-3" class="row">

        <c:forEach items="${carrello.vinili}" var="vinileincarrello">
            <div class="cart-item col-12" id="${vinileincarrello.vinile.ean}">
                <div class="album col-12 row my-5 my-sm-4 mx-auto">
                    <div class="col-9 col-sm-3 mx-auto">
                        <a href="dettaglio-album.html">
                            <img src="<%= request.getContextPath()%>/img/Cover/${vinileincarrello.vinile.copertina}"
                                 alt="Copertina Album 1" class="img-fluid">
                        </a>
                    </div>
                    <div class="information col-12 col-sm-9">
                        <h4>${vinileincarrello.vinile.titolo}</h4>
                        <h4>${vinileincarrello.vinile.prezzo}&euro;</h4>
                    </div>
                </div>
                <div class="item-quantity">
                    <input value="1" type="number" placeholder="quantita"/>
                </div>
                <div class="item-actions">
                    <button class="delete-button" onclick="rimuovi(this)">Rimuovi dal carrello</button>
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

        <!--

          <div class="album col-12 row my-5 my-sm-4 mx-auto">
          <div class="col-9 col-sm-3 mx-auto" >
            <a href="dettaglio-album.html">
              <img src="C:\Users\User\Desktop\Esercizi C - C++\Tecnologie Software per il Web\Progetto\Album\Appetite for Destruction.jpg" alt="Copertina Album 1" class="img-fluid" >
            </a>
          </div>
          <div class="information col-12 col-sm-9">
            <h4>Appetite for Destruction</h4>
            <h4>37.68&euro;</h4>
          </div>
        </div>

          <div class="cart-item">
        <div class="col-12">
          <div class="album col-12 row my-5 my-sm-4 mx-auto">
            <div class="col-9 col-sm-3 mx-auto" >
              <a href="dettaglio-album.html">
                <img src="C:\Users\User\Desktop\Esercizi C - C++\Tecnologie Software per il Web\Progetto\Album\The Final Countdown.jpg" alt="Copertina Album 1" class="img-fluid" >
              </a>
            </div>
            <div class="information col-12 col-sm-9">
              <h4>The Final Countdown</h4>
              <h4>35.14&euro;</h4>
            </div>
          </div>
          <div class="item-quantity">
            <select class="quantity-select">
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
            </select>
          </div>
          <div class="item-actions">
            <button class="delete-button">Rimuovi dal carrello</button>
          </div>
        </div>
      </div>

      <div class="album col-12 row my-5 my-sm-4 mx-auto">
            <div class="col-9 col-sm-3 mx-auto" >
              <a href="dettaglio-album.html">
                <img src="C:\Users\User\Desktop\Esercizi C - C++\Tecnologie Software per il Web\Progetto\Album\The Final Countdown.jpg" alt="Copertina Album 1" class="img-fluid" >
              </a>
            </div>
            <div class="information col-12 col-sm-9">
              <h4>The Final Countdown</h2>
              <h4>35.14&euro;</h3>
            </div>
        </div>
      </div>

        -->


        <div class="prezzo col-4 col-sm-3 mt-4 mt-sm-0">
            <h3>Totale provvisorio (<%= carrello.getQuantita() %>articoli): </h3>
            <h2 class="display-3"><strong><%= carrello.getTotale() %>&euro;</strong></h2>
            <button class="add-to-cart btn btn btn-warning">Procedi all'ordine</button>
        </div>
    </div>
</section>


<%@ include file="footer.jsp" %>

</body>
</html>