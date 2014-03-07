<div class="col-md-12 well">		
		<c:set var="adj" value="Aujourd'hui"/>
			<form method="post" action="visualisation">
				<input type="hidden" name="page" value="visu">
				Filtrer vos TODO(s) : <c:if test="${filtre eq 'Tous'}"><b></c:if>
				<input name="typeFiltre" type="submit" Value="Tous">
				<c:if test="${filtre eq 'Tous'}"></b></c:if> 
				- <c:if test="${filtre eq adj}"><b></c:if>
				<input name="typeFiltre" type="submit" Value="Aujourd'hui">
				<c:if test="${filtre eq adj}"></b></c:if>
				- <c:if test="${filtre eq 'Semaine'}"><b></c:if>
				<input name="typeFiltre" type="submit" Value="Semaine">
				<c:if test="${filtre eq 'Semaine'}"></b></c:if>
				- <c:if test="${filtre eq 'Mois'}"><b></c:if>
				<input name="typeFiltre" type="submit" Value="Mois"><br />
				<c:if test="${filtre eq 'Mois'}"></b></c:if>
				Trier vos TODO(s) : <c:if test="${tri eq 'Date de creation'}"><b></c:if>
				<input name="typeTri" type="submit" Value="Date de creation">
				<c:if test="${tri eq 'Date de creation'}"></b></c:if> 
				- <c:if test="${tri eq 'Priorite'}"><b></c:if>
				<input name="typeTri" type="submit" Value="Priorite">
				<c:if test="${tri eq 'Priorite'}"></b></c:if>
				- <c:if test="${tri eq 'Alphabetique'}"><b></c:if>
				<input name="typeTri" type="submit" Value="Alphabetique">
				<c:if test="${tri eq 'Alphabetique'}"></b></c:if>
			</form>
		</div>
		
		<div class="col-md-12 well">
					<!-- Pour chaque Contexte ... -->
			<div class="btn-group">
				<c:forEach items="${contexte}" var="myCont">
					<button type="button" id="btncolor" class="btn-color" style="background: ${myCont.couleur}">
					${myCont.nom}
					</button>
					
				</c:forEach>
			</div>		
		</div>
		
		<div class="col-md-12 well">
			<div class="col-md-12">Bienvenue dans la visualisation de vos
				Todo !!</div>
			<c:if test="${!empty sessionScope.sessionUtilisateur}">

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
											href="#${myTodo.idTodo}"> Titre : ${myTodo.titre} </a> <a
											href="http://localhost:8080/MyTodo/suppression?deleteTodo=${myTodo.idTodo}">
											<button type="button" class="close">&times;</button>
										</a>
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
											<input border=0 src="./img/pencil-icon.png" type="image"
												Value="submit" align="right">
										</form>
									</div>
								</div>
							</div>
						</c:forEach>

					</div>
				</div>
			</c:if>
		</div>