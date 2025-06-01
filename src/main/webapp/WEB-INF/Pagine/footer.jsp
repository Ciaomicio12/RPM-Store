<footer class="container-fluid bg-light overflow-hidden px-0">
    <div class="barBottom row align-items-center">
        <div class="w-100 d-sm-none"></div>
        <div class="col">
            <ul class="d-flex nav justify-content-evenly">
                <c:if test="${utente != null}">
                    <li><p class="fut" onclick="location.href = '<%= request.getContextPath() %>/user/profilo'">Dati
                        personali</p></li>
                </c:if>
                <c:if test="${(utente != null && !utente.admin) || utente == null}">
                    <li><p class="fut" onclick="location.href = '<%= request.getContextPath() %>/assistenza'">Assistenza</p>
                    </li>
                </c:if>
                <c:if test="${utente != null}">
                    <c:if test="${!utente.admin}">
                        <p class="fut" onclick="location.href = '<%= request.getContextPath() %>/lista-ordini-cliente'">
                            I miei
                            ordini</p>
                    </c:if>
                </c:if><c:if test="${utente != null}">
                <c:if test="${utente.admin}">
                    <li><p class="fut"
                           onclick="location.href = '<%= request.getContextPath() %>/lista-ordini-amministratore'">
                        Elenco
                        ordinazioni</p></li>
                    <li><p class="fut" onclick="location.href = '<%= request.getContextPath() %>/inventario'">
                        Inventario</p></li>
                </c:if>
            </c:if>
                <c:if test="${utente == null}">
                    <li><p class="fut" onclick="location.href = '<%= request.getContextPath() %>/login'">
                        LogIn/Registrazione</p></li>
                </c:if>
                <c:if test="${utente != null}">
                    <c:if test="${!utente.admin}">
                        <li><p class="fut" onclick="location.href = '<%= request.getContextPath() %>/carrello'">
                            Carrello</p></li>
                    </c:if>
                    <li><p class="fut" onclick="location.href = '<%= request.getContextPath() %>/user/logout'">
                        LogOut</p></li>
                </c:if>
            </ul>
        </div>
    </div>
</footer>