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
		<div class="col-md-12"><a href="createTodo" class="btn btn-primary">Créer un TODO</a></div>
		<div class="col-md-12"><a href="synchronisation" class="btn btn-primary">Synchronisation Google Calendar</a></div>
	</div>
	<div class="col-md-9">

	Bienvenue dans la visualisation de vos Todo !!
	<br />
		<c:if test="${!empty sessionScope.sessionUtilisateur}">
		
		<!-- Affichage des TODOs -->
		Nombre de TODO : ${visu.nbTodo}
		<div class="panel-group" id="accordion">

			<!-- Pour chaque Todo ... -->
			<c:forEach items="${todo}" var="myTodo">

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#${myTodo.idTodo}"> Titre : ${myTodo.titre} </a>
						</h4>
					</div>
					<div id="${myTodo.idTodo}" class="panel-collapse collapse">
						<div class="panel-body">
							Contexte : ${myTodo.contexte} <br /> Description :
							${myTodo.description} <br /> Priorité :
							${myTodo.degreImportance} <br /> Date début :
							${myTodo.echeanceBegin} --- Date fin : ${myTodo.echeanceEnd}
						</div>
					</div>
				</div>
			</c:forEach>

		</div>

		</c:if>
		<c:if test="${empty sessionScope.sessionUtilisateur}">
			==================== /!\ ======================<br />
			Veuillez vous connecter pour visualiser vos TODOs.<br />
			==================== /!\ ======================<br />
		</c:if>

		<%@ include file="footer.jsp"%>
</body>
<script type="text/javascript"><%@include file="lib/css/js/jquery.js" %></script>
<script type="text/javascript"><%@include file="lib/css/js/bootstrap.min.js" %></script>
<script type="text/javascript"><%@include file="lib/css/js/bootstrap.js" %></script>
</html>
