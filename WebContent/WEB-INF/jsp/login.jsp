<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<!-- DEPRECATED TO BE REMOVED<spring:url value="/resources/styles_login.css" var="css1" />-->
<spring:url value="/resources/bootstrap-3.3.7/dist/css/bootstrap.min.css" var="css1" />

<spring:url value="/resources/images" var="images" />
<spring:url value="/resources/js/jquery-3.1.1.min.js" var="jqueryjs" />
<spring:url value="/resources/js/script_login.js" var="scriptjs" />

<link href="${css1}" rel="stylesheet" />
<script type="text/javascript" src="${jqueryjs}"></script>
<script type="text/javascript" src="${scriptjs}"></script>

<script src="resources/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

</head>
<body>
<div class="jumbotron text-center">
	Language : <a href="?language=en">English</a>|<a href="?language=fr">Français</a>
	
  <h1>iTunes Clone</h1>
  <p>Amine Waddah | Chou Khorng Trang | Georges Youssef | Ho Jia Jie | Nour El Moussaoui | Tony Duong</p>  
  
     Current Locale : ${pageContext.response.locale}
</div>

<div class="container">	
	<form method="get" id="account-form"">
		<div class="form-group">
		    <label for = "email"><spring:message code="label.email" text="default text" />:</label> 
		    <input id="email" type="text" class="form-control" name="email" placeholder="<spring:message code="placeholder.email" text="default text" />">
	  	</div>
		  
		<div class="form-group">
		    <label for="pwd"><spring:message code="label.pwd" text="default text" />:</label>
		    <input id="password" type="password" class="form-control" name="password" placeholder="<spring:message code="placeholder.pwd" text="default text" />">
		</div>
		<div class="alert alert-danger" id="message" style="display:none;"></div>

		  <button id="submit" type="submit" class="btn btn-default"><spring:message code="label.submit" text="default text" /></button>
	</form>
	<p>
	<div class="container2">
		  
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
	
	
	

	
	
</body>
</html>