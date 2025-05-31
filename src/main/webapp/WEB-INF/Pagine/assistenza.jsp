<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Record Road - Assistenza</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <style>
    .nav-link:hover {
      text-decoration: underline;
    }
  </style>
  <link rel="stylesheet" href="<%= request.getContextPath()%>/style.css"/>
</head>
<body>
<%@ include file="header.jsp" %>
<section class="container">
  <div class="row">
    <div>
      <div mx-auto py-3>
        <h2 class="title">Assistenza</h2>
        <p class="explanation fw-bold">Puoi contattare direttamente l'assistenza clienti inviando un messaggio tramite questo
          form. In questo modo, inserendo il tuo indirizzo email e l'argomento del tuo messaggio, ci aiuterai ad essere
          più veloci nel risponderti. Inoltre, tramite il form, eviti che il tuo messaggio possa essere considerato
          spam.</p>
      </div>
      <form class="px-4 py-3 col-sm-8 col-md-6 mx-auto" method="POST" action="servlet_assistenza" style="margin-bottom: 100px;">

        <div class="mb-3">
          <label for="nome" class="form-label">Nome</label>
          <div></div>
          <input type="text" class="form-control" id="nome" name="nome" placeholder="Inserisci nome e cognome" required>
        </div>
        <div class="mb-3">
          <label for="email" class="form-label">Email</label>
          <div></div>
          <input type="email" class="form-control" id="email" name="email" placeholder="Inserisci email" required>
        </div>
        <div class="mb-3">
          <label for="oggetto" class="form-label">Oggetto</label>
          <div></div>
          <input type="text" class="form-control" id="oggetto" name="oggetto" placeholder="Oggetto:" required>
        </div>
        <div class="mb-3">
          <label for="subject" class="form-label">Descrivi il problema</label>
          <div></div>
          <textarea class="form-control" id="subject" name="subject" placeholder="Scrivi qui" style="height: 200px;"
                    required></textarea>
        </div>
        <div class="mt-3 mb-4 text-center">
          <button type="submit" class="btn-outline-danger rounded-pill px-4">Invia messaggio</button>
        </div>
      </form>
    </div>
  </div>
</section>
<%@ include file="footer.jsp" %>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>