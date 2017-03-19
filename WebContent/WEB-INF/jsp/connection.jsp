<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% response.addHeader("Refresh","60"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/bootstrap-3.3.7/dist/css/bootstrap.min.css" var="css1" />
<spring:url value="/resources/js/jquery-3.1.1.min.js" var="jqueryjs" />
<spring:url value="/resources/js/script_login.js" var="scriptjs" />

<link href="${css1}" rel="stylesheet" />
<script type="text/javascript" src="${jqueryjs}"></script>
<script type="text/javascript" src="${scriptjs}"></script>

<script src="/resources/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>



<title>Connection</title>
</head>
<body>

<div id="connection">
Server: ${statusDB}<br>
Url: ${urlDB}<br>
Database User: ${usernameDB}<br>
Last connection attempt: ${lastCheckedDB}<br>
</div>		   
</body>
</html>