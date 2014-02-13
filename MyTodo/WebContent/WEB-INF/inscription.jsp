<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>inscription</title>
</head>
<body>
	<form method="post" action="inscription">
		<fieldset>

			<legend>Formulaire</legend>
			<p>Vous pouvez vous inscrire via ce formulaire.</p>



			<label for="prenom">Prénom<span class="requis">*</span></label> <input
				type="text" name="prenom" size="30" /> <span class="erreur">${form.erreurs['prenom']}</span><br />

			<label for="nom">Nom<span class="requis">*</span></label> <input
				type="text" name="nom" size="30" /> <span class="erreur">${form.erreurs['nom']}</span><br />

			<label for="email">Adresse email <span class="requis">*</span></label>
			<input type="text" id="email" name="email" value="" size="20"
				maxlength="60" /> <span class="erreur">${form.erreurs['email']}</span><br />
				
			<label for="login">Login<span class="requis">*</span></label> <input
				type="text" id="login" name="login" value="" size="20"
				maxlength="20" /> <span class="erreur">${form.erreurs['login']}</span>
				
			<br /> <label for="motdepasse">Mot de passe <span
				class="requis">*</span></label> <input type="password" id="motdepasse"
				name="motdepasse" value="" size="20" maxlength="20" /> <span
				class="erreur">${form.erreurs['motdepasse']}</span> <br /> <label
				for="confirmation">Confirmation du mot de passe <span
				class="requis">*</span></label> <input type="password" id="confirmation"
				name="confirmation" value="" size="20" maxlength="20" /> <span
				class="erreur">${form.erreurs['confirmation']}</span> <br /> <label
				for="email">Adresse Gmail <span class="requis">*</span></label> <input
				type="text" id="email" name="email" value="" size="20"
				maxlength="60" /> <span class="erreur">${form.erreurs['email']}</span><br />
			<br /> <input type="submit" value="Inscription" class="sansLabel" />


			<br />
		</fieldset>
	</form>

</body>
</html>