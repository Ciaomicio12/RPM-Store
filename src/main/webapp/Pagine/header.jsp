<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@taglib prefix="c"
		  uri="http://java.sun.com/jsp/jstl/core" %>

<header class="container-fluid bg-light">
	<div class="container-fluid">
		<div class="row align-items-center">
			<a class="display-2 col" href="">
				<h1 class="text-decoration-none text-dark fw-bold">
					<img src="img/logo.png" alt="Logo"/>Record Road</h1>
			</a>
			<div class="w-100 d-md-none"></div>
			<form class="col d-flex my-0 my-md-3">
				<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
		</div>
	</div>

	<nav class="container-fluid navbar navbar-expand-sm navbar-light bg-light">
		<button class="navbar-toggler mt-2 mx-auto mt-sm-0" type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarContent"
				aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="row collapse navbar-collapse" id="navbarContent">
			<ul class="navbar-nav mb-2 mb-sm-0 text-center d-flex flex-column flex-sm-row justify-content-evenly">
				<c:if test="${utente != null}">
					<c:if test="${utente.admin}">
						<li class="nav-item justify-content-center">
							<a class="nav-link" href="dati-personali-amministratore">Dati personali</a>
						</li>
					</c:if>
					<c:if test="${!utente.admin}">
						<li class="nav-item justify-content-center">
							<a class="nav-link" href="dati-personali-cliente">Dati personali</a>
						</li>
					</c:if>
				</c:if>
				<c:if test="${utente == null}">
					<li class="nav-item justify-content-center">
						<a class="nav-link" href="#">Assistenza</a>
					</li>
				</c:if>
				<c:if test="${utente != null}">
					<c:if test="${!utente.admin}">
						<li class="nav-item justify-content-center">
							<a class="nav-link" href="#">Assistenza</a>
						</li>
					</c:if>
				</c:if>
				<c:if test="${utente != null}">
					<c:if test="${!utente.admin}">
						<li class="nav-item justify-content-center">
							<a class="nav-link" href="lista-ordini-cliente">I miei ordini</a>
						</li>
						<li class="nav-item justify-content-center">
							<a class="nav-link" href="#">Carrello</a>
						</li>
					</c:if>
					<c:if test="${utente.admin}">
						<li class="nav-item justify-content-center">
							<a class="nav-link" href="lista-ordini-amministratore">Elenco ordinazioni</a>
						</li>
					</c:if>
				</c:if>
				<c:if test="${utente == null}">
					<li class="nav-item justify-content-center">
						<a class="nav-link" href="login">LogIn/Registrazione</a>
					</li>
				</c:if>
				<c:if test="${utente != null}">
					<c:if test="${utente.admin}">
						<li class="nav-item justify-content-center">
							<a class="nav-link" href="inventario">Inventario</a>
						</li>
					</c:if>
					<li class="nav-item justify-content-center">
						<a class="nav-link" href="logout">LogOut</a>
					</li>
				</c:if>
			</ul>
		</div>
	</nav>
</header>