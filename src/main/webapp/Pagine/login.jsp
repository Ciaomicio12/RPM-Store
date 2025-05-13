<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

<%@ include file="header.jsp" %>

<section class="container-fluid">
    <div class="row">
        <form class="border border-dark px-4 py-3 mx-4 mx-sm-auto mt-5 col-sm-6" method="POST" action="login">
            <h2 class="title">Accedi</h2>
            <div class="border border-dark px-4 py-3 mx-1 mx-sm-auto mt-1 col-sm-6">
                <h3> ${errorserverlogin}</h3>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Inserisci email"
                           required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="Inserisci password" required>
                </div>
            </div>
            <div class="mx-auto" style="width: 100px;">
                <button type="submit" class="btn btn-primary">Accedi</button>
            </div>
        </form>
    </div>
    <div class="mx-auto" style="width: 300px;">
        <h6>Sei nuovo su Record Road? <a href="registrazione">Registrati</a></h6>
    </div>
</section>


<%@ include file="footer.jsp" %>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>