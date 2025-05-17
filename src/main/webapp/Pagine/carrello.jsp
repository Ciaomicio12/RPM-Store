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
<section class="container">
    <div class="random-albums my-3" class="row">

        <c:forEach items="${carrello.vinili}" var="vinileincarrello">
            <div class="cart-item">
                <div class="col-12">
                    <div class="album col-12 row my-5 my-sm-4 mx-auto">
                        <div class="col-9 col-sm-3 mx-auto">
                            <a href="dettaglio-album.html">
                                <img src="img/Cover/${vinileincarrello.vinile.copertina}"
                                     alt="Copertina Album 1" class="img-fluid">
                            </a>
                        </div>
                        <div class="information col-12 col-sm-9">
                            <h4>${vinileincarrello.vinile.titolo}</h4>
                            <h4>${vinileincarrello.vinile.prezzo}&euro;</h4>
                        </div>
                    </div>
                    <div class="item-quantity">
                        <input value="1" type="number" placeholder="quantita">
                        <input>
                    </div>
                    <div class="item-actions">
                        <button href="carrello?action=rimuovi&ean=${vinile.ean}" class="delete-button">Rimuovi dal
                            carrello
                        </button>
                    </div>
                </div>
            </div>
        </c:forEach>


        <script>
            function rimuovi(isbn) {
                var xmlHttpReq = new XMLHttpRequest();
                xmlHttpReq.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        console.log(this.responseText);
                        var prezzi = this.responseText.split(" ");
                        var conferma = prezzi[0];
                        var totProdotti = prezzi[6];
                        if (conferma == "ok" && totProdotti != "0") {
                            var prezzoCarrelloNetto = prezzi[1];
                            var prezzoTasse = prezzi[2];
                            var prezzoTotale = prezzi[3];
                            var prezzoSpedizione = prezzi[4];
                            var prezzoCarrelloLordo = prezzi[5];
                            var cod = "<b>Subtotale:</b> " + prezzoCarrelloNetto + " €" + "<br>\n" +
                                "<b>Tasse (22%):</b> " + prezzoTasse + " €" + "<br>\n" +
                                "<b>Totale netto:</b> " + prezzoTotale + " €" + "<br>\n" +
                                "<b>Costo Spedizione:</b> " + prezzoSpedizione + " €" + "<br>\n" +
                                "<b>Totale Lordo:</b> " + prezzoCarrelloLordo + " €";
                            $("#tot").html(cod);
                        } else if (conferma == "ok" && totProdotti == "0") {
                            $("#totale").fadeOut("normal", function () {
                                $(this).remove();
                            });
                            document.getElementById('titolo').insertAdjacentHTML('afterend', '<div class="card" id="carrellovuoto" style="display: none">\n' +
                                '                <h3>Al momento non ci sono prodotti nel carrello</h3>\n' +
                                '            </div>');
                            $("#carrellovuoto").fadeIn("slow");
                        }
                        $("#carrellonavbar").text("Carrello (" + totProdotti + ")");
                    }
                }
                xmlHttpReq.open("GET", "rimuovicarrello?id=" + isbn, true);
                xmlHttpReq.send();
                $("#" + isbn).fadeOut("normal", function () {
                    $(this).remove();
                });
            }

            $(document).ready(function () {


                $("input[type=\"number\"] ").change((event) => {
                    var id = (event.target.id).slice(16, event.target.id.lenght);
                    var quantita = (event.target.value);
                    $.ajax({
                        url: "ModificaCarrello",
                        data: {
                            id,
                            quantita
                        },
                        error: () => console.error("errore Ajax Carrello"),
                        success: (responseText) => {
                            var prezzi = responseText.split(" ");
                            var prezzoProdottoTot = prezzi[0];
                            var prezzoCarrelloNetto = prezzi[1];
                            var prezzoTasse = prezzi[2];
                            var prezzoTotale = prezzi[3];
                            var prezzoSpedizione = prezzi[4];
                            var prezzoCarrelloLordo = prezzi[5];
                            var totProdotti = prezzi[6];
                            var disponibili = prezzi[7];
                            var cod = "<b>Subtotale:</b> " + prezzoCarrelloNetto + " €" + "<br>\n" +
                                "<b>Tasse (22%):</b> " + prezzoTasse + " €" + "<br>\n" +
                                "<b>Totale netto:</b> " + prezzoTotale + " €" + "<br>\n" +
                                "<b>Costo Spedizione:</b> " + prezzoSpedizione + " €" + "<br>\n" +
                                "<b>Totale Lordo:</b> " + prezzoCarrelloLordo + " €";
                            $("#tot").html(cod);
                            $("#prezzoProdotto" + id).text(prezzoProdottoTot + " €");
                            $("#carrellonavbar").text("Carrello (" + totProdotti + ")");

                            if (quantita.length > 8) {
                                quantita = quantita.substring(0, 7);
                            }

                            if (parseInt(disponibili) <= parseInt(quantita)) {
                                $("#modificaQuantita" + id).val("" + disponibili);
                            }
                            if (quantita <= 0 || quantita == "") {
                                $("#modificaQuantita" + id).val("1");
                            }
                        }
                    })
                })
            });
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
            <h3>Totale provvisorio (3 articoli): </h3>
            <h2 class="display-3"><strong>105.95&euro;</strong></h2>
            <button class="add-to-cart btn btn btn-warning">Procedi all'ordine</button>
        </div>
    </div>
</section>


<%@ include file="footer.jsp" %>

</body>
</html>