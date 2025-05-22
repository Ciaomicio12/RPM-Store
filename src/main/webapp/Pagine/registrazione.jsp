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
  <link rel="stylesheet" href="<%= request.getContextPath()%>/styleLogin.css"/>
</head>
<body>
<%@ include file="header.jsp" %>
<section>
  <div class="row">
    <form class="px-4 py-3 mx-auto mt-5 col-sm-6 col-md-8" method="POST" action="registrazione"
          style="margin-bottom: 100px;">
      <h2 class="title">Creare account</h2>
      <div class="px-4 py-3 mx-3 mx-sm-auto mt-1 col-sm-6">
        <p> ${formerror}</p>
        <br>
        <div class="form-group">
          <label for="nome">Nome</label>
          textForm
          <input type="text" class="textForm" id="nome" name="nome" placeholder="Inserisci nome" required>
        </div>
        <div class="form-group">
          <label for="cognome">Cognome</label>
          <div></div>
          <input type="text" class="textForm" id="cognome" name="cognome" placeholder="Inserisci cognome" required>
        </div>
        <div class="form-group">
          <label for="username">Username</label>
          <div></div>
          <input type="text" class="textForm" id="username" name="username" placeholder="Inserisci username"
                 required>
        </div>
        <div class="form-group">
          <label for="sesso">Sesso</label>
          <div></div>
          <select name="sesso" id="sesso" class="textForm">
            <option value="Maschio">Maschio</option>
            <option value="Femmina">Femmina</option>
          </select>
        </div>
        <div class="form-group">
          <label for="email">Email</label>
          <div></div>
          <input type="email" class="textForm" id="email" name="email" placeholder="Inserisci email" required>
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <div></div>
          <input type="password" class="textForm" id="password" name="password" placeholder="Inserisci password"
                 required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$"
                 title="La password é mal formata! La password deve essere lunga 8 caratteri e al massimo 32. Deve contenere una lettere maiuscola e una minuscola. Deve contenere un numero"
          >
        </div>
        <div class="form-group">
          <label for="confirm-password">Conferma Password</label>
          <div></div>
          <input type="password" class="textForm" id="confirm-password" name="confirm-password"
                 placeholder="Inserisci nuovamente la password" required>
        </div>
      </div>

      <div class="mx-auto" style="width: 90px;">
        <button type="submit" class="btn-outline-danger rounded-pill">Conferma</button>
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