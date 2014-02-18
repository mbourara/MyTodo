<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Création d'un TODO</title>
<style>
<%@include file="lib/css/css/calendar.css" %> 
</style>
</head>
<body>
	<form method="post" action="createTodo">
		<fieldset>
			<legend>Nouveau TODO :</legend>
			<label for="title">Titre : <span class="requis">*</span></label> <br />
			<input type="text" id="title" name="title"
				value="${form.contains['title']}" size="110" maxlength="60"
				style="width: 939px;" /> ${form.erreurs['title']} <br /> <label
				for="description">Description : <span class="requis">*</span>
			</label> <br />
			<textarea name="description" rows=7 cols=132>${form.contains['description']}</textarea>
			${form.erreurs['description']} <label for="contexte">Contexte
				: <span class="requis">*</span>
			</label> <br /> <input type="text" id="contexte" name="contexte"
				value="${form.contains['contexte']}" size="110" maxlength="60"
				style="width: 939px;" /> ${form.erreurs['contexte']}<br /> <label
				for="date">Echéance : <span class="requis">*</span></label> <br />
			 <label
				for="date">Date de début : <br />
			<input id="dateb" onclick="ds_sh(this);" name="dateb"
				readonly="readonly" style="cursor: text"
				value="${form.contains['dateb']}" />${form.erreurs['dateb']}
			<table class="ds_box" cellpadding="0" cellspacing="0"
				id="ds_conclass" style="display: none;">
				<tr>
					<td id="ds_calclass"></td>
				</tr>
			</table>
			<select
				name="hourb" size="1">
				<c:forEach var="i" begin="0" end="24" step="1">
					<option><c:if test="${i < 10}" var="maVariable" scope="session">0</c:if>${i}
				</c:forEach>
			</select>
			<select
				name="minb" size="1">
				<c:forEach var="i" begin="0" end="59" step="1">
					<option><c:if test="${i < 10}" var="maVariable" scope="session">0</c:if>${i}
				</c:forEach>
			</select><br />
			<label
				for="date">Date de fin : <br />
			<input id="datee" onclick="ds_sh(this);" name="datee"
				readonly="readonly" style="cursor: text"
				value="${form.contains['datee']}" />${form.erreurs['datee']}
			<table class="ds_box" cellpadding="0" cellspacing="0"
				id="ds_conclass" style="display: none;">
				<tr>
					<td id="ds_calclass"></td>
				</tr>
			</table>
			<select
				name="houre" size="1">
				<c:forEach var="i" begin="0" end="24" step="1">
					<option><c:if test="${i < 10}" var="maVariable" scope="session">0</c:if>${i}
				</c:forEach>
			</select>
			<select
				name="mine" size="1">
				<c:forEach var="i" begin="0" end="59" step="1">
					<option><c:if test="${i < 10}" var="maVariable" scope="session">0</c:if>${i}
				</c:forEach>
			</select><br />
			
			<br /> <label for="degres">Degrès d'importance : </label> <select
				name="degres" size="1">
				<c:forEach var="i" begin="0" end="100" step="1">
					<option
						<c:if test="${ i == form.selected }" var="maVariable" scope="session">
selected
</c:if>>${i}
				</c:forEach>
			</select><br /> <input type="submit" value="Création" class="sansLabel" /> <br />

			<script type="text/javascript">
			<%@include file="lib/css/js/calendar.js" %> 
			</script>
			</fieldset>
	</form>
</body>
</html>