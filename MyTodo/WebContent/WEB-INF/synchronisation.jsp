	<form method="post" action="visualisation">
		<input type="hidden" value="synchro" name="page">
		<c:choose>
			<c:when test="${sessionScope.sessionUtilisateur.gmail != null}">
				<fieldset>
					<select class="form-control" name="calendar">
						<c:forEach var="i" begin="0" end="${formSynchro.getCalendar().size()-1}" step="1">
							<option value="${i}">${formSynchro.calendar[i].getSecond()}</option>
						</c:forEach>
					</select> <input type="submit" value="Synchronisation" class="sansLabel" />
					<br />
				</fieldset>
			</c:when>
			<c:otherwise>  
            	Vous n'avez pas de compte gmail associé.
         </c:otherwise>
		</c:choose>

	</form>