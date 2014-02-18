<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
<%@include file="lib/css/css/bootstrap.min.css" %> 
</style>

<title>MyTodo</title>
</head>
<body>
<%@ include file="header.jsp" %>
 
	
	<div class="col-md-3">
		<%-- Vérification de la présence d'un objet utilisateur en session --%>
	<c:if test="${!empty sessionScope.sessionUtilisateur}">
		<%-- Si l'utilisateur existe en session, alors on affiche son login. --%>
		<p class="succes">Vous êtes connecté(e) avec le login :
			${sessionScope.sessionUtilisateur.login}</p>
	</c:if>
		<a href="createTodo" class="btn btn-primary">Créer un TODO</a>
	</div>
	<div class="col-md-9">
	Bienvenue dans la visualisation de vos Todo !!


	<br />

	<a href="<c:url value="/synchronisation">
				</c:url>">Synchronisation
		avec google Calendar.</a>
	
<%@ include file="footer.jsp" %>

</body>
</html>
