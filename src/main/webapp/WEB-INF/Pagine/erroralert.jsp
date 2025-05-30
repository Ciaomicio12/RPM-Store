<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<html>
<head>
    <title>Record Road - Errore</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/style.css"/>
</head>
</html>
<%@include file="header.jsp" %>
<section class="container" style="min-height: 50vh">
    <div class="row">
        <div class="leftcolumn">
            <div class="card">
                <h2><%= exception.getMessage() %>
                </h2>
            </div>
            <div class="card" id="homediv">
                <a href="${pageContext.request.contextPath}" id="home">Torna alla home</a>
            </div>
        </div>
    </div>
</section>
<%@include file="footer.jsp" %>