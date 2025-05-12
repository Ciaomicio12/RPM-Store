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
<h2>Creazione nuovo prodotto</h2>
<section class="container">
  <div class="random-albums my-3" class="row">
          <div class="album col-12 row my-5 my-sm-4 mx-auto">
            <div class="col-12 col-sm-3 mx-auto" >
              <form method="POST" action="dati_vinili_servlet" enctype="multipart/form-data">
              
                <img src="../../Album/7126739-punto-interrogativo-icona-vettore-gratuito-vettoriale.jpg" alt="Copertina album">
                <label for="file-upload">Inserire nuova copertina:</label>
                <input type="file" id="file-upload" name="file-upload" accept="image/*">
                <label for="title">Titolo</label>
                <input type="text" id="title" name="title" required/>

                <label for="artist">Artista</label>
                <input type="text" id="artist" name="artist" required/>

                <label for="genre">Genere</label>
                <input type="text" id="genre" name="genre" required/>

                <label for="year">Anno</label>
                <input type="number" id="year" name="year" required/>

                <label for="code">EAN</label>
                <input type="text" id="code" name="code" required/>

                <label for="price">Prezzo</label>
                <input type="number" id="price" name="price" required/>

                <label for="stock">Quantità</label>
                <input type="number" min="1" value="1" name="stock" class="quantity-input" id="stock"/>
                <button type="submit" value="aggiungi-prodotto" name="azione">Salva album nell'inventario</button>
              </form>
            </div>
          </div>
  </div>
</section>

</body>
</html>