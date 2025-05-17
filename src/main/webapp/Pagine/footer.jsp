<footer class="container-fluid bg-light overflow-hidden">
    <div class="barBottom row align-items-center">
        <div class="w-100 d-sm-none"></div>
        <div class="col">
            <ul class="d-flex nav justify-content-evenly">
                <c:if test="${utente != null}">
                    <li><a class="nav-link" href="<%= request.getContextPath() %>/profilo">Dati personali</a></li>
                </c:if>
                <li><a class="nav-link" href="<%= request.getContextPath() %>/assistenza">Assistenza</a></li>
                <c:if test="${utente != null}">
                    <c:if test="${!utente.admin}">
                        <a class="nav-link" href="<%= request.getContextPath() %>/lista-ordini-cliente">I miei
                            ordini</a>
                    </c:if>
                </c:if><c:if test="${utente != null}">
                <c:if test="${utente.admin}">
                    <li><a class="nav-link" href="<%= request.getContextPath() %>/lista-ordini-amministratore">Elenco
                        ordinazioni</a></li>
                    <li><a class="nav-link" href="<%= request.getContextPath() %>/inventario">Inventario</a></li>
                </c:if>
            </c:if>
                <c:if test="${utente == null}">
                    <li><a class="nav-link" href="<%= request.getContextPath() %>/login">LogIn/Registrazione</a></li>
                </c:if>
                <c:if test="${utente != null}">
                    <c:if test="${!utente.admin}">
                        <li><a class="nav-link" href="<%= request.getContextPath() %>/carrello">Carrello</a></li>
                    </c:if>
                    <li><a class="nav-link" href="<%= request.getContextPath() %>/user/logout">LogOut</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</footer>