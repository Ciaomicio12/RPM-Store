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
    <form class="border border-dark px-4 py-3 mx-4 mx-sm-auto mt-5 col-sm-6" method="POST" action="servlet_assistenza">
      <h2 class="title">Assistenza</h2>
      <h4 class="explanation">Puoi contattare direttamente l'assistenza clienti inviando un messaggio tramite questo
        form. In questo modo, inserendo il tuo indirizzo email e l'argomento del tuo messaggio, ci aiuterai ad essere
        più veloci nel risponderti. Inoltre, tramite il form, eviti che il tuo messaggio possa essere considerato
        spam.</h4>
      <div class="border border-dark px-4 py-3 mx-1 mx-sm-auto mt-1 col-sm-6">
        <div class="form-group">
          <label for="nome">Nome</label>
          <input type="text" class="form-control" id="nome" name="nome" placeholder="Inserisci nome e cognome" required>
        </div>
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" class="form-control" id="email" name="email" placeholder="Inserisci email" required>
        </div>
        <div class="form-group">
          <label for="oggetto">Oggetto</label>
          <input type="text" class="form-control" id="oggetto" name="oggetto" placeholder="Oggetto:" required>
        </div>
        <div class="row">
          <div class="col-50">
            <label for="subject">Descrivi il problema</label>
          </div>
          <div class="col-125">
                  <textarea
                          id="subject"
                          name="subject"
                          placeholder="Scrivi qui"
                          style="height: 200px;"
                          required
                  ></textarea>
          </div>
        </div>
        <div class="row">
          <input id="contatti" type="submit" value="Invia messaggio"/>
        </div>
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