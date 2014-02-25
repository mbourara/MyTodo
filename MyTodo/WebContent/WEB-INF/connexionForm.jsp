		<h2>Connexion</h2>
		<hr />
		<div style="text-align:justify;">
			<p>Vous pouvez vous connecter via le formulaire ci-dessous.</p>
		</div>
		<hr />
		<div class="col-md-offset-4 col-md-4 well well-lg">
			<div class="row">
				<form role="form" method="post" action="Connexion">
					<div class="form-group col-md-12 ${form.erreurs['login'] != null ? ' has-error' : ''} ">
						<label for="login">Login</label> <input type="text" class="form-control" id="login" name="login" placeholder="Login" />
					</div>
					<div class="${form.erreurs['login'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['login']}</div>
					
					<div class="form-group col-md-12 ${form.erreurs['motdepasse'] != null ? ' has-error' : ''}">
						<label for="motdepasse">Mot de passe</label>
						<input type="password" class="form-control" id="motdepasse" name="motdepasse" placeholder="Mot de passe" />
					</div>
					<div class="${form.erreurs['motdepasse'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['motdepasse']}</div>
					
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