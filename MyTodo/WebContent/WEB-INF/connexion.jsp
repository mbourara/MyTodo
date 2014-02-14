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

<title>Connexion à MyTodo</title>

</head>
<body>
	<div class="container">
		<h2>Connexion</h2>
		<hr />
		<div style="text-align:justify;">
			<p>Vous pouvez vous connecter via le formulaire ci-dessous.</p>
			<p>Vous n'avez pas encore de compte enregistré ? <a href="<c:url value="/inscription"> </c:url>">Cliquez sur ce lien pour en créer un !</a></p>
		</div>
		<hr />
		<div class="col-md-offset-4 col-md-4">
			<div class="row">
				<form role="form" method="post" action="Connexion">
					<div class="form-group col-md-12 ${form.erreurs['login'] != null ? ' has-error' : ''} ">
						<label for="login">Login</label> <input type="text" class="form-control" id="login" name="login" placeholder="Login" />
						<div class="${form.erreurs['login'] != null ? ' alert alert-danger' : ''} col-md-2">${form.erreurs['login']}</div>
					</div>
					<div class="form-group col-md-12 ${form.erreurs['motdepasse'] != null ? ' has-error' : ''}">
						<label for="motdepasse">Mot de passe</label>
						<input type="password" class="form-control" id="motdepasse" name="motdepasse" placeholder="Mot de passe" />
						<div class="${form.erreurs['motdepasse'] != null ? ' alert alert-danger' : ''} col-md-2">${form.erreurs['motdepasse']}</div>
					</div>
					<div class="col-md-12">
						<div class="pull-right">
							<button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-ok"></i> Connexion</button>
						</div>
					</div>
				</form>

				<div class="${form.erreurs['bd'] != null ? ' alert alert-danger' : ''} col-md-3">${form.erreurs['bd']}</div>
				<div class="${empty form.erreurs ? 'succes' : 'erreur'} row col-md-3">${form.resultat}</div>
			</div>
		</div>
	</div>
</body>
</html>