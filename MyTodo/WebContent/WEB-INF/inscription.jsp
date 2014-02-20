
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

<title>inscription</title>

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

			<a href="connexion" class="btn btn-default navbar-btn" role="button">Connexion</a>
			<a href="inscription" class="btn btn-primary navbar-btn" role="button">Inscription</a>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
		<h2>Formulaire d'inscription</h2>
		<hr />
		<div style="text-align:justify;">
			<p>Vous pouvez vous inscrire via ce formulaire.</p>
		</div>
		<hr />
		
		<div class="col-md-offset-4 col-md-4">
			<div class="row">
				<form role="form" method="post" action="inscription">
					<div class="form-group ${form.erreurs['prenom'] != null ? ' has-error' : ''} ">
						<label for="prenom">Prénom</label> <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Prénom" />
					</div>
					<div class="${form.erreurs['prenom'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['prenom']}</div>
					
					<div class="form-group ${form.erreurs['nom'] != null ? ' has-error' : ''} ">
						<label for="nom">Nom</label> <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom" />
					</div>
					<div class="${form.erreurs['nom'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['nom']}</div>
					
					<div class="form-group ${form.erreurs['email'] != null ? ' has-error' : ''} ">
						<label for="email">Adresse email</label> <input type="text" class="form-control" id="email" name="email" maxlength="60" placeholder="Adresse email" />
					</div>
					<div class="${form.erreurs['email'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['email']}</div>
					
					<div class="form-group ${form.erreurs['login'] != null ? ' has-error' : ''} ">
						<label for="login">Login</label> <input type="text" class="form-control" id="login" name="login" maxlength="20" placeholder="Login" />
					</div>
					<div class="${form.erreurs['login'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['login']}</div>
						
					<div class="form-group ${form.erreurs['motdepasse'] != null ? ' has-error' : ''} ">
						<label for="motdepasse">Mot de passe</label> <input type="password" class="form-control" id="motdepasse" name="motdepasse" maxlength="20" placeholder="Mot de passe" />
					</div>
					<div class="${form.erreurs['motdepasse'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['motdepasse']}</div>
					
					<div class="form-group ${form.erreurs['confirmation'] != null ? ' has-error' : ''} ">
						<label for="confirmation">Confirmation du mot de passe</label> <input type="password" class="form-control" id="confirmation" name="confirmation" maxlength="20" placeholder="Confirmation du mot de passe" />
					</div>
					<div class="${form.erreurs['confirmation'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['confirmation']}</div>
									
					<div class="form-group ${form.erreurs['gmail'] != null ? ' has-error' : ''} ">
						<label for="gmail">Adresse Gmail</label>
						<div class="input-group">
							<input type="text" class="form-control" id="gemail" name="gmail" maxlength="60" placeholder="Adresse Gmail" />
						<span class="input-group-addon">@gmail.com</span>
						</div>
					</div>
					<div class="${form.erreurs['gmail'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['gmail']}</div>
										
					<div class="pull-right">
						<button type="submit" value="inscription" class="btn btn-primary">Inscription</button>
					</div>
				</form>		
			</div>
		</div>
	</div>
</body>
</html>