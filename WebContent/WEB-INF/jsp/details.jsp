<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${type}</title>
<spring:url value="/resources/bootstrap-3.3.7/dist/css/bootstrap.min.css" var="css1" />
<spring:url value="/resources/styles_home.css" var="css2" />


<spring:url value="/resources/images" var="images" />
<spring:url value="/resources/js/jquery-3.1.1.min.js" var="jqueryjs" />
<spring:url value="/resources/js/script_details.js" var="scriptjs" />
<spring:url value="/resources/js/moment.js" var="momentjs" />

<link href="${css1}" rel="stylesheet" />
<link href="${css2}" rel="stylesheet" />
<script type="text/javascript" src="${jqueryjs}"></script>
<script type="text/javascript" src="${scriptjs}"></script>
<script type="text/javascript" src="${momentjs}"></script>

<script src="resources/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
<a class="navbar-brand" href="#"><spring:message code="nav.site" text="default text" /></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="home"><spring:message code="nav.home" text="default text" /></a></li>

      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<li><a href="cart"><span class="	glyphicon glyphicon-shopping-cart"></span> <spring:message code="nav.cart" text="default text" /></a></li>
      	<li><a href="emprunts"><span class="	glyphicon glyphicon-film"></span> <spring:message code="nav.rent" text="default text" /></a></li>
      	<li><a href="account"><span class="glyphicon glyphicon-user"></span> <spring:message code="nav.message" text="default text" />, ${nom}!</a></li>
        <li><a href="login"><span class="glyphicon glyphicon-log-out"></span> <spring:message code="nav.logout" text="default text" /></a></li>
      </ul>
    </div>
  </div>
</nav>	


<div class="container-fluid text-center">    
  <div class="row content">
    
    <div class="col-sm-2 sidenav">
    </div>
    
    <div class="col-sm-8 text-left"> 	
      <h1><spring:message code="title.details" text="default text" /></h1>
		<hr>
		
		<div class="container-fluid bg-3 text-center"> 			
			
			<div class="container">
				<div class="row">
					<div id="poster" class="col-sm-8 text-center">
						<img src="${movie.poster}" height="300" width="220" />
					</div>	
				</div>
				
				<div class="row">
						<div class="row">		
						<div class="col-sm-10 text-left" id="titre"><h1>${movie.titre}</h1></div>						
						</div>
						<div class="row">
						<div class="col-sm-5 text-left" id="annee"><label><spring:message code="label.year" text="default text" /></label> ${movie.annee}</div>						
						</div>
						<div class="row">
						<div class="col-sm-5 text-left container-paragraph" id="duree"><label><spring:message code="label.length" text="default text" /></label> ${movie.duree} min</div>
						</div>
						<div class="row">
							<div class="col-sm-5 text-left container-paragraph text-justify" id="resume">${movie.resume}<p></div>
						</div>
						<div class="row">
							<div class="col-sm-5 text-left container-paragraph">
							
							<div>
							<label><spring:message code="label.actor" text="default text" /></label><br>
								<c:forEach var="acteur" items="${acteurs}">
									
									${acteur.nom}</br>
								</c:forEach>
							</div>
							
							<div>
							<p><p>
							<label><spring:message code="label.director" text="default text" /></label><br>
							<c:forEach var="realisateur" items="${realisateurs}">								
									
									${realisateur.nom}<br>
								
							</c:forEach>
							</div>
							
						</div>		
				</div>				
			</div>
					
			</div>
      <hr>
			<div>
				<label><spring:message code="label.start" text="default text" /></label> <input type="date" id="date-debut" >
				<label><spring:message code="label.end" text="default text" /></label> <input type="date" id="date-fin">				
			</div>
			<button id="buy-button" class="btn btn-default"><spring:message code="label.addtocart" text="default text" /></button>
			<div id="message-cout"></div>
			<div id="message"></div>
			</div>
			<hr>
      <h3><spring:message code="label.help" text="default text" /></h3>
      <p><spring:message code="help.details" text="default text" /></p>
    </div>
    
    <div class="col-sm-2 sidenav">  
    <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#dbinfo">Server</button>
		  <div id="dbinfo" class="collapse in">
		   		<IFRAME frameborder="0"
			    style="width: 100%; height: 100%; border: margin: 0px;"
			    id="filterIFrame" name="filterIFrame" scrolling="no"
			    src="connection">
				</IFRAME>
		  </div>
    </div>
  </div>
</div>

<footer class="container-fluid text-center">
  <p>Copyright 2017-2020 by Equipe5. All Rights Reserved.</p>
</footer>
</body>
</html>