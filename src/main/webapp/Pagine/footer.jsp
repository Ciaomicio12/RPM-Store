<footer class="container-fluid bg-light fixed-bottom overflow-hidden">
    <div class="barBottom row align-items-center">
        <div class="w-100 d-sm-none"></div>
        <div class="col">
            <ul class="d-flex nav justify-content-evenly">
                <li><a class="textLI" href="#">Assistenza</a></li>
                <li><a class="textLI" href="#">Carrello</a></li>
                <c:if test="${utente == null}">
                    <li><a class="textLI" href="login">Login/Registrazione</a></li>
                </c:if>
                <c:if test="${utente != null}">
                    <c:if test="${utente.admin}">
                        <li><a class="textLI" href="inventario">Inventario</a></li>
                    </c:if>
                    <li><a class="textLI" href="logout">Logout</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</footer>