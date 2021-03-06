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

<style>
<%@include file="lib/css/css/calendar.css" %> 
</style>

<title>Modification du TODO</title>

</head>
	<body>
	
	<div class="container">
		<table class="ds_box" cellpadding="0" cellspacing="0"
			id="ds_conclass" style="display: none;">
			<tr>
				<td id="ds_calclass"></td>
			</tr>
		</table>
				
		<h2>Modification du TODO</h2>
		
		<div class="col-md-offset-3 col-md-8">
			<div class="row">
				<form role="form" method="post" action="modifyTodo">
					<input name="idTodo" type="hidden" value="${todo.idTodo}">
					<div class="form-group col-md-12 ${form.erreurs['title'] != null ? ' has-error' : ''} ">
						<label for="title">Titre</label> 
						<input type="text" class="form-control" id="title" name="title" value="${todo.titre}" placeholder="Titre du Todo" />
					</div>
					<div class="${form.erreurs['title'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['title']}</div>
					
					<div class="form-group col-md-12 ${form.erreurs['description'] != null ? ' has-error' : ''} ">
						<label for="description">Description</label> 
						<textarea class="form-control" rows ="3" id="description" name="description" placeholder="Description">${todo.description}</textarea>
					</div>
					<div class="${form.erreurs['description'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['description']}</div>
					
					
					<div class="form-group col-md-12 ${form.erreurs['contexte'] != null ? ' has-error' : ''} ">
						<label for="contexte">Contexte</label> 
						<select class="form-control" name="contexte" id="contexte">
							
							<c:forEach items="${contexte}" var="myCont">
								<option value="${myCont.idContexte}">${myCont.nom}</option>
							</c:forEach>
						</select>
					</div>
					<div class="${form.erreurs['contexte'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['contexte']}</div>
					
					<div class="form-group col-md-12">
						<label for="dateb">Echeance :</label>
						
						<div class="form-group">
							<div class="col-md-6">
								<input id="date" onclick="ds_sh(this);" name="date" readonly="readonly" style="cursor: text" class="form-control" 
								value="${todo.echeance}" />
							</div>
									
						</div>
					</div>
					<div class="${form.erreurs['dateb'] != null ? ' alert alert-danger' : ''} col-md-12">${form.erreurs['dateb']}</div>
						
					<div class="form-inline">
						<label for="degres">Degrès d'importance : </label>
						<select class="form-control" name="degres" size="1">
							<c:forEach var="i" begin="0" end="100" step="1">
								<option <c:if test="${ i == form.selected }" var="maVariable" scope="session">selected</c:if> ${todo.degreImportance == i ? 'selected="selected"' : '' }>${i}
							</c:forEach>
						</select>
					</div>
								
					<div class="col-md-12">
						<div class="pull-right">
							<button type="submit" class="btn btn-primary">Modifier</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript"><%@include file="lib/css/js/calendar.js" %></script>
	
	</body>
</html>