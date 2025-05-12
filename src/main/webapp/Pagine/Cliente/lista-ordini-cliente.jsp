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
<%@ include file = "../header.jsp" %>
<section class="container-fluid">
	  		<h2>I miei ordini</h2>
			<div class="order">
		    <h4>Ordine #123456</h4>
	  		<p>Stato: Ordinato</p>
		    <p>Ordine effettuato il: 2023-06-22</p>
		    <p>per: Luca Verdi</p>
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
		    
		    <button class="cancel-button">Annulla Ordine</button>
		    </div>
			</div>
</section>


<%@ include file = "../footer.jsp" %>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
	</html>
