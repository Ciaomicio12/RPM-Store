<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Record Road - Dati personali dell'utente</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/styleLogin.css"/>
</head>
<body>
<header>
    <%@ include file="../header.jsp" %>
</header>
<section>
    <div class="row">
        <div class="col-md-6">
            <form class="border border-dark px-4 py-3 mt-5 margini" method="POST" action="dati_personali_servlet">
                <h5 class="title">Informazioni personali</h5>
                <div class="mb-3">
                    <label for="name" class="textForm">Nome:</label>
                    <input type="text" class="textForm" id="name" value="${utente.nome}" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="surname" class="textForm">Cognome:</label>
                    <input type="text" class="textForm" id="surname" value="${utente.cognome}" name="surname"
                           required>
                </div>
                <div class="mb-3">
                    <label for="email" class="textForm">Indirizzo email:</label>
                    <input type="email" class="textForm" id="email" value="${utente.email}" name="email" required>
                </div>
                <button type="submit" class="btn-outline-danger rounded-pill" name="azione">Aggiorna</button>
            </form>
        </div>
        <div class="col-md-6">
            <form class="border border-dark px-4 py-3 mt-5 margini" method="POST" action="dati_personali_servlet">
                <h5 class="title">Cambio password</h5>
                <div class="mb-3">
                    <label for="password-attuale" class="textForm">Password Attuale:</label>
                    <input type="password" class="textForm" id="password-attuale" name="password-attuale" required>
                </div>
                <div class="mb-3">
                    <label for="new-password" class="textForm">Nuova Password:</label>
                    <input type="password" class="textForm" id="new-password" name="new-password" required>
                </div>
                <div class="mb-3">
                    <label for="confirm-password" class="textForm">Conferma nuova password:</label>
                    <input type="password" class="textForm" id="confirm-password" required>
                </div>
                <button type="submit" class="btn-outline-danger rounded-pill" name="azione" value="aggiorna_password">
                    Aggiorna
                </button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <form class="border border-dark px-4 py-3 mt-5 margini" method="POST" action="dati_personali_servlet"
                  style="margin-bottom: 100px;">
                <h5 class="title">Indirizzo di spedizione</h5>
                <div class="mb-3">
                    <label for="via" class="textForm">Via:</label>
                    <input type="text" class="textForm" id="via" name="via" value="${utente.indirizzo.strada}" required>
                </div>
                <div class="mb-3">
                    <label for="numero-civico" class="textForm">Numero civico:</label>
                    <input type="text" class="textForm" id="numero-civico" name="numero-civico"
                           value="${utente.indirizzo.numeroCivico}" required>
                </div>
                <div class="mb-3">
                    <label for="citta" class="textForm">Città:</label>
                    <input type="text" class="textForm" id="citta" name="citta" value="${utente.indirizzo.citta}"
                           required>
                </div>
                <div class="mb-3">
                    <label for="telefono" class="textForm">Telefono:</label>
                    <input type="text" class="textForm" id="telefono" name="telefono"
                           value="${utente.indirizzo.telefono}" required>
                </div>
                <div class="mb-3">
                    <label for="codice-postale" class="textForm">Codice Postale:</label>
                    <input type="text" class="textForm" id="codice-postale" name="codice-postale"
                           value="${utente.indirizzo.cap}" required>
                </div>
                <button type="submit" class="btn-outline-danger rounded-pill" name="azione" value="aggiorna_indirizzo">
                    Aggiorna
                </button>
            </form>
        </div>
    </div>
</section>
<footer>
    <%@ include file="../footer.jsp" %>
</footer>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>