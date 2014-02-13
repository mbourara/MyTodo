<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.css" />
    	<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    	<script src="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>
    
        <meta charset="utf-8" />
        <title>Formulaire de contact</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
    </head>
    <body>
        <form method="post" action="inscription">
	    <fieldset>
	    
                <legend>Formulaire</legend>
                <p>Vous pouvez vous inscrire via ce formulaire.</p>
                 
                 <!--  p id="civilite"><label>civilité: </label>
					 <fieldset data-role="controlgroup" id="radio-1">
						 <legend></legend>
						 <input name="radio-choice-1" id="radio-choice-1a" value="M." type="radio"/>
						 <label for="radio-choice-1a">M.</label>
						 <input name="radio-choice-1" id="radio-choice-1b" value="Mlle" type="radio"/>
						 <label for="radio-choice-1b">Mlle</label>
						 <input name="radio-choice-1" id="radio-choice-1c" value="Mme" type="radio"/>
						 <label for="radio-choice-1c">Mme</label>
					 </fieldset>.
 				 </p>-->
 				 
 				  <label for="prenom">Prénom<span class="requis">*</span></label>
				 <input type="text" name="prenom" size="30" /><br />

				  <label for="nom">Nom<span class="requis">*</span></label>
				 <input type="text" name="nom" size="30" /><br />
				
               <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="" size="20" maxlength="60" />
                <span class="erreur">${erreurs['email']}</span><br />
				<label for="login">Login<span class="requis">*</span></label>
                <input type="text" id="login" name="login" value="" size="20" maxlength="20" />         
                <span class="erreur">${erreurs['nom']}</span>
                <br />
				 
                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${erreurs['motdepasse']}</span>
                <br />

                <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <span class="erreur">${erreurs['confirmation']}</span>
                <br />

               <label for="email">Adresse Gmail <span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="" size="20" maxlength="60" />
                <span class="erreur">${erreurs['email']}</span><br />
                <br />

                <input type="submit" value="Inscription" class="sansLabel" />
                
				
                <br />
            </fieldset>
        </form>
       
    </body>
</html>