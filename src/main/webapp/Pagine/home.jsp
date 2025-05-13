<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>

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
<jsp:include page="header.jsp"></jsp:include>
<section class="container">
    <div class="random-albums my-3" class="row">
        <c:forEach items="${vinili}" var="vinile">
            <div class="album col-12 row my-5 my-sm-4 mx-auto">
                <div class="col-9 col-sm-3 mx-auto">
                    <a href="dettaglio-album.html">
                        <img src="img/Cover/${vinile.copertina}" alt="Copertina Album 1"
                             class="img-fluid">
                    </a>
                </div>
                <div class="information col-12 col-sm-9">
                    <h2 class="display-3"><a href="vinile?ean=${vinile.ean}">${vinile.titolo}</a></h2>
                    <h3 class="display-5 text-muted"><em>${vinile.autore}</em></h3>
                    <h3 class="display-6"><strong>${vinile.prezzo}&euro;</strong></h3>
                </div>
            </div>
        </c:forEach>
    </div>
</section>


<%@ include file = "footer.jsp" %>

</body>
</html>