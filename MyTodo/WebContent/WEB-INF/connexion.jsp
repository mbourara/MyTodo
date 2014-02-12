<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion à MyTodo</title>
</head>
<body>
<form method="post" action="connexion">
	    <fieldset>
                <legend>Connexion</legend>
                <p>Vous pouvez vous connecter via le formulaire ci-dessous.</p>

                <label for="login">Login <span class="requis">*</span></label>
                <input type="text" id="login" name="login" value="" size="20" maxlength="60" />
                <span class="erreur">${erreurs['login']}</span>
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${erreurs['motdepasse']}</span>
                <br />

                <input type="submit" value="Connexion" class="sansLabel" />
                <br />
                                
                <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
                
				<c:if test="${connexion}">						                
    				<c:redirect url="http://localhost:8080/MyTodo/visualisation"/>
				</c:if>
            </fieldset>
        </form>
</body>
</html>