				<!-- Affichage des TODOs -->
				<div class="col-md-12">Nombre de TODO : ${visu.nbTodo}</div>
				<div class="col-md-12">
					<div class="panel-group" id="accordion">

						<!-- Pour chaque Todo ... -->
						<c:forEach items="${todo}" var="myTodo">

							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#${myTodo.idTodo}"> Titre : ${myTodo.titre} </a>
									<a href="http://localhost:8080/MyTodo/suppression?deleteTodo=${myTodo.idTodo}">
							<button	type="button" class="close">&times;</button></a>
								</h4>
								</div>
								<div id="${myTodo.idTodo}" class="panel-collapse collapse">
									<div class="panel-body">
										Contexte : ${myTodo.contexte} <br /> Description :
										${myTodo.description} <br /> Priorité :
										${myTodo.degreImportance} <br /> Echéance :
										${myTodo.echeance}
										
										<form method="post" action="modifyTodo">
							<input name="IDTodo" type="hidden" value="${myTodo.idTodo}">
							<input border=0 src="./img/pencil-icon.png" type="image" Value="submit" align="right">
										</form>
									</div>
								</div>
							</div>
						</c:forEach>

					</div>
				</div>