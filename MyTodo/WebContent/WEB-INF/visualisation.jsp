<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyTodo</title>
</head>
<body>
	Bienvenue dans la visualisation de vos Todo !!

	<%-- Vérification de la présence d'un objet utilisateur en session --%>
	<c:if test="${!empty sessionScope.sessionUtilisateur}">
		<%-- Si l'utilisateur existe en session, alors on affiche son login. --%>
		<p class="succes">Vous êtes connecté(e) avec le login :
			${sessionScope.sessionUtilisateur.login}</p>
	</c:if>
	<br />
	<a href="<c:url value="/createTodo">
				</c:url>">Création d'un nouveau TODO.</a>
	<a href="<c:url value="/synchronisation">
				</c:url>">Synchronisation avec google Calendar.</a>
</body>
</html>