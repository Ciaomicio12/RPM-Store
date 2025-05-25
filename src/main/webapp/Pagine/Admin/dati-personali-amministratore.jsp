<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Record Road - Dati personali dell'amministratore</title>
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
<%@ include file="../header.jsp" %>
<section>
    <div class="row">
        <div class="col-md-6">
            <h3>Informazioni personali</h3>
            <form>
                <div class="mb-3">
                    <label for="name" class="textForm">Nome</label>
                    <input type="text" class="textForm" id="name" value="${utente.nome}" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="surname" class="textForm">Cognome</label>
                    <input type="text" class="textForm" id="surname" value="${utente.cognome}" name="surname" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="textForm">Email</label>
                    <input type="email" class="textForm" id="email" value="${utente.email}" name="email" required>
                </div>
                <button type="submit" class="btn-outline-danger rounded-pill">Aggiorna</button>
            </form>
        </div>
        <div class="col-md-6">
            <h3>Cambio password</h3>
            <form style="margin-bottom: 100px;">
                <div class="mb-3">
                    <label for="password-attuale" class="textForm">Password Attuale:</label>
                    <input type="password" class="textForm" id="password-attuale" name="password-attuale" required>
                </div>
                <div class="mb-3">
                    <label for="new-password" class="textForm">Nuova password</label>
                    <input type="password" class="textForm" id="new-password" name="new-password" required>
                </div>
                <div class="mb-3">
                    <label for="confirm-password" class="textForm">Conferma nuova password</label>
                    <input type="password" class="textForm" id="confirm-password" name="confirm-password" required>
                </div>
                <button type="submit" class="btn-outline-danger rounded-pill">Aggiorna</button>
            </form>
        </div>
    </div>
</section>
<%@ include file="../footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>