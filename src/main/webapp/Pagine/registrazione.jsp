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
<%@ include file = "header.jsp" %>
<section class="container-fluid">
  <div class="row">
    <form class="border border-dark px-4 py-3 mx-4 mx-sm-auto mt-5 col-sm-6" method="POST" action="servlet_registrazione">
      <h2 class="title">Creare account</h2>
      <div class="border border-dark px-4 py-3 mx-3 mx-sm-auto mt-1 col-sm-6">

        <div class="mx-auto" style="width: 110px;"> 
          <label for="email">Indirizzo email:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="email" id="email" name="email" required>
        </div> 
        <div class="mx-auto" style="width: 50px;">       
          <label for="password">Password:</label>
        </div>   
        <div class="mx-auto" style="width: 200px;">
          <input type="password" id="password" name="password" required> 
        </div> 
        <div class="mx-auto" style="width: 130px;"> 
          <label for="email">Verifica Password:</label>
        </div>  
        <div class="mx-auto" style="width: 200px;">
          <input type="password" id="confirm-password" name="confirm-password" required>
        </div> 
      </div>

      <div class="mx-auto" style="width: 90px;">      
        <input type="submit" value="Continua">
      </div>

    </form>
  </div>
</section>


<%@ include file = "footer.jsp" %>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>