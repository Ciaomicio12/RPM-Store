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
    <link rel="stylesheet" href="<%= request.getContextPath()%>/style.css"/>
</head>
<body>
<%@ include file="../header.jsp" %>
<section>
    <div class="row">
        <div class="col-md-6">
            <form class="border border-dark px-4 py-3 mt-5 margini" method="POST"
                  action="<%=request.getContextPath()%>/user/cambiadatipersonali">
                <h5 class="title">Informazioni personali</h5>
                <div class="mb-3">
                    <label for="name" class="form-label">Nome</label>
                    <input type="text" class="form-control" id="name" value="${utente.nome}" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="surname" class="form-label">Cognome</label>
                    <input type="text" class="form-control" id="surname" value="${utente.cognome}" name="surname"
                           required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" value="${utente.email}" name="email" required>
                </div>
                <button type="submit" class="btn-outline-danger rounded-pill">Aggiorna</button>
            </form>
        </div>
        <div class="col-md-6">
            <form class="border border-dark px-4 py-3 mt-5 margini" method="POST"
                  action="<%=request.getContextPath()%>/user/cambiapassword">
                <h5 class="title">Cambio password</h5>
                <div class="mb-3">
                    <label for="password-attuale" class="form-label">Password Attuale:</label>
                    <input type="password" class="form-control" id="password-attuale" name="password-attuale" required>
                </div>
                <div class="mb-3">
                    <label for="new-password" class="form-label">Nuova password</label>
                    <input type="password" class="form-control" id="new-password" name="new-password" required>
                </div>
                <div class="mb-3">
                    <label for="confirm-password" class="form-label">Conferma nuova password</label>
                    <input type="password" class="form-control" id="confirm-password" name="confirm-password" required>
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