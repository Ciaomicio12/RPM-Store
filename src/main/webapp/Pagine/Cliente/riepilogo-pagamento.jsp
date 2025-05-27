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
  <div class="row">
    <form class="border border-dark px-4 py-3 mx-4 mx-sm-auto mt-5 col-sm-6" method="POST" action="dati_personali_servlet">
      <h5 class="title">Indirizzo di spedizione</h5>
      <div class="border border-dark px-4 py-3 mx-1 mx-sm-auto mt-1 col-sm-6">
        <div class="mx-auto" style="width: 110px;"> 
          <label for="via">Via:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="text" id="via" name="via" required>
        </div> 
        <div class="mx-auto" style="width: 110px;"> 
          <label for="numero-civico">Numero civico:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="text" id="numero-civico" name="numero-civico" required>
        </div> 
        <div class="mx-auto" style="width: 110px;"> 
          <label for="citta">Città:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="text" id="citta" name="citta" required>
        </div> 
        <div class="mx-auto" style="width: 110px;"> 
          <label for="provincia">Provincia:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="text" id="provincia" name="provincia" required>
        </div>
        <div class="mx-auto" style="width: 110px;"> 
          <label for="codice-postale">Codice Postale:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="text" id="codice-postale" name="codice-postale" required>
        </div>
        <div class="mx-auto" style="width: 110px;"> 
          <label for="telefono">Telefono:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="text" id="telefono" name="telefono" required>
        </div>
      </div>
    </form>
    <form class="border border-dark px-4 py-3 mx-4 mx-sm-auto mt-5 col-sm-6" method="POST" action="dati_personali_servlet">
      <h5 class="title">Dati di pagamento</h5>
      <div class="border border-dark px-4 py-3 mx-1 mx-sm-auto mt-1 col-sm-6">
        <div class="mx-auto" style="width: 110px;"> 
          <label for="numero-carta">Numero carta:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="numero-carta" id="numero-carta" name="numero-carta" required>
        </div> 
        <div class="mx-auto" style="width: 110px;"> 
          <label for="scadenza">Scadenza:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="scadenza" id="scadenza" name="scadenza" required>
        </div> 
        <div class="mx-auto" style="width: 110px;"> 
          <label for="cvv">CVV:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="cvv" id="cvv" name="cvv" required>
        </div>
      </div>
    </form>
          <button type="submit" value="conferma-acquisto" name="azione">Conferma il pagamento</button>
  </div>      
</section>


<%@ include file = "../footer.jsp" %>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>