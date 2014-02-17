
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
		<h2>Formulaire d'inscription</h2>
		<hr />
		<div style="text-align:justify;">
			<p>Vous pouvez vous inscrire via ce formulaire.</p>
		</div>
		<hr />

<div class="col-md-offset-4 col-md-4">
			<div class="row">
				<form role="form" method="post" action="inscription">
				
					<div class="form-group col-md-13 ${form.erreurs['prenom']} ">
						<label for="prenom">Prénom</label> <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Prénom" />
					</div>
					
					<div class="form-group col-md-13 ${form.erreurs['nom']} ">
						<label for="nom">Nom</label> <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom" />
					</div>
					
					<div class="form-group col-md-13 ${form.erreurs['email']} ">
						<label for="email">Adresse email</label> <input type="text" class="form-control" id="email" name="email" maxlength="60" placeholder="Adresse email" />
					</div>
					
					<div class="form-group col-md-13 ${form.erreurs['login']} ">
							<label for="login">Login</label> <input type="text" class="form-control" id="login" name="login" maxlength="20" placeholder="Login" />
					</div>
						
					<div class="form-group col-md-13 ${form.erreurs['motdepasse']} ">
								<label for="motdepasse">Mot de passe</label> <input type="password" class="form-control" id="motdepasse" name="motdepasse" maxlength="20" placeholder="Mot de passe" />
					</div>
					
					<div class="form-group col-md-13 ${form.erreurs['confirmation']} ">
								<label for="confirmation">Confirmation du mot de passe</label> <input type="password" class="form-control" id="confirmation" name="confirmation" maxlength="20" placeholder="Confirmation du mot de passe" />
					</div>
				
								
				<div class="form-group col-md-13 ${form.erreurs['confirmation']} ">
								<label for="gemail">Adresse Gmail</label> <input type="text" class="form-control" id="gemail" name="gemail" maxlength="60" placeholder="Adresse Gmail" /><label for="gemail">@gmail.com</label>
					</div>
					
				
				<div class="col-md-13">
						<div class="pull-right">
							<button type="submit" value="Inscription" class="btn btn-primary"><i class="glyphicon glyphicon-ok"></i> Inscription</button>
						</div>
					</div>
				</form>
			
</div>
		</div>
	</div>
</body>
</html>