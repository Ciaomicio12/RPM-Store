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
<%@ include file = "../header.jsp" %>
<section class="container-fluid">
  <div class="row">
    <form class="border border-dark px-4 py-3 mx-4 mx-sm-auto mt-5 col-sm-6" method="POST" action="dati_personali_servlet">
      <h5 class="title">Informazioni personali</h5>
      <div class="border border-dark px-4 py-3 mx-1 mx-sm-auto mt-1 col-sm-6">
        <div class="mx-auto" style="width: 110px;"> 
          <label for="name">Nome:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="name" id="name" name="name" required>
        </div> 
        <div class="mx-auto" style="width: 110px;"> 
          <label for="surname">Cognome:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="surname" id="surname" name="surname" required>
        </div> 
        <div class="mx-auto" style="width: 110px;"> 
          <label for="email">Indirizzo email:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="email" id="email" name="email" required>
        </div>
          <button type="submit" value="aggiorna_dati" name="azione">Aggiorna</button>
      </div> 
    </form> 
    <form class="border border-dark px-4 py-3 mx-4 mx-sm-auto mt-5 col-sm-6" method="POST" action="dati_personali_servlet">
      <h5 class="title">Cambio password</h5>
      <div class="border border-dark px-4 py-3 mx-1 mx-sm-auto mt-1 col-sm-6">
        <div class="mx-auto" style="width: 110px;"> 
          <label for="password">Nuova Password:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="password" id="new-password" name="new-password" required>
        </div> 
        <div class="mx-auto" style="width: 110px;"> 
          <label for="password">Conferma nuova password:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="password" id="confirm-password" required>
        </div>
          <button type="submit" value="aggiorna_password" name="azione">Aggiorna</button>
      </div>
    </form> 
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
        <button type="submit" value="aggiorna_indirizzo" name="azione">Aggiorna</button>
      </div>
    </form> 
  </div>      
</section>


<%@ include file = "../footer.jsp" %>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>