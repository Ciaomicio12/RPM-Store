<%@ page import="java.util.List" %>
<%@ page import="com.progetto.viniliprogetto.Model.Vinile" %>
<%
    List<Vinile> vinili = (List<Vinile>) request.getAttribute("vinili");
%>

<%= vinili %>

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
<section class="container">
  <div class="random-albums my-3" class="row">
          <div class="album col-12 row my-5 my-sm-4 mx-auto">
            <div class="col-9 col-sm-3 mx-auto" >
              <a href="dettaglio-album.html">
                <img src="C:\Users\User\Desktop\Esercizi C - C++\Tecnologie Software per il Web\Progetto\Album\Significant Other.jpg" alt="Copertina Album 1" class="img-fluid" >
              </a>
            </div>
            <div class="information col-12 col-sm-9">
              <h2 class="display-3"><a href="dettaglio-album.html">Significant Other</a></h2>
              <h3 class="display-5 text-muted"><em>Limp Bizkit</em></h3>
              <h3 class="display-6"><strong>33.13&euro;</strong></h3>
            </div>
          </div>

          <div class="album col-12 row my-5 my-sm-4 mx-auto">
            <div class="col-9 col-sm-3 mx-auto" >
              <a href="dettaglio-album.html">
                <img src="C:\Users\User\Desktop\Esercizi C - C++\Tecnologie Software per il Web\Progetto\Album\Appetite for Destruction.jpg" alt="Copertina Album 1" class="img-fluid" >
              </a>
            </div>
            <div class="information col-12 col-sm-9">
              <h2 class="display-3"><a href="dettaglio-album.html">Appetite for Destruction</a></h2>
              <h3 class="display-5 text-muted"><em>Guns N' Roses</em></h3>
              <h3 class="display-6"><strong>37.68&euro;</strong></h3>
            </div>
          </div>
          
          <!--<div class="album">
            <img src="C:\Users\User\Desktop\Esercizi C - C++\Tecnologie Software per il Web\Progetto\Album\Appetite for Destruction.jpg" alt="Copertina Album 2">
            <p>Appetite for Destruction</p>
            <p>Guns N' Roses</p>
            <p>37.68€</p>
          </div>
        -->

        <div class="album col-12 row my-5 my-sm-4 mx-auto">
            <div class="col-9 col-sm-3 mx-auto" >
              <a href="dettaglio-album.html">
                <img src="C:\Users\User\Desktop\Esercizi C - C++\Tecnologie Software per il Web\Progetto\Album\The Final Countdown.jpg" alt="Copertina Album 1" class="img-fluid" >
              </a>
            </div>
            <div class="information col-12 col-sm-9">
              <h2 class="display-3"><a href="dettaglio-album.html">The Final Countdown</a></h2>
              <h3 class="display-5 text-muted"><em>Europe</em></h3>
              <h3 class="display-6"><strong>35.14&euro;</strong></h3>
            </div>
          </div>
          
          <!--<div class="album">
            <img src="C:\Users\User\Desktop\Esercizi C - C++\Tecnologie Software per il Web\Progetto\Album\The Final Countdown.jpg" alt="Copertina Album 3">
            <p>The Final Countdown</p>
            <p>Europe</p>
            <p>35.14€</p>
          </div>
          -->
  </div>
</section>


<%@ include file = "footer.jsp" %>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>