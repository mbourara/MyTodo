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
<div class="container">
	<nav class="navbar navbar-default" role="navigation">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	     	 <h1>My Todo</h1>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	 
	
	      <ul class="nav navbar-nav navbar-right">
	       	 <li>
				<a href="Deconnexion" class="btn btn-default" role="button">Déconnection</a>
	       	</li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
 
	
	<div class="col-md-3 well">
		<%-- Vérification de la présence d'un objet utilisateur en session --%>
	<c:if test="${!empty sessionScope.sessionUtilisateur}">
		<%-- Si l'utilisateur existe en session, alors on affiche son login. --%>
		<p class="succes">Vous êtes connecté(e) avec le login :
			${sessionScope.sessionUtilisateur.login}</p>
	</c:if>
		<div class="col-md-12"><a href="createTodo" class="btn btn-primary">Créer un TODO</a></div>
		<div class="col-md-12"><a href="synchronisation" class="btn btn-primary">Synchronisation Google Calendar</a></div>
	</div>
	<div class="col-md-8 col-md-offset-1 well">
	Bienvenue dans la visualisation de vos Todo !!


	<br />
	
</div>
</div>

</body>
</html>
