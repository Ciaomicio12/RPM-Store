<%@ page import="java.util.List" %>
<%@ page import="com.progetto.viniliprogetto.Model.Genere" %>
<%@ page import="com.progetto.viniliprogetto.Model.Vinile" %>
<%
    Vinile vinile = (Vinile) request.getAttribute("vinile");
    List<Genere> generi = (List<Genere>) request.getAttribute("generi");
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
<%@ include file="../header.jsp" %>
<h2>Impostazioni prodotto</h2>
<form class="container" enctype="multipart/form-data" method="POST"
      action="<%= request.getContextPath() %>/caricavinile">

    <div class="input-group mb-3">
        <div class="d-flex align-items-start">
            <img width="50px"
                 src="<%= request.getContextPath() %>/img/Cover/<%= vinile==null?"senzacover.jpg" : vinile.getCopertina() %>"
                 alt="Copertina album" class="mr-3 d-block">
            <div class="mb-0 form-control border-0">
                <input type="file" class="form-control" id="cover" name="cover" required>
                <div class="mt-0">
                    <p>Seleziona una copertina da inserire</p>
                </div>
            </div>
        </div>
    </div>

    <div class="mb-3">
        <label for="titolo">Titolo</label>
        <input type="text" id="titolo" value="<%= vinile != null ? vinile.getTitolo() : "" %>" name="titolo" required/>
    </div>

    <div class="mb-3">
        <label for="autore">Autore</label>
        <input type="text" id="autore" value="<%= vinile != null ? vinile.getAutore() : "" %>" name="autore" required/>
    </div>

    <div class="mb-3">
        <!-- iNSERIRE UNA checkbox -->
        <div class="col-75">
            <c:forEach items="${generi}" var="genere">
                <input type="checkbox" id="${genere.id}" name="${genere.id}" value="${genere.id}">
                <label for="${genere.id}">${genere.nome}</label><br>
            </c:forEach>
        </div>

        <!--
        <div class="form-check">
          <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
          <label class="form-check-label" for="flexCheckChecked">
            Checked checkbox
          </label>
        </div>
        -->

        <div id="genre-inputs">
            <!-- Generi aggiunti dall'utente -->
        </div>
    </div>

    <div class="mb-3">
        <label for="anno">Anno</label>
        <input type="number" id="anno" value="<%= vinile != null ? vinile.getAnnoPubblicazione() : "" %>" name="anno"
               required/>
    </div>

    <div class="mb-3">
        <label for="ean">EAN</label>
        <input type="text" onkeyup="verificaean(this)" id="ean" value="<%= vinile != null ? vinile.getEan() : "" %>"
               name="ean" required/>
    </div>

    <div class="mb-3">
        <label for="prezzo">Prezzo</label>
        <input type="text" id="prezzo" value="<%= vinile != null ? vinile.getPrezzo() : "" %>" name="prezzo" required/>
    </div>

    <div class="mb-3">
        <label for="numerodisponibile">Numero di cope disponibili</label>
        <input type="number" id="numerodisponibile" min="1" value="1" max="100" name="numerodisponibile"
               class="quantity-input"/>
    </div>

    <button type="submit" value="elimina_prodotto" name="azione">Elimina prodotto</button>
    <button type="submit" value="pubblica" name="azione">Pubblica Vinile</button>
</form>
<%@ include file="../footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

<script>
    function validateEAN() {
        const eanInput = document.getElementById('code');
        const eanValue = eanInput.value;

        if (eanValue.length < 11 || eanValue.length > 13) {
            alert('L\'EAN deve avere tra 11 e 13 cifre.');
            return false; // Blocca l'aggiornamento
        }
    }

    function validateAnno() {
        const annoInput = document.getElementById('code');
        const annoValue = annoInput.value;

        if (annoValue < 1948) {
            alert('L\'anno di uscita non è valido.');
            return false; // Blocca l'aggiornamento
        }
    }

    function validatePrezzo() {
        const prezzoInput = document.getElementById('code');
        const prezzoValue = prezzoInput.value;

        if (prezzoValue < 0) {
            alert('Prezzo non valido.');
            return false; // Blocca l'aggiornamento
        }
    }

    function validateQuantity() {
        const quantityInput = document.getElementById('code');
        const quantityValue = quantityInput.value;

        if (quantityValue < 0) {
            alert('Numero di copie valido.');
            return false; // Blocca l'aggiornamento
        }
    }
</script>

<script>function verificaean(element) {

    var xmlHttpReq = new XMLHttpRequest();
    xmlHttpReq.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var response = this.response;
            if (response == "no") {
                element.value = "";
                alert("questo ean già esiste");
            }
        }
    }
    xmlHttpReq.open("GET", "verificaean?ean=" + encodeURIComponent(element.value), true);
    xmlHttpReq.send();
}</script>
</body>

</html>
