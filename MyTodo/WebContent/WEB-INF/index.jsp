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

<title>Connexion à MyTodo</title>

</head>
<body>

	<div class="container">
		
	<nav class="navbar navbar-default" role="navigation">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	    	<a href="visualisation" class="navbar-brand">My Todo</a>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse navbar-right">

		<ul class="nav nav-pills" id="logTab">
		  	<li ${form.contains['type'] != 'inscription' ? ' class="active"' : ''}><a href="#connexion">Connexion</a></li>
		  	<li ${form.contains['type'] == 'inscription' ? ' class="active"' : ''}><a href="#inscription">Inscription</a></li>
		</ul>

	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
	
	<div class="tab-content">
		<div class="tab-pane ${form.contains['type'] != 'inscription' ? ' active' : ''}" id="connexion"><%@ include file="connexionForm.jsp" %></div>
		<div class="tab-pane ${form.contains['type'] == 'inscription' ? ' active' : ''}" id="inscription"><%@ include file="inscriptionForm.jsp" %></div>
	</div>	
	</div>	
	
<script type="text/javascript"><%@include file="lib/css/js/jquery.js" %></script>
<script type="text/javascript"><%@include file="lib/css/js/bootstrap.min.js" %></script>
<script type="text/javascript"><%@include file="lib/css/js/bootstrap.js" %></script>

<script type="text/javascript">
$('#logTab a').click(function (e) {
	  e.preventDefault()
	  $(this).tab('show')
	})
</script>
</body>
</html>