<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Demo</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <link rel="stylesheet" href="<%= request.getContextPath()%>/style.css"/>
</head>
<body>
<%@ include file="header.jsp" %>

<section class="container">
    <div class="row my-3">
        <div class="col">
            <div class="card">
                <h2>Risultati per la parola chiave: ${ricerca}</h2>
            </div>
            <c:if test="${vinili.size() == 0}">
                <div class="card">
                    <h3>Non ci sono vinili con la parola chiave cercata</h3>
                </div>
            </c:if>
            <c:if test="${vinili.size() > 0}">
                <c:forEach items="${vinili}" var="vinile">
                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 my-4">
                        <div class="card">
                            <a href="vinile?ean=${vinile.ean}">
                                <img src="img/Cover/${vinile.copertina}" class="card-img-top" alt="Copertina Album 1">
                            </a>
                            <div class="card-body">
                                <h5 class="card-title">${vinile.titolo}</h5>
                                <p class="card-text">${vinile.autore}</p>
                                <p class="card-text"><strong>${vinile.prezzo}&euro;</strong></p>
                                <a type="button" href="vinile?ean=${vinile.ean}" class="btn btn-outline-danger rounded-pill">Dettagli </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
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