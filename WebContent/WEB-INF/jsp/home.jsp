<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<spring:url
	value="/resources/bootstrap-3.3.7/dist/css/bootstrap.min.css"
	var="css1" />
<spring:url value="/resources/styles_home.css" var="css2" />

<spring:url value="/resources/images" var="images" />
<spring:url value="/resources/js/jquery-3.1.1.min.js" var="jqueryjs" />
<spring:url value="/resources/js/script.js" var="scriptjs" />
<spring:url value="/resources/js/script_download.js" var="downloadjs" />

<link href="${css1}" rel="stylesheet" />
<link href="${css2}" rel="stylesheet" />
<script type="text/javascript" src="${jqueryjs}"></script>
<script type="text/javascript" src="${scriptjs}"></script>
<script type="text/javascript" src="${downloadjs}"></script>

<script src="resources/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><spring:message code="nav.site"
					text="ItunesClone" /></a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li class="active"><a href="home"><spring:message
							code="nav.home" text="Accueil" /></a></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="cart"><span
						class="	glyphicon glyphicon-shopping-cart"></span> <spring:message
							code="nav.cart" text="Panier" /></a></li>
				<li><a href="emprunts"><span
						class="	glyphicon glyphicon-film"></span> <spring:message
							code="nav.rent" text="Emprunt" /></a></li>
				<li><a href="account"><span
						class="glyphicon glyphicon-user"></span> <spring:message
							code="nav.message" text="Bonjour" />, ${nom}!</a></li>
				<li><a href="login"><span
						class="glyphicon glyphicon-log-out"></span> <spring:message
							code="nav.logout" text="Déconnexion" /></a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid text-center">
		<div class="row content">

			<div class="col-sm-2 sidenav">
				<div id="criterias-div">
					<ul class="nav nav-tabs">
						<li class="dropdown"><a data-toggle="dropdown"
							class="btn btn-default dropdown-toggle" href="#"><spring:message
									code="filter.genre" text="Genre" /> <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a data-toggle="tab" href="#genres"><spring:message
											code="filter.genre" text="Genre" /></a></li>
								<li><a data-toggle="tab" href="#Pays"><spring:message
											code="filter.pays" text="Pays" /></a></li>
								<li><a data-toggle="tab" href="#Langues"><spring:message
											code="filter.langue" text="langue" /></a></li>
								<li><a data-toggle="tab" href="#Temps"><spring:message
											code="filter.temps" text="Durée" /></a></li>
								<li><a data-toggle="tab" href="#Recherche"><spring:message
											code="filter.recherche" text="Recherche par contenu" /></a></li>
							</ul></li>
					</ul>
					<div id="criterias-checkboxes">
						<div class="tab-content">

							<div id="genres" class="tab-pane fade in active">
								<h3>Genres</h3>
								<c:forEach var="genre" items="${listGenre}">
									<input type="checkbox" name="genre-checkbox"
										value="${genre.id}">
									<c:out value="${genre.nom}" />
									<br>
								</c:forEach>
							</div>

							<div id="Pays" class="tab-pane fade">
								<h3>Pays</h3>
								<c:forEach var="pays" items="${listPays}">
									<input type="checkbox" name="pays-checkbox" value="${pays.id}">
									<c:out value="${pays.nom}" />
									<br>
								</c:forEach>
							</div>

							<div id="Langues" class="tab-pane fade">
								<h3>Langues</h3>
								<c:forEach var="langue" items="${listLangue}">
									<input type="checkbox" name="langue-checkbox"
										value="${langue.id}">
									<c:out value="${langue.nom}" />
									</br>
								</c:forEach>
							</div>

							<div id="Temps" class="tab-pane fade">
								<h3>
									<spring:message code="filter.temps" text="Durée" />
								</h3>
								<label for="min">Min:</label><input type="number" name="min"
									min="1" max="300" /><br> <label for="max">Max:</label><input
									type="number" name="max" min="1" max="300" />

							</div>
							
							<div id="Recherche" class="tab-pane fade">
							   <h3>Recherche par contenu</h3>						
							</div>
													
							
						</div>
					</div>
				</div>
			</div>

			<div class="col-sm-8 text-left">
				<div id="search-bar-div">
					<p>
					<form id="search-form">
						<div class="form-group">
							<input id="search-bar-input" type="text" name="search"
								class="form-control"
								placeholder="<spring:message code="placeholder.home" text="Recherche par nom de film, acteur, année..." />">
							<button id="search-bar-submit" type="submit"
								class="btn btn-default">
								<spring:message code="label.search" text="Rechercher" />
							</button>
						</div>
					</form>
				</div>
				<form id="upload-form" enctype="multipart/form-data">
					<spring:message code="label.download" var="ld" text="Télécharger json" />
					<input type="file" name="file" id="file" /> <input
						id="upload-file-submit" type="submit" value="Upload File" />
				</form>

				<h1>
					<spring:message code="title.home" text="Résultat de la recherche" />
				</h1>

				<div class="container-fluid bg-3 text-center">
					<div id="result-search-div" class="row"></div>
				</div>

				<hr>
				<h3>
					<spring:message code="label.help" text="Aide" />
				</h3>
				<p>
					<spring:message code="help.home" text="Il est possible de rechercher pour un film ou une personne dans la barre de recherche. Utilisez le filtre, afin de spécifier des contraintes." />
				</p>

			</div>

			<div class="col-sm-2 sidenav">
				<button type="button" class="btn btn-info" data-toggle="collapse"
					data-target="#dbinfo">Server</button>
				<div id="dbinfo" class="collapse in">
					<IFRAME frameborder="0"
						style="width: 100%; height: 100%; border: margin: 0px;"
						id="filterIFrame" name="filterIFrame" scrolling="no"
						src="connection"> </IFRAME>
				</div>
			</div>


		</div>
	</div>

	<footer class="container-fluid text-center">
	<p>Copyright 2017-2020 by Equipe5. All Rights Reserved.</p>
	</footer>

</body>
</html>