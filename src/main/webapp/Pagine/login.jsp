<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Record Road - LogIn</title>
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
        <form class="px-4 py-3 mx-auto mt-5 col-sm-6 col-md-8" method="POST" action="login"
              style="margin-bottom: 100px;">
            <h2 class="title">Accedi</h2>
            <div class="px-4 py-3 mx-1 mx-sm-auto mt-1 col-sm-6">
                <h3> ${errorserverlogin}</h3>
                <div class="form-group">
                    <label for="email">Email</label>
                    <div></div>
                    <input type="email" class="textForm" id="email" name="email" placeholder="Inserisci email"
                           required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <div></div>
                    <input type="password" class="textForm" id="password" name="password"
                           placeholder="Inserisci password" required>
                </div>
            </div>
            <div class="mx-auto" style="width: 100px;">
                <button type="submit" class="btn-outline-danger rounded-pill">Accedi</button>
            </div>
        </form>
    </div>
    <div class="text-center">
        <h6><a href="registrazione" class="regLink">Sei nuovo su Record Road? Registrati</a></h6>
    </div>
</section>


<%@ include file="footer.jsp" %>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>