<footer class="container-fluid bg-light fixed-bottom pb-4 pt-2 overflow-hidden">
    <div class="row align-items-center">
        <div class="col text-center mb-2 mb-sm-0">
            <h1 class="display-4"><img src="img/logo.png" alt="Logo"/><a class="text-decoration-none text-dark fw-bold"
                                                                         href="#">Record Road</a></h1>
        </div>
        <div class="w-100 d-sm-none"></div>
        <div class="col">
            <ul class="d-flex nav justify-content-evenly">
                <li><a href="#">Assistenza</a></li>
                <li><a href="#">Carrello</a></li>
                <c:if test="${utente == null}">
                    <li><a href="login">Login/Registrazione</a></li>
                </c:if>
                <c:if test="${utente != null}">
                    <c:if test="${utente.admin}">
                        <li><a href="inventario">Inventario</a></li>
                    </c:if>
                    <li><a href="logout">Logout</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</footer>