<%@ page import="com.progetto.viniliprogetto.Model.Utente" %>
<%
  Utente utente = (Utente) request.getAttribute("utente");
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Record Road - Riepilogo pagamento</title>
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
<%@ include file = "../header.jsp" %>
<h2>Riepilogo ordine</h2>
<section class="container-fluid">
    <div class="row border border-dark px-4 py-3 mx-4 mx-sm-auto mt-5" method="POST"
          action="dati_personali_servlet">
      <form class="px-4 py-3 mx-1 mx-auto mx-md-0 mt-1 col-md-6"
            method="POST"
            action="<%= request.getContextPath()%>/cliente/riepilogopagamento">
        <h5 class="title">Indirizzo di spedizione</h5>
        <div class="mx-auto" style="width: 110px;">
          <label for="via" class="form-label">Via:</label>
          <input type="text" class="form-control" id="via" name="via" value="<%=utente.getIndirizzo().getStrada()%>" required>
        </div>
        <div class="mx-auto" style="width: 110px;">
          <label for="numero-civico" class="form-label">Numero civico:</label>
          <input type="text" class="form-control" id="numero-civico" name="numero-civico"
                 value="<%=utente.getIndirizzo().getNumeroCivico()%>" required>
        </div>
        <div class="mx-auto" style="width: 110px;">
          <label for="citta" class="form-label">Città:</label>
          <input type="text" class="form-control" id="citta" name="citta" value="<%=utente.getIndirizzo().getCitta()%>"
                 required>
        </div>
        <div class="mx-auto" style="width: 110px;">
          <label for="cap" class="form-label">Codice Postale:</label>
          <input type="text" class="form-control" id="cap" name="cap" value="<%=utente.getIndirizzo().getCap()%>"
                 required>
        </div>
        <div class="mx-auto" style="width: 110px;">
          <label for="telefono" class="form-label">Telefono:</label>
          <input type="text" class="form-control" id="telefono" name="telefono"
                 value="<%=utente.getIndirizzo().getTelefono()%>" required>
        </div>
        <button type="submit" class="btn btn-outline-danger btn-secondary btn-lg active" value="conferma-acquisto"
                name="azione">Conferma il pagamento
        </button>
      </form>
      <div class="px-4 py-3 mx-4 mx-auto mx-md-0 mt-1 col-md-6">
        <h5 class="title">Dati di pagamento</h5>
      <div class="px-4 py-3 mx-1 mx-sm-auto mt-1">
        <div class="mx-auto" style="width: 110px;">
          <label class="form-label" for="numero-carta">Numero carta:</label>
          <input class="form-control" type="text" id="numero-carta" name="numero-carta" required>
        </div>
        <div class="mx-auto" style="width: 110px;">
          <label class="form-label" for="scadenza">Scadenza:</label>
          <input class="form-control" type="text" id="scadenza" name="scadenza" required>
        </div>
        <div class="mx-auto" style="width: 110px;">
          <label class="form-label" for="cvv">CVV:</label>
          <input class="form-control" type="text" id="cvv" name="cvv" required>
        </div>
      </div>
      </div>
  </div>
</section>


<%@ include file = "../footer.jsp" %>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>