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
<%@ include file = "header.jsp" %>
<h2>Dettaglio ordine</h2>

<!--

<section class="container-fluid">
    <div class="row">
        <div class="col-md-6">
            <form class="border border-dark px-4 py-3 mt-5" method="GET" action="DettagliOrdineServlet">
                <h2 class="title"><strong>Indirizzo di spedizione</strong></h2>
                <div class="mb-3">
                	<c:if test="${utente != null}">
						<c:if test="${utente.admin}">
							<h5><strong>Acquirente: </strong>${ordine.acquirente.nome} ${ordine.acquirente.cognome}</h5>
						</c:if>
					</c:if>
                    <h5>${ordine.nome} ${ordine.cognome}</h5>
                    <h5>${ordine.via} ${ordine.numero-civico}</h5>
                    <h5>${ordine.città}</h5>
                    <h5>${ordine.provincia}</h5>
                    <h5>${ordine.codice-postale}</h5>
                </div>
            </form>
        </div>
        <div class="col-md-6">
            <form class="border border-dark px-4 py-3 mt-5" method="GET" action="DettagliOrdineServlet">
				<h2 class="title"><strong>Ordine:</strong> ${ordine.stato}</h2>
					<div class="mb-3">
						<c:forEach items="${ordine}" var="ordine">
						   <c:forEach items="${ordine.vinile}" var="vinile">
								<img src="img/Cover/${ordine.vinile.copertina}" alt="Copertina Album" class="img-fluid">
								<h5>${ordine.vinile.numero-copie}x ${ordine.vinile.prezzo-acquisto}&euro;</h5>
								<h5>${ordine.vinile.titolo}</h5>
								<h5>${ordine.vinile.artista}</h5>
						   </c:forEach>
						</c:forEach>
					</div>
					<button type="submit" class="btn btn-primary" name="azione" value="annulla-ordine">Annulla ordine</button>
					<c:if test="${utente != null}">
						<c:if test="${utente.admin}">
							<button type="submit" class="btn btn-primary" name="azione" value="spedisci-ordine">Segna come spedito</button>
						</c:if>
					</c:if>
                </form>
        </div>
    </div>
</section>

-->

			<div class="order">
		    <h4>Ordine #123456</h4>
	  		<p>Stato: Ordinato</p>
		    <p>Ordine effettuato il: 2023-06-22</p>
		    <p>per: Luca Verdi</p>
		    <!-- Aggiungere indirizzo di spedizione -->
		    <p>60.50€</p>
		    
		    <div class="order-items">
		      <div class="order-item">
		        <img src="C:\Users\User\Desktop\Esercizi C - C++\Tecnologie Software per il Web\Progetto\Album\Elvis Presley.jpg" alt="Copertina Vinile 1">
		        <p>Elvis Presley</p>
		        <p>Elvis Presley</p>
		        <p>21.51€</p>
		      </div>
		      
		      <div class="order-item">
		        <img src="C:\Users\User\Desktop\Esercizi C - C++\Tecnologie Software per il Web\Progetto\Album\Sgt. Pepper's Lonely Hearts Club Band.jpg" alt="Copertina Vinile 2">
		        <p>Sgt. Pepper's Lonely Hearts Club Band</p>
		        <p>The Beatles</p>
		        <p>38.99€</p>
		      </div>		    
		    <button class="change-status">Segna come spedito</button>
		    <button class="cancel-button">Annulla Ordine</button>
		    </div>

				<%@ include file = "footer.jsp" %>
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
						integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
						crossorigin="anonymous"></script>
</body>

	</html>
