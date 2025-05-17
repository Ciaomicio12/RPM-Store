<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${ricerca}"/>
</jsp:include>
<div class="card ricerca_mobile">
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>Risulati per la parola chiave: ${ricerca}</h2>
        </div>
        <c:if test="${vinili.size() == 0}">
            <div class="card">
                <h3>Non ci sono vinili con la parola chiave cercata</h3>
            </div>
        </c:if>
        <c:if test="${vinili.size() > 0}">
            <c:forEach items="${vinili}" var="vinile">
                <div class="card vinile">
                    <img src="img/Cover/${vinile.copertina}" alt="vinile" height="215px" class="image"/>
                    <div class="vinile">
                        <h3>
                                ${vinile.titolo}
                        </h3>
                        <h4>
                                ${vinile.prezzo}
                        </h4>
                        <a href="vinile?ean=${vinile.ean}">Vai alla scheda tecnica</a>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
    <jsp:include page="header.jsp"/>
