<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style><%@ include file="lib/css/css/bootstrap.min.css" %></style>
<style><%@ include file="lib/css/css/bootstrap-colorpalette.css" %></style>
<style><%@ include file="lib/css/css/calendar.css" %></style>


<script type="text/javascript"><%@include file="lib/css/js/jquery.js" %></script>
<script type="text/javascript"><%@include file="lib/css/js/bootstrap.min.js" %></script>
<script type="text/javascript"><%@include file="lib/css/js/bootstrap-colorpalette.js" %></script>

<title>MyTodo</title>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a href="visualisation" class="navbar-brand">My Todo</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse navbar-right">
				<a href="modifyCompte" class="btn btn-default navbar-btn" role="button">Modifier son compte</a>
				<a href="deconnexion" class="btn btn-default navbar-btn" role="button">Déconnexion</a>
			</div>
			<!-- /.navbar-collapse -->
		</div>		<!-- /.container-fluid --> 
		</nav>

		<div class="col-md-12">
		<div class="col-md-3 well">
			<%-- Vérification de la présence d'un objet utilisateur en session --%>
			<c:if test="${!empty sessionScope.sessionUtilisateur}">
				<%-- Si l'utilisateur existe en session, alors on affiche son login. --%>
				<p class="succes">Vous êtes connecté(e) avec le login :
					${sessionScope.sessionUtilisateur.login}</p>
			</c:if>
			
	
			
			


			<!-- -------------------------------- -->
			
			<ul class="nav nav-pills nav-stacked" id="visuTab">
			  <li ${page == 'visu' ? ' class="active"' : ''}><a href="#visualisation">Visualisation des Todos</a></li>
			  <li ${page == 'createTodo' ? ' class="active"' : ''}><a href="#creertodo">Créer un Todo</a></li>
			  <li ${page == 'createContext' ? ' class="active"' : ''}><a href="#creercontext">Créer un Contexte</a></li>
			  <li ${page == 'synchro' ? ' class="active"' : ''}><a href="#synchro">Synchroniser avec GCalendar</a></li>
			</ul>
		</div>

		<div class="col-md-offset-1 col-md-8 well">
			<div class="tab-content">
				<div class="tab-pane ${page == 'visu' ? ' active' : ''}" id="visualisation"><%@ include file="visuTodoForm.jsp" %></div>
				<div class="tab-pane ${page == 'createTodo' ? ' active' : ''}" id="creertodo"><%@ include file="createTodo.jsp" %></div>
				<div class="tab-pane ${page == 'createContext' ? ' active' : ''}" id="creercontext"><%@ include file="createContext.jsp" %></div>
				<div class="tab-pane ${page == 'synchro' ? ' active' : ''}" id="synchro"><%@ include file="synchronisation.jsp" %></div>
			</div>	

		</div>
	</div>
	</div>
	<c:if test="${empty sessionScope.sessionUtilisateur}">
			==================== /!\ ======================<br />
			Veuillez vous connecter pour visualiser vos TODOs.<br />
			==================== /!\ ======================<br />
	</c:if>

</body>

<script type="text/javascript">
$('#visuTab a').click(function (e) {
	  e.preventDefault()
	  $(this).tab('show')
	})
</script>
</html>
