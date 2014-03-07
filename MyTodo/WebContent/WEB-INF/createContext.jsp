
			<!-- Create and Visualisation Context -->

			<div class="btn-group">
				<form role="form" method="post" action="visualisation">
					<input type="hidden" value="createContext" name="page">
					<label for="contexte">Nouveau contexte :</label> <br />
					<input type="text" class="form-control" id="contexte"
						name="contexte" placeholder="contexte"  /> ${form.erreurs['contexte']}
						<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown">
						<button type="button" id="btnColor" class="btn-color" style="height: 15px;background: #FFFFFF"></button></a>
					<ul class="dropdown-menu">
						<li><div id="colorpalette1"></div></li>
					</ul>
					<input type="hidden" name="color" id="color" value="#FFFFFF"/>
					<input type="submit" value="Créer" />
				</form>
				
			</div>
			
			<script>
				$('#colorpalette1').colorPalette().on('selectColor',
						function(e) {
							$('#selected-color1').val(e.color);
							$('#btnColor').css("background", e.color);
							$('#color').val(e.color);
						});
			</script>