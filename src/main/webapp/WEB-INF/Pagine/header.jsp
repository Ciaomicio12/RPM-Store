<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>

<header class="container-fluid bg-red">
    <div class="container-fluid pt-3">
        <div class="row align-items-center">
            <a class="display-2 col d-flex align-items-center" href="<%= request.getContextPath() %>/">
                <img class="logo"
                     style="   .transparent { zoom: 1;filter: alpha(opacity=50);opacity: 0.5;} width: 100px; height: 100px; margin-right: 10px;"
                     src="<%= request.getContextPath() %>/img/logo.png" alt="Logo"/>
                <h1 class="text-decoration-none text-dark fw-bold titleHeader">Record Road</h1>
            </a>
            <div class="w-100 d-md-none"></div>
            <form class="col d-flex my-0 my-md-3" method="get" action="<%= request.getContextPath() %>/cerca">
                <input class="form-control me-2" list="ricerca-datalist" name="ricerca" type="search"
                       onkeyup="ricercaajax(this.value)" placeholder="Search" aria-label="Search" required>
                <datalist id="ricerca-datalist"></datalist>
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
            <script>function ricercaajax(str) {
                var dataList = document.getElementById('ricerca-datalist');
                if (str.length == 0) {
                    dataList.innerHTML = '';
                    return;
                }

                var xmlHttpReq = new XMLHttpRequest();
                xmlHttpReq.responseType = 'json';
                xmlHttpReq.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {

                        dataList.innerHTML = '';

                        for (var i in this.response) {

                            var option = document.createElement('option');

                            option.value = this.response[i];

                            dataList.appendChild(option);
                        }
                    }
                }
                xmlHttpReq.open("GET", "<%= request.getServletContext().getContextPath() %>/ricercaajax?q=" + encodeURIComponent(str), true);
                xmlHttpReq.send();
            }</script>
        </div>
    </div>

    <nav class="container-fluid navbar navbar-expand-sm navbar-light bg-red">
        <button class="navbar-toggler mt-2 mx-auto mt-sm-0" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarContent"
                aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="row collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav mb-2 mb-sm-0 text-center d-flex flex-column flex-sm-row justify-content-evenly">
                <c:if test="${utente != null}">
                    <li class="nav-item justify-content-center">
                        <a class="nav-link" href="<%= request.getContextPath() %>/user/profilo">Dati personali</a>
                    </li>
                </c:if>
                <c:if test="${(utente != null && !utente.admin) || utente == null}">
                    <li class="nav-item justify-content-center">
                        <a class="nav-link" href="<%= request.getContextPath() %>/assistenza">Assistenza</a>
                    </li>
                </c:if>
                <c:if test="${utente != null}">
                    <c:if test="${!utente.admin}">
                        <li class="nav-item justify-content-center">
                            <a class="nav-link" href="<%= request.getContextPath() %>/cliente/ordini">I miei
                                ordini</a>
                        </li>
                        <li class="nav-item justify-content-center">
                            <a class="nav-link" href="<%= request.getContextPath() %>/cliente/carrello">Carrello</a>
                        </li>
                    </c:if>
                    <c:if test="${utente.admin}">
                        <li class="nav-item justify-content-center">
                            <a class="nav-link" href="<%= request.getContextPath() %>/admin/gestisciordini">Elenco
                                ordinazioni</a>
                        </li>
                    </c:if>
                </c:if>
                <c:if test="${utente == null}">
                    <li class="nav-item justify-content-center">
                        <a class="nav-link" href="<%= request.getContextPath() %>/login">LogIn/Registrazione</a>
                    </li>
                </c:if>
                <c:if test="${utente != null}">
                    <c:if test="${utente.admin}">
                        <li class="nav-item justify-content-center">
                            <a class="nav-link" href="<%= request.getContextPath() %>/admin/inventario">Inventario</a>
                        </li>
                    </c:if>
                    <li class="nav-item justify-content-center">
                        <a class="nav-link" href="<%= request.getContextPath() %>/logout">LogOut</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </nav>
</header>

