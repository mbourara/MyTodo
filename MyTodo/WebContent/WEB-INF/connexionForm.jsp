		<h2>Connexion</h2>
		<hr />
		<div style="text-align:justify;">
			<p>Vous pouvez vous connecter via le formulaire ci-dessous.</p>
		</div>
		<hr />
		<div class="col-md-offset-4 col-md-4 well well-lg">
			<div class="row">
				<form role="form" method="post" action="Connexion">
					<div class="form-group col-md-12 ${form.erreurs['loginC'] != null ? ' has-error' : ''} ">
						<label for="loginC">Login</label> <input type="text" class="form-control" id="loginC" name="loginC" placeholder="Login" value="${form.contains['loginC']}"/>
					</div>
					<div class="${form.erreurs['loginC'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['loginC']}</div>
					
					<div class="form-group col-md-12 ${form.erreurs['motdepasseC'] != null ? ' has-error' : ''}">
						<label for="motdepasseC">Mot de passe</label>
						<input type="password" class="form-control" id="motdepasseC" name="motdepasseC" placeholder="Mot de passe" value="${form.contains['motdepasseC']}"/>
					</div>
					<div class="${form.erreurs['motdepasseC'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['motdepasseC']}</div>
					
					<div class="col-md-12">
						<div class="pull-right">
							<button type="submit" class="btn btn-primary">Connexion</button>
						</div>
					</div>
				</form>
	
				<div class="${form.erreurs['bd'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['bd']}</div>
				<div class="${empty form.erreurs ? 'succes' : 'erreur'} col-md-12">${form.resultat}</div>
			</div>
		</div>